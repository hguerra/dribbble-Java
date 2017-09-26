package br.com.geoambiente.controller;

import br.com.geoambiente.domain.impl.Author;
import br.com.geoambiente.domain.impl.Image;
import br.com.geoambiente.service.ScreenshotService;
import br.com.geoambiente.domain.impl.Screenshot;
import br.com.geoambiente.service.DribbbleService;
import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScreenshotControllerTest {
    private static final int JSON_SIZE = 1;
    private static final String JSON_SCHEMA = "shot-schema.json";

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ScreenshotService screenshotServiceMock;


    @MockBean
    private DribbbleService dribbbleServiceMock;


    @Before
    public void setUp() throws Exception {
        Author author = Author.builder()
                .dribbbleId(127552L)
                .name("Heitor Carneiro")
                .username("hguerra")
                .build();

        Image images = Image.builder()
                .teaser("https://cdn.dribbble.com/users/127552/screenshots/3831139/icons_teaser.gif")
                .normal("https://cdn.dribbble.com/users/127552/screenshots/3831139/icons_1x.gif")
                .hidpi("https://cdn.dribbble.com/users/127552/screenshots/3831139/icons.gif")
                .build();

        Screenshot screenshot = Screenshot.builder()
                .dribbbleId(3831139L)
                .author(author)
                .images(images)
                .createdAt(ZonedDateTime.now(ZoneOffset.UTC))
                .viewsCount(1)
                .likesCount(1)
                .bucketsCount(1)
                .build();

        given(this.dribbbleServiceMock.getScreenshots(any()))
                .willReturn(Collections.singletonList(screenshot));

        given(this.screenshotServiceMock.findFavorites())
                .willReturn(Collections.singletonList(screenshot));
    }

    @Test
    public void shouldReturnPopularShots() {
        given()
                .webAppContextSetup(context)
                .when()
                .get("/shots").
                then()
                .statusCode(200);
    }

    @Test
    public void shouldReturnFavoritesShots() throws ProcessingException {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().
                setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();

        given()
                .webAppContextSetup(context)
                .when()
                .get("/favorites").
                then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .assertThat().body(matchesJsonSchemaInClasspath(JSON_SCHEMA).using(jsonSchemaFactory))
                .body("size", equalTo(JSON_SIZE))
                .body("user.name", hasItems("Heitor Carneiro"));
    }
}