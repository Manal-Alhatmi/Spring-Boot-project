package com.codeline.ccsb;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CourseController {
    private Map<Integer, String> courses = new HashMap<>();
    private int idCounter = 1;

    @GetMapping("fillData")
    public String addSampleCourses() {
        createCourse("Mathematics");
        createCourse("Physics");
        createCourse("Chemistry");
        createCourse("Computer Science");
        createCourse("Biology");
        createCourse("History");
        createCourse("English");
        createCourse("Economics");
        createCourse("Architecture");
        createCourse("Art");
        return "Completed";
    }

    @PostMapping("create")
    public String createCourse(@RequestParam String name) {
        courses.put(idCounter, name);
        return "Course created with ID: " + idCounter++;
    }

    @GetMapping("getAll")
    public Map<Integer, String> getAllCourses() {
        return courses;
    }

    @GetMapping("getById")
    public String getCourseById(@RequestParam int id) {
        return courses.getOrDefault(id, "Course not found");
    }

    @PutMapping("update")
    public String updateCourse(@RequestParam int id, @RequestParam String name) {
        if (courses.containsKey(id)) {
            courses.put(id, name);
            return "Course updated successfully";
        }
        return "Course not found";
    }

    @DeleteMapping("delete/{id}")
    public String deleteCourse(@PathVariable int id) {
        if (courses.remove(id) != null) {
            return "Course deleted successfully";
        }
        return "Course not found";
    }
}
