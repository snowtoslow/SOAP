package com.soap.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServicesConfig {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext){

        MessageDispatcherServlet messageDispatcherServlet =
                new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean(messageDispatcherServlet,"/ws/*");

    }

    @Bean(name = "courses")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema){

        DefaultWsdl11Definition definition =

                new DefaultWsdl11Definition();


        definition.setTargetNamespace("https://github.com/snowtoslow/SOAP");

        definition.setLocationUri("/ws/courses.wsdl");

        definition.setSchema(xsdSchema);

        definition.setPortTypeName("CoursePort");

        return definition;
    }


    @Bean
    public XsdSchema courseSchema(){

        return new SimpleXsdSchema(new ClassPathResource("/xsd-files/course-details.xsd"));

    }





}
