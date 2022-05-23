package com.example.adobe.domain.date_time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class RemoteClientTest {
    private com.example.adobe.domain.date_time.DateTimeClient remoteClient;

    @BeforeEach
    void setUp() {
        remoteClient = new com.example.adobe.domain.date_time.DateTimeClient();
    }

    @Test
    void shouldFetchData() throws ExecutionException, InterruptedException {

    }

    @Test
    void fetchWithPlane() throws ExecutionException, InterruptedException {

    }
}