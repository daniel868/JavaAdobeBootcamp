package com.example.adobe.domain;

import com.amazonaws.services.s3.transfer.model.UploadResult;
import com.example.adobe.domain.cloud.UploadAws;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UploadAwsTest {

    @Autowired
    private UploadAws uploadAws;

    @Test
    public void shouldUploadToAws() throws ExecutionException, InterruptedException {
        CompletableFuture<UploadResult> uploadResultCompletableFuture = uploadAws.insertIntoAmazonS3();
        String key = uploadResultCompletableFuture.get()
                .getKey();
        assertThat(key).isNotEqualTo("");
    }
}