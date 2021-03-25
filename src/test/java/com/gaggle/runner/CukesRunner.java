package com.gaggle.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-report.html",
                "json:target/cucumber.json"},
        features = "src/test/resources/features",
        glue="com/gaggle/step_definitions"

)


public class CukesRunner {


}
