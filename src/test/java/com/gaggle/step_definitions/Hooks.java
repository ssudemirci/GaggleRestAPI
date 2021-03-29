package com.gaggle.step_definitions;

import com.gaggle.utillities.ConfigurationReader;
import io.cucumber.java.Before;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.log4j.Logger;

import java.util.Properties;

public class Hooks {

    private  final static Logger logger=Logger.getLogger(Hooks.class);
    public Properties prop;
    public static RequestSpecification requestSpec;
    public ResponseSpecification responseSpec;

//    @Before
//    public void setup(){
//        logger.info(":::: Starting automation :::");
//        logger.info("Base URL : "+ ConfigurationReader.getProperty("baseURI"));
//        System.out.println(" Port type: "+ ConfigurationReader.getProperty("basePath"));
//
//    }

}
