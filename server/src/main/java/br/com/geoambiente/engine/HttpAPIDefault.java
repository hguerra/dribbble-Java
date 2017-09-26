package br.com.geoambiente.engine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

@Configuration("httpAPI")
public class HttpAPIDefault implements HttpAPI {
    @Value("${app.dribbble.url}")
    private String DRIBBBLE_API;

    @Override
    @Bean
    public DribbbleAPI dribbble() {
        Retrofit retrofit = this.retrofit(DRIBBBLE_API);
        return retrofit.create(DribbbleAPI.class);
    }
}
