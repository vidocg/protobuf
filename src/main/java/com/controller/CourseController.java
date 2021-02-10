package com.controller;

import com.protobuf.message.ProtoMessage;
import com.repository.CourseRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    private final CourseRepository courseRepo;

    public CourseController(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    @RequestMapping("/courses/{id}")
    ProtoMessage.Course customer(@PathVariable Integer id) {
        return courseRepo.getCourse(id);
    }
}
