package br.com.geoambiente.controller;

import br.com.geoambiente.domain.impl.Screenshot;
import br.com.geoambiente.service.DribbbleService;
import br.com.geoambiente.service.ScreenshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class ScreenshotController {

    @Autowired
    private DribbbleService dribbbleService;

    @Autowired
    private ScreenshotService screenshotService;

    @RequestMapping(value = {"/shots/{page}"}, method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Screenshot> getPopular(@PathVariable Integer page) {
        return this.dribbbleService.getScreenshots(page);
    }

    @RequestMapping(value = "/favorites", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Screenshot> getFavorites() {
        return this.screenshotService.findFavorites();
    }


    @RequestMapping(value = "/favorites/date", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Screenshot> getFavoritesByDate() {
        return this.screenshotService.findFavoritesByDate();
    }


    @RequestMapping(value = "/favorites/recent", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Screenshot> getFavoritesRecentlyAdded() {
        return this.screenshotService.findFavoritesRecentlyAdded();
    }


    @RequestMapping(value = "/favorites", method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Screenshot addToFavorites(@Valid @RequestBody Screenshot screenshot) {
        return this.screenshotService.addToFavorites(screenshot);
    }

    @RequestMapping(value = "/favorites", method = DELETE, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity removeFromFavorites(@Valid @RequestBody Screenshot screenshot) {
        this.screenshotService.removeFromFavorites(screenshot);
        return new ResponseEntity(HttpStatus.OK);
    }
}
