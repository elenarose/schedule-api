package com.nuwit.schedule.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetTime;
import java.util.List;

public class Course {
    private final String name;
    private final OffsetTime startTime;
    private final OffsetTime endTime;
    private final List<Days> daysOfTheWeek;

    public Course(String name, OffsetTime startTime, OffsetTime endTime, List<Days> daysOfTheWeek) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.daysOfTheWeek = daysOfTheWeek;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    @JsonFormat(pattern = "HH:mm")
    public OffsetTime getStartTime() {
        return startTime;
    }

    @JsonProperty
    @JsonFormat(pattern = "HH:mm")
    public OffsetTime getEndTime() {
        return endTime;
    }

    @JsonProperty
    public List<Days> getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

}
