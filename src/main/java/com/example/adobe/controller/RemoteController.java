package com.example.adobe.controller;

import com.example.adobe.domain.date_time.DateTimeService;
import com.example.adobe.domain.date_time.DateTimeResponse;
import com.example.adobe.domain.date_time.DateTimeDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/remote")
@RestController
public class RemoteController {
    private final DateTimeService remoteClient;

    public RemoteController(DateTimeService remoteClient) {
        this.remoteClient = remoteClient;
    }


    @GetMapping("/do-work")
    public String doSomeWork() throws InterruptedException {
        Thread.sleep(10000);
        return "do-some-work";
    }

    @GetMapping("/get-pojo")
    public DateTimeDetails getResponsePojo() throws Exception {

        DateTimeResponse responseApi = remoteClient.getRemoteDateTimeFlight("CDG", "FCO")
                .get()
                .getBody();

        return responseApi.getResponse()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find a flight"));

    }

}
