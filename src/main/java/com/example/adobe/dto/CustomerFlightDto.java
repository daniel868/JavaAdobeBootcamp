package com.example.adobe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerFlightDto {
    private final String clientId;
    private final String fromLocation;
    private final String toLocation;
}
