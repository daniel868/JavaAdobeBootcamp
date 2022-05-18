package com.example.adobe.domain.remote;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class RemoteClient implements RemoteService {

    @Override
    @Async
    public CompletableFuture<HttpResponse<ResponseApi>> fetchData() {
        return Unirest.get("https://airlabs.co/api/v9/schedules?dep_iata=CDG&arr_iata=FCO")
                .queryString("api_key", "16b2a750-2d0d-42aa-b776-5d91ff73a7d5")
                .asObjectAsync(ResponseApi.class);
    }

}
