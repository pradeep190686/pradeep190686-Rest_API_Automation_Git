package step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;


public class CommonDefinitions {



    @Given("^The Test Data is Ready$")
    public void The_Test_Data_is_Ready() throws Throwable {

        System.out.println("Carrom to all");

    }

    @When("^The Test Data is Ready2$")
    public void The_Test_Data_is_Ready2() throws Throwable {

        System.out.println("Carrom to all");

    }

    @Given("^I am verifying DataTables$")
    public void I_am_verifying_DataTables(DataTable dt)
    {

//        System.out.println(scenario.getId());
        List<Map<String,String>> lst=dt.asMaps(String.class, String.class);
        System.out.println(lst.get(0).get("firstname"));
        System.out.println(lst.get(1).get("Year"));
        System.out.println("drogba"+lst.subList(1, 3));
        Map<String,String> mp=lst.get(0);
        System.out.println("Lampard"+mp);
        for(Map<String,String> mp1:lst)
        {
            System.out.println(mp1);
        }



        List<List<String>> lst2=dt.asLists();
        System.out.println("list is"+lst2.toString());
        System.out.println("list is"+lst2.get(1).get(0));
        for(List<String> lst3:lst2)
        {
            System.out.println(lst3);
        }




    }

}
