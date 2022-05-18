package com.example.adobe.domain.remote;

import kong.unirest.HttpResponse;

import java.util.concurrent.CompletableFuture;

public interface RemoteService {
    CompletableFuture<HttpResponse<ResponseApi>> fetchData();
}
