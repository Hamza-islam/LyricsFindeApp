package com.hamza.lyricsfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText edtArtistName;
    private EditText edtSongName;
    private Button lyricsBtn;
    private TextView lyricsTxt;

    //https://api.lyrics.ovh/v1/Rihanna/Diamonds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtArtistName = (EditText) findViewById(R.id.edtArtistName);
        edtSongName = (EditText) findViewById(R.id.edtSongName);
        lyricsBtn = (Button) findViewById(R.id.getLyrics);
        lyricsTxt = (TextView) findViewById(R.id.actualLyrics);

        lyricsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Toast.makeText(MainActivity.this,"Tapped", Toast.LENGTH_SHORT).show();*/
                String url = "https://api.lyrics.ovh/v1/" + edtArtistName.getText().toString() + "/" + edtSongName.getText().toString();
                url.replace(" ", "%20");
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            lyricsTxt.setText(response.getString("lyrics"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });

    }
}