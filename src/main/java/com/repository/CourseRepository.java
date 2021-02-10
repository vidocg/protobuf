package com.repository;

import com.protobuf.message.ProtoMessage;

import java.util.Map;

public class CourseRepository {

    private Map<Integer, ProtoMessage.Course> courses;

    public CourseRepository(Map<Integer, ProtoMessage.Course> courses) {
        this.courses = courses;
    }

    public ProtoMessage.Course getCourse(int id) {
        return courses.get(id);
    }
}
