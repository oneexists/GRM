package edu.metrostate.ics370.grm.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class videogame {

    //Uses Json Simple Parser Library - Might not want to use.
    //https://medium.com/swlh/getting-json-data-from-a-restful-api-using-java-b327aafb3751

    public static void main(String[] args) {
        try {

            URL url = new URL("https://steamspy.com/api.php?request=appdetails&appid=730");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);



                System.out.println(data_obj);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

