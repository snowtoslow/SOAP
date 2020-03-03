
package com.soap.config;



import com.soap.bean.Course;
import com.soap.services.CourseDetailsService;
import https.github_com.snowtoslow.soap.CourseDetails;
import https.github_com.snowtoslow.soap.DeleteCourseDetailsRequest;
import https.github_com.snowtoslow.soap.DeleteCourseDetailsResponse;
import https.github_com.snowtoslow.soap.GetAllCourseDetailsRequest;
import https.github_com.snowtoslow.soap.GetAllCourseDetailsResponse;
import https.github_com.snowtoslow.soap.GetCourseDetailsRequest;
import https.github_com.snowtoslow.soap.GetCourseDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import java.util.List;


@Endpoint
public class EndPoints {

    @Autowired
    CourseDetailsService service;

    private CourseDetails mapCourse(Course course){

        CourseDetails courseDetails = new CourseDetails();

        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());

        return courseDetails;

    }

    private GetCourseDetailsResponse mapCourseDetails(Course course){

        GetCourseDetailsResponse response = new GetCourseDetailsResponse();

        response.setCourseDetails(mapCourse(course));

        return response;
    }

    private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses){

        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();

        for (Course course:
             courses) {
            CourseDetails courseDetails = mapCourse(course);
            response.getCourseDetails().add(courseDetails);
        }

        return response;
    }








    @PayloadRoot(namespace = "https://github.com/snowtoslow/SOAP",localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request){

        Course course = service.findById(request.getId());


        return mapCourseDetails(course);

    }


    @PayloadRoot(namespace = "https://github.com/snowtoslow/SOAP",localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request){

        List<Course> courses = service.findAll();

        return mapAllCourseDetails(courses);

    }

    @PayloadRoot(namespace = "https://github.com/snowtoslow/SOAP",localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse processCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request){

        String status = service.deleteById(request.getId());

        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();

        response.setStatus(status);


        return response;

    }



}

