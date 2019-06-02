package com.example.examen;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //Member variables
    private RecyclerView mRecyclerView;
    private adapterDatos mExampleAdapter;
    private ArrayList<itemsDatos> mExampleList;
    private RequestQueue mRequestQueue;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private String email;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersDbHelper conn = new usersDbHelper(this,"users", null, 1);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList =new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        usersDbHelper conn = new usersDbHelper(this,"users", null, 1);
        final SQLiteDatabase db = conn.getWritableDatabase();
        final ContentValues values = new ContentValues();

        String url = "https://reqres.in/api/users?page=2";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i =0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                id = data.getString("id");
                                imageUrl = data.getString("avatar");
                                firstName = data.getString("first_name");
                                lastName = data.getString("last_name");
                                email = data.getString("email");

                                mExampleList.add(new itemsDatos(imageUrl, firstName, lastName));
                            }

                            for (int i =0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                id = data.getString("id");
                                imageUrl = data.getString("avatar");
                                firstName = data.getString("first_name");
                                lastName = data.getString("last_name");
                                email = data.getString("email");
                                values.put(itemsSQL.usersEntry._ID, id);
                                values.put(itemsSQL.usersEntry.FIRST_NAME, firstName);
                                values.put(itemsSQL.usersEntry.LAST_NAME, lastName);
                                values.put(itemsSQL.usersEntry.EMAIL, email);
                                values.put(itemsSQL.usersEntry.AVATAR_URI, imageUrl);
                                Long idUsuario = db.insert(itemsSQL.usersEntry.TABLE_NAME, itemsSQL.usersEntry._ID, values);
                                mExampleList.add(new itemsDatos(imageUrl, firstName, lastName));
                                Toast.makeText(getApplicationContext(),"Se Guardo en SQLite:"+idUsuario, Toast.LENGTH_SHORT).show();
                            }

                            mExampleAdapter = new adapterDatos(MainActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    public void Guardar(){
        usersDbHelper conn = new usersDbHelper(this,"users", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(itemsSQL.usersEntry._ID, id);
        values.put(itemsSQL.usersEntry.FIRST_NAME, firstName);
        values.put(itemsSQL.usersEntry.LAST_NAME, lastName);
        values.put(itemsSQL.usersEntry.EMAIL, email);
        values.put(itemsSQL.usersEntry.AVATAR_URI, imageUrl);
        Long idUsuario = db.insert(itemsSQL.usersEntry.TABLE_NAME, itemsSQL.usersEntry._ID, values);
        Toast.makeText(getApplicationContext(),"Se Guardo en SQLite:"+idUsuario, Toast.LENGTH_SHORT).show();
        Log.println(3,"",idUsuario.toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

