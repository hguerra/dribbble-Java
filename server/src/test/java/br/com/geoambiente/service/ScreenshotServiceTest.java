package br.com.geoambiente.service;

import br.com.geoambiente.TestData;
import br.com.geoambiente.domain.impl.Author;
import br.com.geoambiente.domain.impl.Image;
import br.com.geoambiente.domain.impl.Screenshot;
import br.com.geoambiente.repository.ScreenshotRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScreenshotServiceTest {

    @Autowired
    private ScreenshotService service;

    @MockBean
    private ScreenshotRepository repositoryMock;

    private Screenshot screenshot;


    @Before
    public void setUp() throws Exception {
        this.screenshot = TestData.screenshot();
        Screenshot screenshotMock = Screenshot.builder()
                .dribbbleId(this.screenshot.getDribbbleId())
                .author(this.screenshot.getAuthor())
                .images(this.screenshot.getImages())
                .createdAt(this.screenshot.getCreatedAt())
                .viewsCount(this.screenshot.getViewsCount())
                .likesCount(this.screenshot.getLikesCount())
                .bucketsCount(this.screenshot.getBucketsCount())
                .build();
        screenshotMock.setPkey(1L);

        given(this.repositoryMock.save(this.screenshot))
                .willReturn(screenshotMock);

        given(this.repositoryMock.findAll())
                .willReturn(Collections.singletonList(this.screenshot));

        given(this.repositoryMock.findAllOrderByAddedAtDesc())
                .willReturn(Collections.singletonList(this.screenshot));

        given(this.repositoryMock.findByAddedAtGreaterThan(any()))
                .willReturn(Collections.singletonList(this.screenshot));
    }

    @After
    public void tearDown() throws Exception {
        this.screenshot = null;
    }

    @Test
    public void shoudNotBeNullService() {
        assertNotNull(this.service);
    }

    @Test
    public void addToFavorites() {
        Screenshot saved = this.service.addToFavorites(this.screenshot);
        assertNotNull(saved.getPkey());
    }

    @Test
    public void findFavorites() {
        Screenshot saved = this.service.addToFavorites(this.screenshot);
        assertNotNull(saved.getPkey());

        List<Screenshot> favorites = this.service.findFavorites();
        assertThat(favorites.size(), is(1));
    }

    @Test
    public void findFavoritesByDate() {
        Screenshot saved = this.service.addToFavorites(this.screenshot);
        assertNotNull(saved.getPkey());

        List<Screenshot> favorites = this.service.findFavoritesByDate();
        assertThat(favorites.size(), is(1));
    }

    @Test
    public void findFavoritesRecentlyAdded() {
        Screenshot saved = this.service.addToFavorites(this.screenshot);
        assertNotNull(saved.getPkey());

        List<Screenshot> favorites = this.service.findFavoritesRecentlyAdded();
        assertThat(favorites.size(), is(1));
    }

}