package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/Cucumber", glue = "Cucumber.stepdefination", monochrome = true, plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
