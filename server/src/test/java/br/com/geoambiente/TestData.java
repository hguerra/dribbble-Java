package br.com.geoambiente;

import br.com.geoambiente.domain.impl.Author;
import br.com.geoambiente.domain.impl.Image;
import br.com.geoambiente.domain.impl.Screenshot;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class TestData {
    public static Author author() {
        return Author.builder()
                .dribbbleId(127552L)
                .name("Heitor Carneiro")
                .username("hguerra")
                .build();
    }

    public static Image images() {
        return Image.builder()
                .teaser("https://cdn.dribbble.com/users/127552/screenshots/3831139/icons_teaser.gif")
                .normal("https://cdn.dribbble.com/users/127552/screenshots/3831139/icons_1x.gif")
                .hidpi("https://cdn.dribbble.com/users/127552/screenshots/3831139/icons.gif")
                .build();
    }

    public static Screenshot screenshot() {
        return Screenshot.builder()
                .dribbbleId(3831139L)
                .author(author())
                .images(images())
                .createdAt(ZonedDateTime.now(ZoneOffset.UTC))
                .viewsCount(1)
                .likesCount(1)
                .bucketsCount(1)
                .build();
    }
}
