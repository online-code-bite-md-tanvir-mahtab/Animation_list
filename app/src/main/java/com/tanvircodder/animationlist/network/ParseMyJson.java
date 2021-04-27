package com.tanvircodder.animationlist.network;

import android.content.Context;

import com.tanvircodder.animationlist.MainActivity;
import com.tanvircodder.animationlist.utility.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseMyJson {
    private static List<Util> mdata = null;


    private static final String DATAF_KEY= "data";
    private static final String TYPE_KEY = "type ";
    private static final String ATTRIBUTES_KEY = "attributes";
    private static final String DESCRIPTION_KEY = "description";
    private static final String TITLE_JSON_KEY = "titles";
    private static final String ENGLIST_TITLE_KEY = "en";

//    now to store the data of the list we are going to ..//


    public static  List<Util> parsingJson(MainActivity context, String url) throws JSONException {
        // : 4/26/2021 now i need to add the key that will help me to
//        parse the uri json file..//
        JSONObject jsonObject = new JSONObject(url);
//        now i am going to parse the array from the json object..//
        JSONArray jsonArray = jsonObject.getJSONArray(DATAF_KEY);
        // TODO: 4/26/2021 I also need to inisialize the object of the util class.
//        now i am going to inisialize the object..//
        mdata = new ArrayList<>();

        for (int i =0;i<jsonArray.length();i++){
//            now i am going to loop the jsonobject..//
            JSONObject nObjet = jsonArray.getJSONObject(i);
//            for the type case..//
            String type = nObjet.getString(TYPE_KEY);
//            now I am going to parse the another object array..//
            JSONObject attributeObject =   nObjet.getJSONObject(ATTRIBUTES_KEY);
//            for the description..//
            String description = attributeObject.getString(DESCRIPTION_KEY);
//            now for the another jsonobject...//
            JSONObject titleObject  = attributeObject.getJSONObject(TITLE_JSON_KEY);
            String englishTitle = titleObject.getString(ENGLIST_TITLE_KEY);

//            nwo i am going to add the value..///
            mdata.add(new Util(type,description,englishTitle));

        }
        return mdata;
    }
}
