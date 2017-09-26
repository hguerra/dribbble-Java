package br.com.geoambiente.controller;

import br.com.geoambiente.TestData;
import br.com.geoambiente.domain.impl.Screenshot;
import br.com.geoambiente.service.DribbbleService;
import br.com.geoambiente.service.ScreenshotService;
import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScreenshotControllerTest {
    private static final int JSON_SIZE = 1;
    private static final String JSON_SCHEMA = "shot-schema.json";
    private static final String JSON_USER_NAME = "Heitor Carneiro";

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ScreenshotService screenshotServiceMock;

    @MockBean
    private DribbbleService dribbbleServiceMock;

    private MediaType contentType;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
        this.mockMvc = webAppContextSetup(context).build();

        Screenshot screenshot = TestData.screenshot();
        List<Screenshot> response = Collections.singletonList(screenshot);
        given(this.dribbbleServiceMock.getScreenshots(any()))
                .willReturn(response);

        given(this.screenshotServiceMock.findFavorites())
                .willReturn(response);

        given(this.screenshotServiceMock.findFavoritesByDate())
                .willReturn(response);

        given(this.screenshotServiceMock.findFavoritesRecentlyAdded())
                .willReturn(response);
    }

    @After
    public void tearDown() throws Exception {
        this.contentType = null;
        this.mockMvc = null;
    }

    @Test
    public void shouldReturnPopularShots() {
        given()
                .webAppContextSetup(context)
                .when()
                    .get("/shots/1")
                .then()
                    .statusCode(OK.value());
    }

    @Test
    public void shouldReturnFavoritesShots() throws ProcessingException {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().
                setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();

        given()
                .webAppContextSetup(context)
                .when()
                    .get("/favorites")
                .then()
                    .statusCode(OK.value())
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .assertThat().body(matchesJsonSchemaInClasspath(JSON_SCHEMA).using(jsonSchemaFactory))
                    .body("size", equalTo(JSON_SIZE))
                    .body("user.name", hasItems(JSON_USER_NAME));
    }

    @Test
    public void shouldReturnFavoritesShotsByDate() throws ProcessingException {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().
                setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();

        given()
                .webAppContextSetup(context)
                .when()
                    .get("/favorites/date")
                .then()
                    .statusCode(OK.value())
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .assertThat().body(matchesJsonSchemaInClasspath(JSON_SCHEMA).using(jsonSchemaFactory))
                    .body("size", equalTo(JSON_SIZE))
                    .body("user.name", hasItems(JSON_USER_NAME));
    }


    @Test
    public void shouldReturnFavoritesShotsRecentlyAdded() throws ProcessingException {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().
                setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();

        given()
                .webAppContextSetup(context)
                .when()
                    .get("/favorites/recent")
                .then()
                    .statusCode(OK.value())
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .assertThat().body(matchesJsonSchemaInClasspath(JSON_SCHEMA).using(jsonSchemaFactory))
                    .body("size", equalTo(JSON_SIZE))
                    .body("user.name", hasItems(JSON_USER_NAME));
    }

    @Test
    public void shouldAddScreenshotToFavorites() throws Exception {
        Screenshot screenshot = TestData.screenshot();
        screenshot.setTitle("shouldAddScreenshotToFavorites");

        mockMvc.perform(post("/favorites")
                .content(screenshot.toString())
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRemoveScreenshotFromFavorites() throws Exception {
        Screenshot screenshot = TestData.screenshot();
        screenshot.setPkey(1L);

        mockMvc.perform(delete("/favorites")
                .content(screenshot.toString())
                .contentType(contentType))
                .andExpect(status().isOk());
    }
}