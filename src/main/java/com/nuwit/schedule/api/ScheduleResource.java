package com.nuwit.schedule.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.List;

@Path("/schedule")
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleResource {

    public ScheduleResource() {}

    @GET
    public List<Course> getCourses() {
        Course course = new Course("Calc 1", OffsetTime.of(LocalTime.parse("08:00"), ZoneOffset.UTC),
                OffsetTime.of(LocalTime.parse("09:05"), ZoneOffset.UTC), List.of(Days.TUESDAY));
        return List.of(course);
    }

}
