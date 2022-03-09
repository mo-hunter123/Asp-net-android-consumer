package com.example.aspconsummer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.Model.Livre;
import com.example.controller.ApiFetcher;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//s
public class MainActivity extends AppCompatActivity {

    static final String API_LINK = "http://192.168.254.1:81/api/showLivres";
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        class LivreTask extends AsyncTask<String, Void, List<Livre>>{
            @Override
            protected List<Livre> doInBackground(String... strings) {

                List<Livre> listeLivres = new ArrayList<Livre>();
                try {
                    listeLivres = ApiFetcher.GetLivres(new URL(strings[0]), getApplicationContext());

                }catch (IOException e){
                    System.out.println("==IO" + e);
                } catch (JSONException e){
                    System.out.println("==JSON" + e);
                }
                return  listeLivres;
            }

            @Override
            protected void onPostExecute(List<Livre> livres) {
                super.onPostExecute(livres);
                ArrayList<String> lv_arr = new ArrayList<String>();
                for(int i=0; i<livres.size(); i++){
                    lv_arr.add(livres.get(i).toString());
                }
                listView.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.text_view_livre, lv_arr));
            }
        }
        new LivreTask().execute(API_LINK);
    }

}