package io.github.gdtknight;

import io.github.gdtknight.myjsonparser.core.MyJsonObject;
import io.github.gdtknight.myjsonparser.exception.MyJsonException;

public class Main {
  public static void main(String[] args) {
    String jsonString =
        "{\n"
            + "  \"name\": \"Madame Uppercut\",\n"
            + "  \"age\": 39,\n"
            + "  \"secretIdentity\": \"Jane Wilson\",\n"
            + "  \"powers\": [\n"
            + "    \"Million tonne punch\",\n"
            + "    \"Damage resistance\",\n"
            + "    \"Superhuman reflexes\"\n"
            + "  ]\n"
            + "}";

    MyJsonObject jsonObject = new MyJsonObject(jsonString);
    System.out.println(jsonObject);
    System.out.println();
    try {
      System.out.println("name : " + jsonObject.getValue("name"));
      System.out.println("age : " + jsonObject.getValue("age"));
      System.out.println("secretIdentity : " + jsonObject.getValue("secretIdentity"));
      System.out.println("powers : " + jsonObject.getValue("powers"));
    } catch (MyJsonException e) {
      System.out.println(e.getMessage());
    }
    System.out.println();
  }
}
