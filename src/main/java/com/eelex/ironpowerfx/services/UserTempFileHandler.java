package com.eelex.ironpowerfx.services;

import com.eelex.ironpowerfx.templates.UserObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserTempFileHandler {

    public static final int ERROR = -1;
    public static final int SUCCESS = 1;

    public static UserObject getUser() {
        File tempFile = new File("userTempInfo.json");

        try (Scanner s = new Scanner(tempFile)){
            StringBuilder sUser = new StringBuilder();

            while (s.hasNextLine()) {
                sUser.append(s.nextLine());
            }

            JSONParser parser = new JSONParser();

            UserObject user = new UserObject();
            user.setUserInfoFromJSON((JSONObject) parser.parse(sUser.toString()));

            return user;
        } catch (FileNotFoundException | ParseException e) {
            return null;
        }
    }

    public static int createTempFile(UserObject user) {
        File tempFile = new File("userTempInfo.json");
        try (FileWriter fw = new FileWriter(tempFile)){
            fw.write(user.getUserInJSON().toJSONString());
            return SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public static int deleteTempFile() {
        File tempFile = new File("userTempInfo.json");
        if (tempFile.exists()) {
            if (tempFile.delete()) {
                return SUCCESS;
            } else {
                return ERROR;
            }
        } else {
            return SUCCESS;
        }
    }
}
