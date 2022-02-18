package runners;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.sourceforge.cobertura.reporting.Report;
import org.testng.annotations.AfterClass;


import java.io.File;
import java.lang.reflect.ReflectPermission;


/**
     * @author pbaliga002 on 06/08/18
     * @project compwcsecureterrain
     */

    @CucumberOptions(
            monochrome = true,
            features = "src/test/resources/Test.feature",
            plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
            glue = {"step_definitions"})



    public class API_implementation extends AbstractTestNGCucumberTests {

    }





