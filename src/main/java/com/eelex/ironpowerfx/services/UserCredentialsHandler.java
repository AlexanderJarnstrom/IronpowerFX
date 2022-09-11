package com.eelex.ironpowerfx.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserCredentialsHandler {
    /**
     * It checks if the file exists, if not it creates it, then it reads the file and returns the JSONObject
     *
     * @return A JSONObject
     */
    public static JSONObject checkFileContent() {
        boolean fileExist = false;

        File userFile = new File("userCredentials.json");

        if (!userFile.exists()){
            try {
                fileExist = userFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            fileExist = true;
        }

        if (fileExist) {
            try (Scanner s = new Scanner(userFile)) {
                StringBuilder sb = new StringBuilder();
                JSONParser parser = new JSONParser();

                while (s.hasNextLine()) {
                    sb.append(s.nextLine());
                }

                if (sb.length() > 0) {
                    return (JSONObject) parser.parse(sb.toString());
                }
            } catch (FileNotFoundException | ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    public static void writeToFile(String email, String password) {
        File file = new File("userCredentials.json");

        try (FileWriter fw = new FileWriter(file)){
            JSONObject o = new JSONObject();
            o.put("email", email);
            o.put("password", password);

            fw.write(o.toJSONString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
