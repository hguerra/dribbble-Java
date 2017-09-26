package br.com.geoambiente.service.impl;

import br.com.geoambiente.domain.impl.Screenshot;
import br.com.geoambiente.repository.ScreenshotRepository;
import br.com.geoambiente.service.ScreenshotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;


@Slf4j
@Service("screenshotService")
public class ScreenshotServiceImpl implements ScreenshotService {
    private final static int LAST_MINUTES = 2;

    @Autowired
    private ScreenshotRepository repository;

    @Override
    public Screenshot addToFavorites(Screenshot screenshot) {
        log.info("> Adding Screenshot '{}' to favorites.", screenshot.getTitle());
        return this.repository.save(screenshot);
    }

    @Override
    public void removeFromFavorites(Screenshot screenshot) {
        log.info("> Removing Screenshot '{}' of author '{}' from favorites.", screenshot.getTitle(), screenshot.getAuthor().getName());
        this.repository.delete(screenshot);
    }

    @Override
    public List<Screenshot> findFavorites() {
        log.info("> Retrieving all Screenshots.");
        return this.repository.findAll();
    }

    @Override
    public List<Screenshot> findFavoritesByDate() {
        log.info("> Retrieving Screenshots by date of inclusion.");
        return this.repository.findAllOrderByAddedAtDesc();
    }

    @Override
    public List<Screenshot> findFavoritesRecentlyAdded() {
        log.info("> Retrieving Screenshots recently added.");
        ZonedDateTime limit = ZonedDateTime.now(ZoneOffset.UTC).minusMinutes(LAST_MINUTES);
        return this.repository.findByAddedAtGreaterThan(limit);
    }
}
