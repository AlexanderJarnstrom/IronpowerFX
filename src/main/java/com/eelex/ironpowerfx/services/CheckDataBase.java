package com.eelex.ironpowerfx.services;

import com.eelex.ironpowerfx.templates.UserObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class CheckDataBase {
    public static final int ERROR = -1;

    public static int checkTanks() throws IOException {
        UserObject user = UserTempFileHandler.getUser();

        if (user != null) {
            URL url = new URL("http://localhost:8080/ironpower/checkTable?uid=" + user.getUid() + "&table=tanks");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;

            http.disconnect();

            return http.getResponseCode();
        }

        return ERROR;
    }

    public static int checkCustomers() throws IOException {
        UserObject user = UserTempFileHandler.getUser();

        if (user != null) {
            URL url = new URL("http://localhost:8080/ironpower/checkTable?uid=" + user.getUid() + "&table=customers");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;

            http.disconnect();

            return http.getResponseCode();
        }

        return ERROR;

    }

    public static int checkDatabase() throws IOException {
        UserObject user = UserTempFileHandler.getUser();

        if (user != null) {
            URL url = new URL("http://localhost:8080/ironpower/checkTable?uid=" + user.getUid() + "&table=database");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;

            http.disconnect();

            return http.getResponseCode();
        }

        return ERROR;

    }

    public static int checkInventory() throws IOException {
        UserObject user = UserTempFileHandler.getUser();

        if (user != null) {
            URL url = new URL("http://localhost:8080/ironpower/checkTable?uid=" + user.getUid() + "&table=inventory");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;

            http.disconnect();

            return http.getResponseCode();
        }

        return ERROR;

    }
}
