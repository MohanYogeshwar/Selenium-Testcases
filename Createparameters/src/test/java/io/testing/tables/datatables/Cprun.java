package io.testing.tables.datatables;


import java.io.File;
import java.nio.file.FileSystems;
import io.testing.tables.datatables.Cpsd;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		monochrome = true, 
		features = {"Features\\Createparameters.feature"},
        glue = {"classpath:io.testing.tables.datatables"} )

public class Cprun extends AbstractTestNGCucumberTests {

private void path() {
    String featurePath = FileSystems.getDefault().getPath("Createparameters.feature").normalize().toAbsolutePath().toString();
    System.out.println(featurePath);
	}

}
