package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import android.util.Log;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();

        try {
            JSONObject reader = new JSONObject(json);

            JSONObject nameObject = reader.getJSONObject(NAME);
            sandwich.setMainName(nameObject.getString(MAIN_NAME));
            JSONArray knownAsJsonArray = nameObject.getJSONArray(ALSO_KNOWN_AS);
            List<String> knownAsList = new ArrayList<>();
            if (knownAsJsonArray != null) {
                for (int i = 0; i < knownAsJsonArray.length(); i++) {
                    knownAsList.add(knownAsJsonArray.getString(i));
                }
            }
            sandwich.setAlsoKnownAs(knownAsList);
            sandwich.setPlaceOfOrigin(reader.getString(PLACE_OF_ORIGIN));
            sandwich.setDescription(reader.getString(DESCRIPTION));
            sandwich.setImage(reader.getString(IMAGE));
            JSONArray ingredientArray = reader.getJSONArray(INGREDIENTS);
            List<String> ingredientList = new ArrayList<>();
            for (int index = 0; index < ingredientArray.length(); index++) {
                ingredientList.add(ingredientArray.getString(index));
            }
            sandwich.setIngredients(ingredientList);

            Log.d("TAG","JSON Contents: " + sandwich.toString());

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return sandwich;
    }
}

