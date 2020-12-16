package com.site.utils.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.site.entities.Employee;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class EmployeeSerializer implements JsonSerializer<Employee> {

    @Override
    public JsonElement serialize(Employee scr, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy");

        String fullName = scr.getSurname() + " " + scr.getName() + " " + scr.getPatronymic();
        result.addProperty("id", scr.getId());
        result.addProperty("fullName", fullName);
        result.addProperty("position", scr.getPosition().getName());
        result.addProperty("email", scr.getEmail());
        result.addProperty("phone", scr.getPhone());

        String sex = scr.getSex().equals("male") ? "Мужчина" : "Женщина";
        result.addProperty("sex", sex);

        result.addProperty("hired", formatter.format(scr.getHired()));
        result.addProperty("salary", scr.getSalary());

        if (scr.getBirthday() == null) {
            result.addProperty("birthday", "Не установлено");
        } else {
            result.addProperty("birthday", formatter.format(scr.getBirthday()));
        }

        return result;
    }
}
