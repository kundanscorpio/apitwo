package com.britishgas.apitwo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.britishgas.apitwo.model.Response;

/**
 * Created by Kundan Sharma.
 */
@RestController
@RequestMapping("/")
public class ApitwoController {
    private static final Logger log = LoggerFactory.getLogger(ApitwoController.class);

    @Value("${anotherapiURL}")
    private String anotherapiURL;
    private RestTemplate restTemplate;

    public ApitwoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/callAnotherApi", method = RequestMethod.GET)
    public Response callAnotherApi() {
        Response response = restTemplate.getForObject(anotherapiURL, Response.class);
        response.addResponse("Initial call received by apitwo, other api is called and returning now");
        log.info(response.toString());
        return response;
    }

    @RequestMapping(value = "/callByAnotherApi", method = RequestMethod.GET)
    public Response callByAnotherApi() {
        Response response = new Response();
        response.addResponse("From apitwo: I got your call.");
        log.info(response.toString());
        return response;
    }
}