package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

@RestController
public class HelloController {

    public String getRequest(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @GetMapping("/")
    public String getWelcomeText() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/api/numbers")
    public String number(@RequestParam String number) throws IOException, InterruptedException {
        return getRequest("http://numbersapi.com/" + number + "/math");
    }

    @GetMapping("/api/cat")
    public String getCatFact() throws IOException, InterruptedException {
        return getRequest("https://catfact.ninja/fact");
    }

    @GetMapping("/api/bitcoin")
    public String getCurrentBitcoinPrice() throws IOException, InterruptedException {
        return getRequest("https://api.coindesk.com/v1/bpi/currentprice.json");
    }

    @GetMapping("/api/yahoo")
    public String getYahoo() throws IOException, InterruptedException {
        return getRequest("https://uk.yahoo.com/?p=us");
    }
}
