package com.devpoint.annotation_example.annotations.field;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
public class JsonSerializer {
    private static String getSerializedKey(Field field) {
        String annotationValue = field.getAnnotation(JsonField.class).value();
        if (annotationValue.isEmpty()) {
            return field.getName();
        } else {
            return annotationValue;
        }
    }

    public String serialize(Object object) throws JsonSerializeException {
        try {
            Class<?> objectClass = requireNonNull(object).getClass();
            Map<String, Object> jsonElements = new HashMap<>();
            for (Field field : objectClass.getDeclaredFields()) {

                field.setAccessible(true);
                if (field.isAnnotationPresent(JsonField.class)) {
                    jsonElements.put(getSerializedKey(field), field.get(object));
                } else if (field.isAnnotationPresent(IgnoreMe.class)) {

                } else {
                    jsonElements.put(field.getName(),  field.get(object));
                }
            }
            System.out.println(toJsonString(jsonElements));
            return toJsonString(jsonElements);
        } catch (IllegalAccessException e) {
            throw new JsonSerializeException(e.getMessage());
        }
    }

    private Object requireNonNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        } else {
            return object;
        }

    }

    private String toJsonString(Map<String, Object> jsonMap) {


        StringBuilder elementsString = null;
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()){
            if ( elementsString == null) {
                elementsString = new StringBuilder();
                elementsString.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
            }else{
                elementsString.append(",\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
            }
        }

        return "{" + elementsString + "}";
    }
}