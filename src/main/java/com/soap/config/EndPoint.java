
package com.soap.config;



import https.github_com.snowtoslow.soap.CourseDetails;
import https.github_com.snowtoslow.soap.GetCourseDetailsRequest;
import https.github_com.snowtoslow.soap.GetCourseDetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class EndPoint {

    @PayloadRoot(namespace = "https://github.com/snowtoslow/SOAP",localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request){

        GetCourseDetailsResponse response = new GetCourseDetailsResponse();

        CourseDetails courseDetails = new CourseDetails();

        courseDetails.setId(request.getId());
        courseDetails.setName("Soap course");
        courseDetails.setDescription("UDEMY");

        response.setCourseDetails(courseDetails);

        return response;

    }



}

