package br.com.geoambiente.service;

import br.com.geoambiente.domain.impl.Screenshot;

import java.util.List;

public interface ScreenshotService {
    Screenshot addToFavorites(Screenshot screenshot);

    void removeFromFavorites(Screenshot screenshot);

    void removeFromFavorites(Long id);

    List<Screenshot> findFavorites();

    List<Screenshot> findFavoritesByDate();

    List<Screenshot> findFavoritesRecentlyAdded();
}
