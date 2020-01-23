package com.example.restservice;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;

@RestController
public class CobaTimeout {

    @GetMapping("/restTemplate")
    public String restTemplate(){

        String response = "";

        try{
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(5000); // milisecond, 5 detik
            requestFactory.setReadTimeout(5000); // milisecond, 5 detik

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(requestFactory);
            response = restTemplate.getForObject("http://localhost:8080/greeting", String.class);
        } catch (RestClientException e){
            System.out.println("### catch by Rest Client Exception");
            e.printStackTrace();

        } catch (Exception e){
            System.out.println("### catch by General Exception");
            e.printStackTrace();
        }


        return response;
    }

    @GetMapping("/unirest")
    public String unirest(){

        HttpResponse<String> response = null;

        try {
            Unirest.setTimeouts(5000, 5000); // milisecond, 5 detik
            response = Unirest.get("http://localhost:8080/greeting").asString();

        } catch (UnirestException e) {
            e.printStackTrace();

        } catch (Exception e){
            System.out.println("### catch by General Exception");
            e.printStackTrace();
        }

        return response.getBody();
    }

}
