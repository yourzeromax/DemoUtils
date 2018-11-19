package com.yourzeromax.moduleutils;

/* *
 * Created by yourzeromax
 * on 2018/10/22
 *
 *
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static <T> T deserialize(String json, Class<T> classOfT) {
        return new Gson().fromJson(json, classOfT);
    }

    public static <T> String serialize(T src) {
        return new Gson().toJson(src);
    }

    public static String getStringToJson(String jsonStr, String key) {
        String value = "";
        try {
            if (!CheckUtils.isEmpty(jsonStr)) {
                JsonObject jsonObject = new Gson().fromJson(jsonStr, JsonObject.class);
                JsonElement jsonElement = jsonObject.get(key);
                if (jsonElement != null) {
                    if (jsonElement.isJsonObject()) {
                        value = jsonElement.getAsJsonObject().toString();
                    } else {
                        value = jsonElement.getAsString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;

    }

    public static <T> List<T> deserializeList(String json, Class<T> cls) {
        List<T> ret = null;
        if (json != null &&
                json.startsWith("[") &&
                json.endsWith("]")) {
            ret = new ArrayList<>();
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);
            JsonArray array = new JsonParser().parse(reader).getAsJsonArray();
            for (final JsonElement elem : array) {
                ret.add(new Gson().fromJson(elem, cls));
            }
        }
        return ret;
    }

    public static <T> String serializeList(List<T> src) {
        String ret = null;
        if (src != null) {
            ret = "[";
            int listSize = src.size();
            for (int i = 0; i < listSize; i++) {
                String objString = serialize(src.get(i));
                if (i == listSize - 1) {
                    ret += objString;
                } else {
                    ret += objString + ",";
                }
            }
            ret += "]";
        }
        return ret;
    }
}
