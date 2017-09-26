package br.com.geoambiente;

import br.com.geoambiente.domain.impl.Author;
import br.com.geoambiente.domain.impl.Image;
import br.com.geoambiente.domain.impl.Screenshot;
import br.com.geoambiente.service.ScreenshotService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;


@SpringBootApplication
public class Application {

    @Bean
    InitializingBean saveData(ScreenshotService shotRepository) {
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

        return () -> {
            shotRepository.addToFavorites(screenshot);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
