package com.example.apiproject.enums;

import lombok.Getter;

@Getter
public enum TimeRange {
    MORNING("05:00", "09:00"),
    DAYTIME("09:00", "16:00"),
    EVENING("16:00", "24:00"),
    WEEKEND("05:00", "24:00");

    private String startTime;
    private String endTime;

    TimeRange(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
