package com.example.adobe.domain.remote;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ResponseApi {
    private final List<ResponseDetails> response;
}
