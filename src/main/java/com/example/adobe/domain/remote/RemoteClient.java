package com.example.adobe.domain.remote;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RemoteClient {

    public HttpResponse<ResponseApi> fetchPlane() {
        CompletableFuture<HttpResponse<ResponseApi>> response = Unirest.get("https://airlabs.co/api/v9/flight?flight_iata=AA6")
                .queryString("api_key", "16b2a750-2d0d-42aa-b776-5d91ff73a7d5")
                .asObjectAsync(ResponseApi.class);

        try {
            return response.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
