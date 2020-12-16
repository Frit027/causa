package com.site.utils.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.site.entities.Employee;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class BirthdaySerializer implements JsonSerializer<Employee> {
    @Override
    public JsonElement serialize(Employee src, Type type, JsonSerializationContext context) {
        if (src.getBirthday() == null) return null;

        JsonObject result = new JsonObject();

        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());

        Calendar calendarBirthday = Calendar.getInstance();
        calendarBirthday.setTime(src.getBirthday());

        String left;
        if (!isSameMonth(calendarNow, calendarBirthday)) {
            return null;
        } else {
            int diff = getDiffDays(calendarBirthday, calendarNow);

            if (diff < 0) {
                return null;
            } else if (diff == 0) {
                left = "сегодня";
            } else if (diff == 1) {
                left = "завтра";
            } else {
                left = "в этом месяце";
            }
        }

        result.addProperty("id", src.getId());
        result.addProperty("age", getAge(src.getBirthday()));
        result.addProperty("name", src.getSurname() + " " + src.getName());
        result.addProperty("position", src.getPosition().getName());
        result.addProperty("left", left);

        return result;
    }

    private boolean isSameMonth(Calendar c1, Calendar c2) {
        return (c1.get(Calendar.MONTH) + 1) == (c2.get(Calendar.MONTH) + 1);
    }

    private int getDiffDays(Calendar c1, Calendar c2) {
        return c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH);
    }

    private int getAge(Date birthday) {
        Date safeDate = new Date(birthday.getTime());
        LocalDate date = safeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return Period.between(date, LocalDate.now()).getYears() + 1;
    }
}
