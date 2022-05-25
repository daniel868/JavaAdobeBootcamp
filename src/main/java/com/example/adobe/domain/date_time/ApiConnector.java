package com.example.adobe.domain.date_time;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ApiConnector {
    public String baseUrl = "https://airlabs.co/api/v9/schedules?";
}
