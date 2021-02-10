package com.config;

import com.protobuf.message.ProtoMessage;
import com.repository.CourseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Configuration
public class ProtoConfig {

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    public CourseRepository createTestCourses() {
        Map<Integer, ProtoMessage.Course> courses = new HashMap<>();
        ProtoMessage.Course course1 = ProtoMessage.Course.newBuilder()
                .setId(1)
                .setCourseName("Course One")
                .addAllStudent(createTestStudents())
                .build();
        ProtoMessage.Course course2 = ProtoMessage.Course.newBuilder()
                .setId(2)
                .setCourseName("Course TWO")
                .addAllStudent(new ArrayList<ProtoMessage.Student>())
                .build();
        courses.put(course1.getId(), course1);
        courses.put(course2.getId(), course2);
        return new CourseRepository(courses);
    }

    private List<ProtoMessage.Student> createTestStudents() {
        List<ProtoMessage.Student> students = new ArrayList<>();
        for(int i = 0; i<3; i++) {
            int randomOneToThousand = (int) ((Math.random() * (1000 - 1)) + 1);
            int phoneType = (int) ((Math.random() * (2 - 1)) + 1);
            ProtoMessage.Student.PhoneNumber phoneNumber = ProtoMessage.Student.PhoneNumber
                    .newBuilder()
                    .setNumber(randomOneToThousand + "")
                    .setTypeValue(phoneType)
                    .build();


            ProtoMessage.Student student = ProtoMessage.Student
                    .newBuilder()
                    .setId(i)
                    .addAllPhone(Collections.singletonList(phoneNumber))
                    .setEmail("Email_" + randomOneToThousand)
                    .setFirstName("FirstName_" + randomOneToThousand)
                    .setLastName("LastName_" + randomOneToThousand)
                    .build();
            students.add(student);
        }
        return students;
    }

    @Bean
    RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
        return new RestTemplate(Arrays.asList(hmc));
    }

}
