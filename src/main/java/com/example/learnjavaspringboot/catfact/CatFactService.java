package com.example.learnjavaspringboot.catfact;

import com.example.learnjavaspringboot.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class CatFactService implements Query<Integer, CatFactDTO> {

    private final RestTemplate restTemplate;

    private final String url = "https://catfact.ninja/fact";

    private final String MAX_LENGTH = "max_length";

    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Integer input) {
//        Setup url with query string parameter
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam(MAX_LENGTH, input).build().toUri();

//        Headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<CatFactResponse> response = restTemplate.exchange(uri, HttpMethod.GET, entity, CatFactResponse.class);

            CatFactDTO catFactDTO = new CatFactDTO(response.getBody().getFact());

            return ResponseEntity.ok(catFactDTO);
        } catch (Exception e) {
            throw new RuntimeException("Cat fact API is down");
        }
    }
}