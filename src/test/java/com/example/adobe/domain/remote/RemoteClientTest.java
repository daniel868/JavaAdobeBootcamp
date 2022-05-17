package com.example.adobe.domain.remote;

import kong.unirest.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class RemoteClientTest {
    private RemoteClient remoteClient;

    @BeforeEach
    void setUp() {
        remoteClient = new RemoteClient();
    }

    @Test
    void shouldFetchData() {
        HttpResponse<ResponseApi> expected = remoteClient.fetchPlane();
        assertThat(expected).isNotNull();
    }
}
