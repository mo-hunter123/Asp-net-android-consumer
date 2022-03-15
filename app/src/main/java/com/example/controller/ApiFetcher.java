package com.example.controller;

import android.content.Context;

import com.example.Model.Livre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiFetcher {
    public static List<Livre> GetLivres(URL url, Context context) throws IOException, JSONException {
        List<Livre> listesLivres = new ArrayList<Livre>();
        JSONArray jsonArray = new JSONArray(ReaderMiddleWare.loadDataFromLink(context, url));

        for(int i = 0; i<jsonArray.length(); i++){
            listesLivres.add(
                    new Livre(
                            Integer.parseInt(jsonArray.getJSONObject(i).getString("id")),
                            jsonArray.getJSONObject(i).getString("title"),
                            jsonArray.getJSONObject(i).getString("ISBN")
                    )
            );

        }
        return listesLivres;
    }
}
