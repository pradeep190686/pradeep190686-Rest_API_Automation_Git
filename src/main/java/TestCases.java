import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
//import com.jayway.jsonpath.internal.filter.ValueNodes;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.scripts.JS;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.mozilla.javascript.json.JsonParser;
//import org.testng.Assert;
//import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;
//import com.jayway.jsonpath.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.JMock1Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;



public class TestCases {




    @Test(priority=0)
    public void getallvideogames2() {


        RequestSpecification request;

        Response res =
                given().contentType("application/json").
                        when().get("http://localhost:8081/app/videogames")
                        .then()
                        .log().body().statusCode(200)
                        .extract().response();
        String finalresp = res.asString();
        System.out.println("Json oouput ### "+ finalresp);


        try {
            JSONObject json = XML.toJSONObject(res.toString());
            String jsonString = json.toString(4);
            System.out.println(jsonString);

        }catch (JSONException e) {
            System.out.println(e.toString());
        }
        assertThat(finalresp, matchesJsonSchemaInClasspath("Test.json"));

    }


//POST CALLS


    @Test(priority = 1)

    public void postcalls() {


        HashMap data = new HashMap();
        data.put("id", 102);
        data.put("name", "Spider-Man");
        data.put("releaseDate", "2021-05-25T05:49:19.515Z");
        data.put("reviewScore", "5");
        data.put("category", "Adventure");
        data.put("rating", "Universal");


        HashMap hea=new HashMap();
                hea.put("a",1 );
                hea.put("b", 2);



        Response re =

                given().contentType("application/json")
                        .headers(hea)
                        .body(data)
                        .when()
                        .post("http://localhost:8081/app/videogames")
                        .then()
                        .statusCode(200)
                        //.log().body()
                        .log().all()
                        .extract().response();

        String test = re.asString();
        System.out.println(test);
        Assert.assertEquals(test.contains("Record Added Successfully"), true);


    }

    @Test(priority = 2)

    public void get_id() {

        String re =

                given()
                        .when()
                        .get("http://localhost:8081/app/videogames/102")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response().thenReturn().toString();


    }

    @Test(priority = 3)

    public void get_id1() {

        Response re =

                given()
                        .when()
                        .get("http://localhost:8081/app/videogames/102")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();
      //String xyz=  from(re.asString()).get("videoGames.id");
        //XmlPath xml=new XmlPath(re.asString());
       // String categories = with(xml).get("videoGame.id");


       // String cate1=with(re).get("videoGame.id");
       // System.out.println("carrom"+xyz);

       String stringResponse = re.asString();
        XmlPath xmlPath = new XmlPath(stringResponse);
        String status = xmlPath.get("videoGame.id");
        System.out.println("carrom"+status);
    }





//Put calls

    @Test(priority=5)
    public void putcalls() {
        HashMap data = new HashMap();
        data.put("id", 102);
        data.put("name", "Amazing-Spider-Man");
        data.put("releaseDate", "2021-05-25T05:49:19.515Z");
        data.put("reviewScore", "5");
        data.put("category", "Adventure");
        data.put("rating", "Universal");

        Response re =

                given().contentType("application/json")
                        .body(data)
                        .when()
                        .put("http://localhost:8081/app/videogames/102")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        String test = re.asString();
        System.out.println(test);


    }

    //DELETE CALLS

    @Test(priority=6)
    public void deletecalls()
    {
        Response re_1=

         given()
         .when()
          .delete("http://localhost:8081/app/videogames/102")
          .then()
          .statusCode(200)
                 .extract().response();


          String re2=re_1.asString();
          Assert.assertEquals(re2.contains("Record Deleted Successfully"), true);


    }




   @Test(priority= 7)
   public void api_jmeter_get_newenv1()
   {
       HashMap header=new HashMap();
       header.put("Ocp-Apim-Subscription-Key", "c45756b2e3af42db990cb0b0b62b0d3c");
       header.put("Proxy-Authorization", "Basic VVNfYWR2X0NsYWltc09wdGltaXplcl9zMDAxOlExdEFleWREMXRsMTExWDF6dDEx");
       header.put("Content-Type", "application/json");
       header.put("Authorization", "Basic VVNfYWR2X0NsYWltc09wdGltaXplcl9zMDAxOlExdEFleWREMXRsMTExWDF6dDEx");
       header.put("Accept","*/*");
       header.put("Cache-Control", "no-cache");
       header.put("Host", "gif-apim-sandbox.glblint.pwcinternal.com");
       header.put("Accept-Encoding", "gzip, deflate, br");
       header.put("Connection","keep-alive");

       Response Resp=

       given()
               .headers(header)
               .when()
               .get("https://gif-apim-sandbox.glblint.pwcinternal.com/claims-optimizer-qa/claims")
               .then()
               .statusCode(200)
               .extract().response();
       System.out.println("Response is "+ Resp) ;






   }

    @Test(priority=8)
    public void api_jmeter_get_oldenv()
    {
        HashMap header=new HashMap();
        header.put("Accept-Language", "en-US,en;q=0.9");
        header.put("accept", "application/json");
        header.put("Sec-Fetch-Dest","empty");
        header.put("Sec-Fetch-Mode","cors");
        header.put("Sec-Fetch-Site","same-origin");
        header.put("Cache-Control", "no-cache");
        header.put("Host", "adv-dgl-co-wap-qa-001.azurewebsites.net");
        header.put("Accept-Encoding", "gzip, deflate, br");
        header.put("Connection","keep-alive");

        Response Resp=

                given()
                        .headers(header)
                        .when()
                        .get("https://adv-dgl-co-wap-qa-001.azurewebsites.net/claims")
                        .then()
                        .statusCode(200)
                        .extract().response();

        String restring=Resp.asString();
        JsonPath jsp=new JsonPath(restring);
        //System.out.println("In String format"+restring);
        //JSONArray json = new JSONArray(restring);
        //JSONObject json1 = new JSONObject(restring);// Convert text to object
        System.out.println("JSON Value as String"+jsp.prettyPrint()); // Print it with specified indentation
        String pain=jsp.prettyPrint();

        //JsonParser parser=new JSONParser(restring);
        //JSONObject json = (JSONObject)parser.parseValue(restring);
       // JSONObject jsonObj = new JSONObject(restring);
        String value = jsp.getString("updatedAt");
        System.out.println("Json value is"+ value);
        String returendName = from(pain).getString("$..documents[0]");
        System.out.println("Testing123 "+returendName);

//        String JsonpathExpression="$..documents[0].fieldsUpdated[?(@.name=='Diagnosis 8')].date";
//        ValueNodes.JsonNode jsonNode1= com.jayway.jsonpath.JsonPath.parse(restring).read(JsonpathExpression, ValueNodes.JsonNode.class);
//        System.out.println("Value Returned is "+jsonNode1.toString());



        //Enable Pretty print with Jackson.databind in pombkp.xml
        //ObjectMapper mapper = new ObjectMapper(json);
        //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
       try {
           //ObjectMapper mapper = new ObjectMapper();
           //mapper.enable(SerializationFeature.INDENT_OUTPUT);
           //mapper.writeValueAsString(json);}
          // ObjectMapper mapper = new ObjectMapper();
           //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json1));

       }
       catch (Exception e)
       {
           System.out.println("Issue found");
       }

        //System.out.println("Value of new json  is "+json);

        //System.out.println("Json Response is"+ Resp);
        //System.out.println("Response is "+ restring);
    }



    @Test(priority=9)
    public void getallvideogames2_extractstring() {


        RequestSpecification request;

        Response res =
                given().
                        when().get("http://localhost:8081/app/videogames/1")
                        .then()
                       .log().body().statusCode(200)
                       .extract().response();



    }

 //JSON Schema
    @Test(priority=10)
    public void api_jmeter_get_newenv_noauth() {
        HashMap header = new HashMap();
        header.put("Accept", "application/json");
        header.put("Cache-Control", "no-cache");
        header.put("Host", "adv-dgl-co-wap-qa-001.azurewebsites.net");
        header.put("Accept-Encoding", "gzip, deflate, br");
        header.put("Connection", "keep-alive");
        header.put("Accept-Language", "en-US,en;q=0.9");
        header.put("Pragma","no-cache");
        header.put("Sec-Fetch-Dest", "empty");
        header.put("Sec-Fetch-Mode","cors");
        header.put("Sec-Fetch-Site","same-origin");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");

        Response Resp =

                given()
                        .headers(header)
                        .when()
                        .get("https://adv-dgl-co-wap-qa-001.azurewebsites.net/claims")
                        .then()
                        .statusCode(200)
                        .extract().response();
        System.out.println("Response is " + Resp);

        String xyz = Resp.asString();
        assertThat(xyz, matchesJsonSchemaInClasspath("Test.json"));

    }

}