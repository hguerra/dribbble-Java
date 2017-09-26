package br.com.geoambiente.repository;

import br.com.geoambiente.domain.impl.Screenshot;
import br.com.geoambiente.service.DribbbleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScreenshotRepositoryTest {

    private static final int PAGE = 1;

    @Autowired
    private ScreenshotRepository repository;

    @Autowired
    private DribbbleService service;

    @Value("${app.dribbble.per_page}")
    private int QUANTITY_PER_PAGE;

    private List<Screenshot> screenshots;

    @Before
    public void setUp() throws Exception {
        this.screenshots = this.service.getScreenshots(PAGE);
    }

    @After
    public void tearDown() throws Exception {
        this.screenshots = null;
    }

    @Test
    public void shouldNotBeNullRepository() {
        assertNotNull(this.repository);
    }

    @Test
    public void shouldNotBeNullService() {
        assertNotNull(this.repository);
    }

    @Test
    public void shouldNotBeNullScreenshots() {
        assertNotNull(this.screenshots);
        assertThat(this.screenshots.size(), equalTo(QUANTITY_PER_PAGE));
    }

    @Test
    public void shouldSaveAllScreenshotFromDribbbleRequest() {
        List<Screenshot> saved = this.repository.save(this.screenshots);
        assertNotNull(saved);

        Screenshot shot = saved.get(0);

        assertNotNull(shot);
        assertNotNull(shot.getAuthor().getPkey());
        assertNotNull(shot.getImages().getPkey());
        assertNotNull(shot.getPkey());
    }


    @Test
    public void shouldRemoveScreenshot() {
        this.repository.deleteAll();
        List<Screenshot> saved = this.repository.save(this.screenshots);
        assertNotNull(saved);

        Screenshot shot = saved.get(0);

        assertNotNull(shot);
        assertThat(saved.size(), equalTo(QUANTITY_PER_PAGE));

        this.repository.delete(shot);
        saved = this.repository.findAll();
        assertThat(saved.size(), equalTo(QUANTITY_PER_PAGE -1));
    }

    @Test
    public void shouldFindAllScreenshots() {
        System.out.println(this.screenshots);
        List<Screenshot> saved = this.repository.save(this.screenshots);
        assertNotNull(saved);
        assertThat(saved.size() + 1, equalTo(this.repository.findAll().size()));
    }

    @Test
    public void shouldFindAllScreenshotsOrderByAddedAt() {
        List<Screenshot> saved = this.repository.save(this.screenshots);
        assertNotNull(saved);

        List<Screenshot> favorites = this.repository.findAllOrderByAddedAtDesc();

        Screenshot before = saved.get(0);
        Screenshot after = favorites.get(0);
        assertThat(after.getAddedAt().isAfter(before.getAddedAt()), is(true));
    }

    @Test
    public void shouldFindAllRecentlyScreenshots() {
        List<Screenshot> saved = this.repository.save(this.screenshots);
        assertNotNull(saved);

        ZonedDateTime limit = ZonedDateTime.now(ZoneOffset.UTC).minusMinutes(2);
        List<Screenshot> recently = this.repository.findByAddedAtGreaterThan(limit);

        recently.forEach(screenshot -> {
            assertThat(screenshot.getAddedAt().isAfter(limit), is(true));
        });
    }
}