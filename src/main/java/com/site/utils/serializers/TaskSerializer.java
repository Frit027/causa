package com.site.utils.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.site.entities.Employee;
import com.site.entities.Task;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class TaskSerializer implements JsonSerializer<Task> {
    @Override
    public JsonElement serialize(Task src, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy");
        Employee emp = src.getEmployee();
        String executor = emp.getSurname() + " "
                            + emp.getName().charAt(0) + ". "
                            + emp.getPatronymic().charAt(0) + ".";

        String priority = null;
        switch (src.getPriority()) {
            case 1:
                priority = "Низкий";
                break;
            case 2:
                priority = "Средний";
                break;
            case 3:
                priority = "Высокий";
        }

        result.addProperty("id", src.getId());
        result.addProperty("name", src.getName());
        result.addProperty("description", src.getDescription());
        result.addProperty("deadline", formatter.format(src.getDeadline()));
        result.addProperty("priority", priority);
        result.addProperty("executor", executor);

        return result;
    }
}
