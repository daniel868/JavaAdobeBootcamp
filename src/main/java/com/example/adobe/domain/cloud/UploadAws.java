package com.example.adobe.domain.cloud;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.services.s3.transfer.model.UploadResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Data
public class UploadAws {
    private final AmazonS3 amazonS3;

    @Async
    public CompletableFuture<UploadResult> insertIntoAmazonS3() {
        String bucketName = "adobe-project";
        String keyName = "photoFolder/some-key";
        String filePath = "C:\\Users\\danit\\OneDrive\\Pictures\\samples\\sample_9.png";

        TransferManager transferManager = TransferManagerBuilder.standard()
                .withS3Client(amazonS3)
                .build();

        try {
            Upload upload = transferManager.upload(bucketName, keyName, new File(filePath));

            UploadResult uploadResult = upload.waitForUploadResult();


            return CompletableFuture.completedFuture(uploadResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
