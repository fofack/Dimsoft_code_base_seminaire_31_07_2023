package com.proof.concept.bdd_integration_tests;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"junit", "html:target/bdd-reports/bdd-reports.html"},
    features = {"classpath:bdd"},
    glue = {"com.proof.concept.bdd_integration_tests"})
@DirtiesContext
public class RunBDDTests {
    
}