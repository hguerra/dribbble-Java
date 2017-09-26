package br.com.geoambiente.controller;

import br.com.geoambiente.domain.impl.Screenshot;
import br.com.geoambiente.service.DribbbleService;
import br.com.geoambiente.service.ScreenshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ScreenshotController {

    @Autowired
    private DribbbleService dribbbleService;

    @Autowired
    private ScreenshotService screenshotService;

    @RequestMapping(value = "/shots", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Screenshot> getPopular() {
        return this.dribbbleService.getScreenshots(1);
    }

    @RequestMapping(value = "/favorites", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Screenshot> getFavorites() {
        return this.screenshotService.findFavorites();
    }
}
