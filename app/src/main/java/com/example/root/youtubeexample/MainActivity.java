package com.example.root.youtubeexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Module> alist =new ArrayList<>();
    RecyclerView recyclerView;
    RequestQueue requestQueue ;
    String url="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet,status&fields=nextPageToken,items%28snippet%28publishedAt,title,resourceId,thumbnails%29,status%29&maxResults=50&playlistId=PLrEnWoR732-BHrPp_Pm8_VleD68f9s14-&key=AIzaSyBE6MOf5lgU7wpMMCvmh9RLmHYxxS9Es68&videoEmbeddable=true&videoSyndicated=true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest request =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject firstObject = new JSONObject(response);
                    JSONArray itemArray = firstObject.getJSONArray("items");


                    for (int i = 0; i < itemArray.length(); i++) {
                        JSONObject secondObject =itemArray.getJSONObject(i);
                        JSONObject snippetObject = secondObject.getJSONObject("snippet");


                        Module m = new Module();
                        m.setTitle(snippetObject.getString("title"));
                        m.setDate(snippetObject.getString("publishedAt"));

                        JSONObject thumbnailObject = snippetObject.getJSONObject("thumbnails");
                        JSONObject resourceId =snippetObject.getJSONObject("resourceId");
                        m.setVideoid(resourceId.getString("videoId"));

                        JSONObject defaultObject =thumbnailObject.getJSONObject("default");
                        m.setUrl(defaultObject.getString("url"));

                        alist.add(m);
                    }
                }

                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Exception caught", Toast.LENGTH_SHORT).show();
                }
                recyclerView.setAdapter(new CustomAdapter(MainActivity.this,alist));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Could not connect to server", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }
}
