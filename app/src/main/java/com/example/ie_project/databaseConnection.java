package com.example.ie_project;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class databaseConnection
{
    private static final String BASE_URL = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/login.php";

    public static String login()
    {
        URL url = null;
        HttpURLConnection conn = null;
        String result = "";
        try
        {
            url = new URL(BASE_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            Scanner inStream = new Scanner(conn.getInputStream());
            while (inStream.hasNextLine())
            {
                result += inStream.nextLine();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.disconnect();
        }
        return result;
    }

}
