package step_definitions;

import Header_Body_Data.Headers_data;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;


public class Implementation {

    public String csrf_token_temp=null;
    public static String csrf_token;
    public Cookies cookies;
    public Cookies cookies2;
    public Cookies test;
    public List<Cookie>lst;
    public Map<String,String> mp;
    public String lst1;
    public String test2;
    private Response response;
   // public Properties properties;
    private static  String application;
    public Scenario scenario;
    public static String url_final_path;
    public String url;
   // public boolean flag=true;




    @Given("^I login to TR ([^\"]*) with ([^\"]*) and ([^\"]*)$")
    public void i_login_to_TR_application_with_username_and__password(String application_path, String username,String password) throws Exception
    {
        System.out.println("application is"+application_path+password+username);
    }

    @Before
    // @Given("I access the application to procure temporary token for the application ([^\"]*) with ([^\"]*) and ([^\"]*)$")
    //public void I_access_the_application_to_procure_temporary_token_for_the_application(String application, String username,String password) throws Exception

    public void initialize(Scenario scenario) throws Exception
    {


          if(Headers_data.single_exec()==1)
         {
            Headers_data.setapp_env();
            System.out.println("Benzema");
        }
        url = Headers_data.url_get("app_env");
        String application=Headers_data.url_get("app_env")+"/account/login";
        String username="qa-admin-1";
        String password="Notasecret1!";
        RestAssured.useRelaxedHTTPSValidation();
        response=
                given().headers(Headers_data.header_first_token())
                        .when()
                        .get(application)
                        .then()
                        .statusCode(200)
                        .extract().response();
        String Response_String=response.asString();
        cookies=response.getDetailedCookies();
        String regular_expression="window.__env.csrfTokenTemp = \"(.*?)\"";
        String dynamic_variable=Headers_data.Regex_Capture(Response_String,regular_expression,1);
        System.out.println("dynamic variable is"+dynamic_variable);
        if(dynamic_variable.toLowerCase().equalsIgnoreCase("not found"))
        {
            Assert.assertTrue(false, "Temp Token not found");
        }
        csrf_token_temp=dynamic_variable;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        response=
                given().urlEncodingEnabled(false)
                        .cookies(cookies)
                        .headers(Headers_data.header_csrf_token()).redirects().follow(true).
                        body("csrfmiddlewaretoken="+csrf_token_temp+"&username=qa-admin-1&password=Notasecret1%21").
                        when().
                        post(Headers_data.url_get("app_env")+"/login/?next=/")
                        .then()
                        .log().all()
                        .extract().response();
        String Response_final_String=response.asString();
        cookies2=response.getDetailedCookies();
        System.out.println("value of list1 is"+lst1);
        System.out.println("Shared cookies is "+cookies2);
        System.out.println("bubbles is"+response.getDetailedCookie("sessionid"));
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        response=
                given().urlEncodingEnabled(false)
                        //.cookies(cookies2).redirects().follow(true).
                        .cookies(cookies2).redirects().follow(true)
                        //  headers(Headers_data.header_csrf_token()).
                        .header("Location","/")
                        .when().
                        get(Headers_data.url_get("app_env")+"/")
                        .then()
                        .log().all()
                        .extract().response();
        String Response_final_2=response.asString();
        System.out.println("Response vodka"+Response_final_2);
        String regular_expression_1="window.__env.csrfToken = \"(.*?)\";";
        String dynamic_variable_final=Headers_data.Regex_Capture(Response_final_2,regular_expression_1,1);
        System.out.println("dynamic variable final is"+dynamic_variable_final);
        System.out.println("Final Response is"+Response_final_String);
        if(dynamic_variable_final.toLowerCase().equalsIgnoreCase("not found"))
        {
            Assert.assertTrue(false, "CSRF Token not found");
        }
        csrf_token=dynamic_variable_final;
        System.out.println("csrf_token is cech"+ csrf_token);




    }



    @Then("^I pass get request witn ([^\"]*) and ([^\"]*) and verify response with ([^\"]*)$")
    public void I_pass_get_request_witn_(String url_path,String query_parameters,String SchemaPath) throws Exception {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        response =
                given().urlEncodingEnabled(false)
                        .cookies(cookies2)
                        .redirects().follow(true).
                        headers(Headers_data.header_csrf_token())
                        .when().
                        get(Headers_data.url_get("app_env") + url_path)
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .log().ifError()
                        .extract().response();
        String Response_final_2 = response.asString();
        System.out.println("lampard" + Response_final_2);
        if (!SchemaPath.equalsIgnoreCase("")) {
            System.out.println("Floyd");
            assertThat(Response_final_2, matchesJsonSchemaInClasspath(SchemaPath));
        }


    }


    @Then("^I pass post request with ([^\"]*),([^\"]*),([^\"]*) and verify response with ([^\"]*)$")
            public void I_pass_post_request_with_(String url_path,String body_data,String query_parameters,String SchemaPath) throws Exception
    {
         url_final_path=url_path;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        response =
                given().urlEncodingEnabled(false)
                        .cookies(cookies2)
                        .redirects().follow(true)
                        .headers(Headers_data.post_Header(csrf_token))
                        .body(Headers_data.sort_body_data(body_data,url_path))
                        .when().
                        post(Headers_data.url_get("app_env")+url_path)
                        .then().log().all()
                        .assertThat()
                        .statusCode(201)
                        .extract().response();


        String Response_final_2=response.asString();
        if (!SchemaPath.equalsIgnoreCase("")) {
            System.out.println("Floyd");
            assertThat(Response_final_2, matchesJsonSchemaInClasspath(SchemaPath));
        }

    }


    @After
    public void logout(Scenario scenario) throws Exception
    {


        response =
                given().urlEncodingEnabled(false)
                        .cookies(cookies2)
                        .redirects().follow(true).
                        headers(Headers_data.header_csrf_token())
                        .when().
                        get(Headers_data.url_get("app_env") + "/logout")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        response = given().urlEncodingEnabled(false)
                .cookies(cookies)
                .redirects().follow(true).
                        headers("Location", "/")
                .when().
                        get(Headers_data.url_get("app_env"))
                .then()
                .assertThat()
                .statusCode(200)
                .log().all()
                .extract().response();

        response = given().urlEncodingEnabled(false)
                .cookies(cookies2)
                .redirects().follow(true).
                        headers("Location", "/account/login/?next=/")
                .when().
                        get(Headers_data.url_get("app_env") + "/account/login/?next=/")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all()
                .extract().response();

        String Response_logout_s = response.asString();

        System.out.println("Terry" + Response_logout_s);


    }











}

