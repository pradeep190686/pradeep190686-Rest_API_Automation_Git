package Header_Body_Data;
//import package step_defintions.Implementation;


import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Headers_data {

    public static HashMap<String,String> header_tokentemp;
    public static HashMap<String,String> header_csrftoken;
    public static HashMap<String,String> post_header;
    public static Properties prop;
    public static Properties properties;
    public static int i=0;



    public static HashMap<String,String> header_first_token()
    {

        header_tokentemp=new HashMap<String, String>();
        header_tokentemp.put("Accept-Language", "en-US,en;q=0.5");
        header_tokentemp.put("Upgrade-Insecure-Requests","1");
        header_tokentemp.put("Cache-Control", "Cache-Control");
        header_tokentemp.put("Accept-Encoding", "gzip, deflate, br");
        header_tokentemp.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0");
        header_tokentemp.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");

        return header_tokentemp;

    }


    public static String Regex_Capture(String Captured_Response,String Pattern_1,int groupid)
    {
        String y1=null;
        Pattern r=Pattern.compile(Pattern_1);
        Matcher m=r.matcher(Captured_Response);
        if(m.find()) {
            System.out.println("Found value "+m.group(groupid));
            y1=m.group(groupid);
        }

        else if(!m.find()) {
            y1="Not Found";
        }

        return y1;

    }

    public static HashMap<String,String> header_csrf_token() throws Exception
    {

        header_csrftoken=new HashMap<String, String>();
        header_csrftoken.put("Referer", Headers_data.url_get("app_env")+"/account/login/?next=/");
        header_csrftoken.put("Accept-Language", "en-US,en;q=0.5");
        header_csrftoken.put("Pragma", "no-cache");
        header_csrftoken.put("Connection", "keep-alive");
        header_csrftoken.put("Upgrade-Insecure-Requests","1");
        header_csrftoken.put("Content-Type", "application/x-www-form-urlencoded");
        header_csrftoken.put("Accept-Encoding", "gzip, deflate, br");
        header_csrftoken.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36");
        header_csrftoken.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        header_csrftoken.put("sec-ch-ua","Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"97\", \"Chromium\";v=\"97");
        header_csrftoken.put("sec-ch-ua-mobile","?0");
        header_csrftoken.put("sec-ch-ua-platform","Windows");
        header_csrftoken.put("Sec-Fetch-Dest" ,"document" );
        header_csrftoken.put("Sec-Fetch-Site","same-origin" );
        header_csrftoken.put("Sec-Fetch-Mode", "navigate");
        header_csrftoken.put("Sec-Fetch-User","?1");
        header_csrftoken.put("Host",Headers_data.no_http_url());
        header_csrftoken.put("Origin", Headers_data.url_get("app_env"));
        header_csrftoken.put("Cache-Control", "no-cache");



        return header_csrftoken;



    }

    public static String getSystemDataAndTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd_HHmmss");
        Date date = new Date();
        String systemDate = dateFormat.format(date);
        System.out.println("terry"+systemDate);
        return systemDate;
    }

    public static String sort_body_data(String key,String url_path1) throws Exception
    {
        prop=new Properties();
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\body_data.properties");
        prop.load(fis);
        String body_data_raw= prop.getProperty(key);

        if(!prop.getProperty("to_be_replaced_"+key).equalsIgnoreCase(""))
        {
           String to_be_replaced=prop.getProperty("to_be_replaced_"+key);
           String replacement= Headers_data.Replacement(to_be_replaced,url_path1);
              String final_body= body_data_raw.replaceAll(prop.getProperty("to_be_replaced_"+key),replacement);
               System.out.println("Neo_spin"+final_body);
               return final_body;

        }
        return body_data_raw;

    }

    public static String Replacement(String to_be_replaced,String url_path1)
    {
        String final_Replacement=null;

        if(url_path1.equalsIgnoreCase("/api/v0_1/comments"))
        {
             final_Replacement="Test_"+getSystemDataAndTime();


        }

        return final_Replacement;
    }


    public static HashMap<String, String> post_Header(String csrf) throws Exception {

        post_header=new HashMap<String, String>();
        post_header.put("Host",Headers_data.no_http_url());
        post_header.put("Connection", "keep-alive");
        post_header.put("Pragma", "no-cache");
        post_header.put("Cache-Control", "no-cache");
        post_header.put("Accept", "*/*");
        post_header.put("Origin", Headers_data.url_get("app_env"));
        post_header.put("X-CSRFToken", csrf);
        post_header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36");
        post_header.put("Content-Type", "application/json");
        post_header.put("Upgrade-Insecure-Requests","1");
        post_header.put("Sec-Fetch-Site","same-origin");
        post_header.put("Sec-Fetch-Mode","cors");
        post_header.put("Referer", Headers_data.url_get("app_env")+"/");
        post_header.put("Accept-Encoding", "gzip, deflate, br");
        post_header.put("Accept-Language", "en-US,en;q=0.5");
//
//        post_header.put("sec-ch-ua","Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"97\", \"Chromium\";v=\"97");
//        post_header.put("sec-ch-ua-mobile","?0");
//        post_header.put("sec-ch-ua-platform","Windows");
//        post_header.put("Sec-Fetch-Dest" ,"document" );
//        post_header.put("Sec-Fetch-Mode", "navigate");
//        post_header.put("Sec-Fetch-User","?1");


        
        return post_header;
    }


    public static String url_get(String url) throws Exception

    {
        try {
            properties=new Properties();
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\env.properties");
            properties.load(fileInputStream);



        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Not able to capture the value");
        }

//        if(System.getProperty("app_url")!=null)
//        {
//            FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\env.properties");
//            properties.setProperty("app_env", System.getProperty("app_url"));
//            properties.store(out, null);
//            out.close();
//            return properties.getProperty(url);
//        }

        return properties.getProperty(url);
    }


    public static String no_http_url()
    {

        try {
            properties=new Properties();
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\env.properties");
            properties.load(fileInputStream);



        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Not able to capture the value");
        }

         String modified_value=properties.getProperty("app_env").replace("https://", "");
         System.out.println("robben"+modified_value);
         return modified_value;


    }


    public static void setapp_env() throws Exception
    {
        if(System.getProperty("app_url")!=null)
        {

            String new_app_env="https\u003A//"+System.getProperty("app_url");
            FileInputStream in = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\env.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();
            FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\env.properties");
            props.setProperty("app_env",new_app_env.trim().toLowerCase());
            props.store(out, "");
            out.close();


//            PropertiesConfiguration conf = new PropertiesConfiguration(System.getProperty("user.dir")+"\\src\\main\\resources\\env.properties");
//            conf.setProperty("app_env", new_app_env);
//            conf.save();

        }

        if(System.getProperty("app_url")==null)
        {

            FileInputStream in = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\env.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();
            FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\env.properties");
            props.setProperty("app_env","https://qa.mar.sterrain.com");
            props.store(out, "");
            out.close();


//            PropertiesConfiguration conf = new PropertiesConfiguration(System.getProperty("user.dir")+"\\src\\main\\resources\\env.properties");
//            conf.setProperty("app_env", new_app_env);
//            conf.save();

        }



    }


    public static int single_exec()
    {
        int y=i+1;
        i++;
        return y;
    }
}
