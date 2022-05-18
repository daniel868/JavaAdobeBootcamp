package com.example.adobe.domain.remote;

import kong.unirest.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class RemoteClientTest {
    private RemoteClient remoteClient;

    @BeforeEach
    void setUp() {
        remoteClient = new RemoteClient();
    }

    @Test
    void shouldFetchData() throws ExecutionException, InterruptedException {

    }

    @Test
    void fetchWithPlane() throws ExecutionException, InterruptedException {

    }
}