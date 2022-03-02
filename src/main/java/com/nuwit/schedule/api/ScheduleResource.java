package com.nuwit.schedule.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Resource class with endpoints for student schedules
 */
@Path("/schedule")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScheduleResource {
    private final Map<String, Course> courses;
    private final Map<String, List<String>> schedules;

    /**
     * Constructor for ScheduleResource
     */
    public ScheduleResource() {
        this.courses = buildCourseList();
        this.schedules = buildSchedules();
    }

    /**
     * Helper method to build a list of courses
     * @return List of courses
     */
    private Map<String, Course> buildCourseList() {
        Course calculus = new Course("Calc 1", "MATH1234", OffsetTime.of(LocalTime.parse("08:00"), ZoneOffset.UTC),
                OffsetTime.of(LocalTime.parse("09:05"), ZoneOffset.UTC), List.of(Days.TUESDAY, Days.THURSDAY));

        Course ood = new Course("Object oriented design", "CS2000", OffsetTime.of(LocalTime.parse("10:00"), ZoneOffset.UTC),
                OffsetTime.of(LocalTime.parse("11:05"), ZoneOffset.UTC), List.of(Days.MONDAY, Days.TUESDAY, Days.THURSDAY));

        Course algo = new Course("Algorithms", "CS3000", OffsetTime.of(LocalTime.parse("01:30"), ZoneOffset.UTC),
                OffsetTime.of(LocalTime.parse("02:35"), ZoneOffset.UTC), List.of(Days.MONDAY, Days.WEDNESDAY, Days.FRIDAY));

        return Map.of(calculus.getCode(), calculus, ood.getCode(), ood, algo.getCode(), algo);
    }

    /**
     * Helper method to initialize the schedules map and prefill some data.
     * @return Map of student name to list of course codes
     */
    private Map<String, List<String>> buildSchedules() {
        Map<String, List<String>> initialSchedules = new HashMap<>();
        initialSchedules.put("Elena", List.of("MATH1234", "CS2000"));
        initialSchedules.put("Jess", List.of("CS2000", "CS3000"));
        initialSchedules.put("Ehlana", List.of("CS3000", "MATH1234"));
        return initialSchedules;
    }

    /**
     * Get a list of available courses
     * @return List of available courses
     */
    @GET
    @Path("courses")
    public Collection<Course> getCourses() {
        return courses.values();
    }

    @GET
    @Path("courses/student/{studentName}")
    public List<String> getCoursesForStudent(@PathParam("studentName") String studentName) {
        return this.schedules.get(studentName);
    }

    @POST
    @Path("courses/student/{studentName}")
    public void addStudentToCourse(@PathParam("studentName") String studentName,
                                   String courseCode) {
        List<String> allCourses = new ArrayList<>(this.schedules.get(studentName));
        allCourses.add(courseCode);
        this.schedules.put(studentName, allCourses);
    }

    @GET
    @Path("courses/{courseCode}/students")
    public List<String> getStudentsInCourse(@PathParam("courseCode") String courseCode) {
        List<String> students = new ArrayList<>();

        for (String student : this.schedules.keySet()) {
            List<String> courses = this.schedules.get(student);

            if (courses.contains(courseCode)) {
                students.add(student);
            }
        }

        return students;
    }

    @DELETE
    @Path("courses/{courseCode}/student/{studentName}")
    public void deleteStudentFromCourse(@PathParam("studentName") String studentName,
                                        @PathParam("courseCode") String courseCode) {
        List<String> allCourses = new ArrayList<>(this.schedules.get(studentName));
        allCourses.remove(courseCode);
        this.schedules.put(studentName, allCourses);
    }
}
