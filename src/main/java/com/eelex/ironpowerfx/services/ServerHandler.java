package com.eelex.ironpowerfx.services;

import com.eelex.ironpowerfx.templates.UserObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ServerHandler {
    public static int signIn(String email, String password) throws IOException {

        HttpURLConnection http = null;

        try {
            URL url = new URL(
                    "http://localhost:8080/ironpower/signIn?email=" +
                            email + "&password=" + password
            );

            URLConnection con = null;
            con = url.openConnection();
             http = (HttpURLConnection) con;

            String s = new String(http.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            JSONParser parser = new JSONParser();
            JSONObject userJSON = (JSONObject) parser.parse(s);

            if (http.getResponseCode() == 200) {
                System.out.println(userJSON.toJSONString());
                UserObject user = new UserObject();
                user.setUserInfoFromJSON(userJSON);

                if (UserTempFileHandler.createTempFile(user) == UserTempFileHandler.SUCCESS) {
                    System.out.println("Success");
                } else {
                    System.out.println("Failed");
                }
            }

            return http.getResponseCode();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        assert http != null;
        return http.getResponseCode();
    }

    public static int register(String email, String firstName, String lastName, String phoneNumber, String password) throws IOException {
        URL url = new URL("http://localhost:8080/ironpower/register");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;

        Map<String, String> userMap = new HashMap<String, String>();
        userMap.put("email", email);
        userMap.put("firstName", firstName);
        userMap.put("lastName", lastName);
        userMap.put("phoneNumber", phoneNumber);
        userMap.put("password", password);

        byte[] out = getURLRequest(userMap);

        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.setDoOutput(true);
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }

        return http.getResponseCode();
    }

    public static int getCustomers() throws IOException, ParseException {
        String uid = UserTempFileHandler.getUser().getUid();

        URL url = new URL("http://localhost:8080/ironpower/customers?uid=" + uid);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;

        String s = new String(http.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        JSONParser parser = new JSONParser();
        JSONArray JSONACustomer = (JSONArray) parser.parse(s);

        if (http.getResponseCode() == 200) {
            System.out.println(JSONACustomer.toJSONString());
        }

        http.disconnect();

        return http.getResponseCode();
    }

    private static byte[] getURLRequest(Map<String, String> m) {
        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : m.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "="
                    + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        return sj.toString().getBytes(StandardCharsets.UTF_8);
    }

}
