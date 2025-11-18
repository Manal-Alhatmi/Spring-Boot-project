package com.codeline.ccsb;

import com.codeline.ccsb.entities.Course;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class CourseController {

    private List<Course> courseList = new ArrayList<>();
    private int idCounter = 1;

    @PostMapping("createCourse")
    public String createCourse(@RequestBody Course requestObj) {

        requestObj.setId(idCounter);
        requestObj.setCreatedDate(new Date());
        requestObj.setIsActive(true);

        courseList.add(requestObj);

        return "Course created with ID: " + idCounter++;
    }

    @GetMapping("getAllCourses")
    public List<Course> getAllCourses() {
        List<Course> responseList = new ArrayList<>();

        for (Course c : courseList) {
            if (c.getIsActive()) {
                responseList.add(c);
            }
        }
        return responseList;
    }

    @GetMapping("getCourseById")
    public Course getCourseById(@RequestParam int id) {
        for (Course c : courseList) {
            if (c.getId() == id && c.getIsActive()) {
                return c;
            }
        }
        return Course.builder().build();
    }

    @PutMapping("updateCourse")
    public String updateCourse(@RequestBody Course updateObjFromUser) {

        if (updateObjFromUser != null && updateObjFromUser.getId() != null) {

            Course existingCourseToUpdate = findCourseById(updateObjFromUser.getId());

            courseList.remove(existingCourseToUpdate);

            existingCourseToUpdate.setName(updateObjFromUser.getName());
            existingCourseToUpdate.setDescription(updateObjFromUser.getDescription());
            existingCourseToUpdate.setUpdatedDate(new Date());

            courseList.add(existingCourseToUpdate);

            return "Course updated successfully";
        }

        return "Course not found";
    }

    @DeleteMapping("deleteCourse/{id}")
    public String deleteCourse(@PathVariable int id) {

        Course existingCourseToUpdate = findCourseById(id);

        if (existingCourseToUpdate.getId() > 0) {

            courseList.remove(existingCourseToUpdate);

            existingCourseToUpdate.setIsActive(false);
            existingCourseToUpdate.setUpdatedDate(new Date());

            courseList.add(existingCourseToUpdate);

            return "Course deleted successfully";
        }

        return "Invalid id";
    }

    public Course findCourseById(int id) {
        for (Course c : courseList) {
            if (c.getId() == id) {
                return c;
            }
        }
        return Course.builder().id(-1).build();
    }
}
