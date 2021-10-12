package edu.metrostate.ics370.grm.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FetchAPI {

    // Uses Json Simple Parser Library - Might not want to use.

    // Can even pull header picture by using the App ID for each game.
    // https://steamcdn-a.akamaihd.net/steam/apps/730/header.jpg
    // Number 730 is the App ID for CSGO.
    // Number 550 is L4D2.

    // This example only works for single game pulls.
    // https://steamspy.com/api.php?request=top100in2weeks returns multiple. Idk how to loop through each.

    // https://medium.com/swlh/getting-json-data-from-a-restful-api-using-java-b327aafb3751 - Source

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

                // Print Complete Object
                System.out.println("Complete Object: " + data_obj + "\n");

                // Grab + Print  Name
                String name = (String) data_obj.get("name");
                System.out.println("Game name: " + name + "\n");

                // Grab + Print  Publisher
                String publisher = (String) data_obj.get("publisher");
                System.out.println("Publisher: " + publisher + "\n");

                // Grab + Print Languages
                String languages = (String) data_obj.get("languages");
                System.out.println("Languages: " + languages + "\n");

                // Grab + Print Tags Object | Not sure how to separate this.
                JSONObject tags = (JSONObject) data_obj.get("tags");
                System.out.println("Tags: " + tags + "\n");

                // Loop through all key + values using for-each loop
                for (Object o : data_obj.keySet()) {
                    String key = (String) o;
                    System.out.println(o + ": " + data_obj.get(key));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

