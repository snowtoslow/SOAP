package com.soap.services;


import com.soap.bean.Course;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseDetailsService {

    private final String DELETED = "DELETED";
    private final String FAILURE = "FAILURE";

    private static ArrayList<Course> courses = new ArrayList<>();

    static {
        Course course1 =
                new Course(1,"Algorithms and data Structureas","Algorrithms");

        Course course2 =
                new Course(2,"LFPC","Formal Languages");

        Course course3 =
                new Course(3,"SOMMIP","OS");

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
    }


    public Course findById(int id){

        for (Course course:
             courses) {
            if (course.getId()==id){
                return course;
            }
        }
        return null;

    }


    public List<Course> findAll(){
        return courses;
    }


    public String deleteById(int id){
        for (Course course:
                courses) {
            if (course.getId()==id){
                courses.remove(course);
                return DELETED;
            }
        }
        return FAILURE;
    }








}
