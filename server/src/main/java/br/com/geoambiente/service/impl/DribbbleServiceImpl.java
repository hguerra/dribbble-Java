package br.com.geoambiente.service.impl;

import br.com.geoambiente.engine.DribbbleAPI;
import br.com.geoambiente.domain.impl.Screenshot;
import br.com.geoambiente.service.DribbbleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("dribbbleService")
public class DribbbleServiceImpl implements DribbbleService {
	@Value("${app.dribbble.client_access_token}")
	private String CLIENT_ACCESS_TOKEN;

	@Value("${app.dribbble.per_page}")
	private int QUANTITY_PER_PAGE = 18;

	@Autowired
	private DribbbleAPI dribbbleAPI;

	@Override
	public List<Screenshot> getScreenshots(Integer page) {
		log.info("> Retrieving Screenshots");
		List<Screenshot> response;
		Call<List<Screenshot>> popular = this.dribbbleAPI.getPopular(page, QUANTITY_PER_PAGE, CLIENT_ACCESS_TOKEN);
		try {
			Response<List<Screenshot>> execute = popular.execute();
			if (execute.isSuccessful()) {
				response = execute.body();
			} else {
				throw new IOException(execute.message());
			}
		} catch (IOException e) {
			log.error(e.getMessage());
			response = new ArrayList<>();
		}

		return response;
	}
}
