package br.com.geoambiente.service;


import br.com.geoambiente.domain.impl.Screenshot;

import java.util.List;

public interface DribbbleService {
    List<Screenshot> getScreenshots(Integer page);
}
