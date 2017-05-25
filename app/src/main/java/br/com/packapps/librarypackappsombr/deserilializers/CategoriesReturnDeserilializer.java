package br.com.packapps.librarypackappsombr.deserilializers;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import br.com.packapps.librarypackappsombr.returns.CategoriesReturn;

/**
 * Created by paulolinhares on 25/05/17.
 */

public class CategoriesReturnDeserilializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement je = json.getAsJsonObject();
        return (new Gson().fromJson(je, CategoriesReturn.class));
    }
}
