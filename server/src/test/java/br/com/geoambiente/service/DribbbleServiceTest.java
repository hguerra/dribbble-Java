package br.com.geoambiente.service;

import br.com.geoambiente.domain.impl.Screenshot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DribbbleServiceTest {
    private static final int PAGE = 1;

    @Value("${app.dribbble.per_page}")
    private int QUANTITY_PER_PAGE;

    @Autowired
    private DribbbleService service;

    @Test
    public void shouldNotBeNullService() {
        assertNotNull(this.service);
    }

    @Test
    public void shouldHasSizeEqualsToTen() {
        List<Screenshot> screenshots = this.service.getScreenshots(PAGE);

        assertNotNull(screenshots);
        assertThat(screenshots.size(), equalTo(QUANTITY_PER_PAGE));
    }

}