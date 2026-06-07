package webtest.demoqa.com.tasks.sandbox;

import com.google.gson.Gson;

public class Sandbox {
    public static void main(String[] args) {

        String input = "{name=John, version=1, status=START}";
        String converted = input.replace("=", ":")
                .replace("{", "{\"")
                .replace("}", "\"}")
                .replace(", ", "\", \"")
                .replace(":", "\":\"");
        System.out.println(input);
        System.out.println(converted);

        Gson gson = new Gson();
        MyPojo myPojo = new MyPojo("user name", "some data is here: test, valid test[];", 1);

        String jsonString = gson.toJson(myPojo);
        System.out.println(jsonString);
        MyPojo convertedpojo = gson.fromJson(jsonString, MyPojo.class);
        System.out.println(convertedpojo.toString());

        String ss = "{\"name\":\"user name\",\"data\":\", some special symbol:,.\"version\":1}";
        convertedpojo = gson.fromJson(jsonString, MyPojo.class);
        System.out.println(convertedpojo.toString());
    }
}
