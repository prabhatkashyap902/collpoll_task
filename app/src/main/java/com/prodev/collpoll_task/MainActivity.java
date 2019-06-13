package com.prodev.collpoll_task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.prodev.collpoll_task.adapters.recycler_adp;
import com.prodev.collpoll_task.api.api_interface;
import com.prodev.collpoll_task.model.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView rec;
    recycler_adp reca;

    int currentPage = 1;

    ArrayList<model> modelRecyclerArrayList = new ArrayList<>();
    model modelRecycler;
    ProgressBar progressBar,pb2;
    LinearLayoutManager manager;
    private static final String TAG = "MainActivity";

    Boolean isScrolling = false;
    int currentItem, totalItem, ScrolledItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb2 = findViewById(R.id.progrss_bar);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);
        reca = new recycler_adp(this);


        rec = (RecyclerView) findViewById(R.id.recycle);
        pb2.setVisibility(View.VISIBLE);
        manager = new LinearLayoutManager(this);
        rec.setLayoutManager(manager);

        fetchJSON(currentPage);




        rec.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                currentItem = manager.getChildCount();
                totalItem = manager.getItemCount();
                ScrolledItem = manager.findFirstVisibleItemPosition();



                Log.d(TAG, "totalItemCount: "+totalItem);
                Log.d(TAG, "firstVisibleItemPosition: "+ScrolledItem);
                Log.d(TAG, "currentItem: "+currentItem);
              //  Log.d(TAG, "PAGE_SIZE: "+PAGE_SIZE);


                if(dy >0){
                    if (isScrolling && ((ScrolledItem+currentItem)==totalItem)  ) {
                        isScrolling = false;
                        if (modelRecyclerArrayList.size() == 87) {
                            isScrolling = false;
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "We have reached to the end!", Toast.LENGTH_LONG);
                        } else {
                            isScrolling = false;
                            progressBar.setVisibility(View.VISIBLE);
                            //  String k= String.valueOf(currentPage);
                            // fetchJSON(k);
                            currentPage += 2;
                            fetchJSON(currentPage);
                        }

                    }


                }
            }

        });

        rec.setAdapter(reca);

    }

    private void fetchJSON(final int number){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api_interface.JSONURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

            final api_interface api = retrofit.create(api_interface.class);



                Call<String> call2=api.getProducts(number);


                call2.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call2, Response<String> response) {
                        // Log.i("Responsestring", response.body().toString());
                        //Toast.makeText()
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.i("onSuccess", response.body().toString());

                                pb2.setVisibility(View.INVISIBLE);
                                progressBar.setVisibility(View.GONE);

                                String jsonresponse = response.body().toString();
                                // Log.i("Done",modelRecyclerArrayList.toString());

                                writeRecycler(jsonresponse);
                                reca.notifyDataSetChanged();
                                int k=number;
                                fetchJSON2(++k);
                               // Call<String> call = api.getString();

                            } else {
                                Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call2, Throwable t) {

                    }
                });




    }
        public void fetchJSON2(int number){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api_interface.JSONURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

            api_interface api = retrofit.create(api_interface.class);

            //Call<String> call = api.getString();
            final Call<String > call3 = api.getProducts(number);
            call3.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call2, Response<String> response) {
                    // Log.i("Responsestring", response.body().toString());
                    //Toast.makeText()
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            Log.i("onSuccess", response.body().toString());

                            String jsonresponse = response.body().toString();
                            // Log.i("Done",modelRecyclerArrayList.toString());
                            pb2.setVisibility(View.INVISIBLE);
                            progressBar.setVisibility(View.GONE);
                            writeRecycler(jsonresponse);



                        } else {
                            Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call2, Throwable t) {

                }
            });
        }

       public void writeRecycler(String response){

            try {
                //getting the whole json object from the response

                JSONObject obj = new JSONObject(response);
                if(obj.opt("next")!=null||obj.opt("next")==null){


                    JSONArray dataArray  = obj.getJSONArray("results");

                    for (int i = 0; i < dataArray.length(); i++) {

                        modelRecycler = new model();
                        JSONObject dataobj = dataArray.getJSONObject(i);
                        String key;
                        JSONArray j1 = dataobj.getJSONArray("films");
                        JSONArray j2 = dataobj.getJSONArray("species");
                        JSONArray j3 = dataobj.getJSONArray("vehicles");
                        JSONArray j4 = dataobj.getJSONArray("starships");


                        //modelRecycler.setImg(dataobj.getString("thumbnail"));
                        modelRecycler.setName(dataobj.getString("name"));
                        modelRecycler.setBirthyear(dataobj.getString("birth_year"));
                        modelRecycler.setHeight(dataobj.getString("height"));
                        modelRecycler.setGender(dataobj.getString("gender"));
                        modelRecycler.setFilm_count(""+j1.length());
                        modelRecycler.setSpecies_count(""+j2.length());
                        modelRecycler.setVehicles_count(""+j3.length());
                        modelRecycler.setStarships_count(""+j4.length());
                        modelRecycler.setCreatedAt(dataobj.getString("created"));
                        modelRecycler.setEditedAt(dataobj.getString("edited"));
                        modelRecycler.setUrl(dataobj.getString("url"));
                        modelRecyclerArrayList.add(modelRecycler);
                        reca.updateList(modelRecyclerArrayList);

                        reca.notifyDataSetChanged();


                    }
                   // Log.i("Done",modelRecyclerArrayList.get(0).toString());


                }else {
                    Toast.makeText(getApplicationContext(), obj.optString("message")+"", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



    }

}