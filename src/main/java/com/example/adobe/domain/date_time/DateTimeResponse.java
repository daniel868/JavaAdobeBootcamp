package com.example.adobe.domain.date_time;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class DateTimeResponse {
    private final List<DateTimeDetails> response;
}
