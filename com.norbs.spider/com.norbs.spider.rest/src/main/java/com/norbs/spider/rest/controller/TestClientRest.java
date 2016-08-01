/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.norbs.spider.rest.controller;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Norbs
 */
public class TestClientRest {
    
    private static final String REST_URI = "http://localhost:8080/com.norbs.spider.web";
    
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("username", "norbs");
		map.add("password", "norbs");
                map.add("_csrf", "af00c6a2-a067-4835-8141-dd8b514bb790");
		
		Object object = restTemplate.postForObject("http://localhost:8080/com.norbs.spider.web/login", map, Object.class);
		System.out.println(object.toString());        
    }
}
