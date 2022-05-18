package com.example.adobe.controller;

import com.example.adobe.domain.remote.RemoteService;
import com.example.adobe.domain.remote.ResponseApi;
import com.example.adobe.domain.remote.ResponseDetails;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/remote")
@RestController
public class RemoteController {
    private final RemoteService remoteClient;

    public RemoteController(RemoteService remoteClient) {
        this.remoteClient = remoteClient;
    }


    @GetMapping("/do-work")
    public String doSomeWork() throws InterruptedException {
        Thread.sleep(10000);
        return "do-some-work";
    }

    @GetMapping("/get-pojo")
    public ResponseDetails getResponsePojo() throws Exception {

        ResponseApi responseApi = remoteClient.fetchData()
                .get()
                .getBody();

        return responseApi.getResponse()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find a flight"));

    }

}
