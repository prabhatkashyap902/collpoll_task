package com.prodev.collpoll_task;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.prodev.collpoll_task.api.api_interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SecondActivity extends AppCompatActivity {
    String a;
    ProgressDialog pd;

    TextView tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8,tx9,tx10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

pd =new ProgressDialog(this);
         a = getIntent().getExtras().getString("number");
         tx1=findViewById(R.id.tx1);
            tx2=findViewById(R.id.tx2);
            tx3=findViewById(R.id.tx3);
            tx4=findViewById(R.id.tx4);
            tx5=findViewById(R.id.tx5);
            tx6=findViewById(R.id.tx6);
            tx7=findViewById(R.id.tx7);
            tx8=findViewById(R.id.tx8);
            tx9=findViewById(R.id.tx9);
            tx10=findViewById(R.id.tx10);
       // Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
pd.show();
pd.setCanceledOnTouchOutside(false);
pd.setMessage("Loading Data from Server! ");
        fetchJSON();


    }


        private void fetchJSON(){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(a)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

            api_interface api = retrofit.create(api_interface.class);

            Call<String> call = api.getString();

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.i("Responsestring", response.body().toString());
                    //Toast.makeText()
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            Log.i("onSuccess", response.body().toString());

                            String jsonresponse = response.body().toString();
                            // Log.i("Done",modelRecyclerArrayList.toString());
                           // pb.setVisibility(View.GONE);
                            pd.dismiss();
                            writeRecycler(jsonresponse);



                        } else {
                            Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }

        public void writeRecycler(String response){

            try {
                //getting the whole json object from the response

                String m="",n="",o="",p="";

                JSONObject obj = new JSONObject(response);
                ArrayList<String> al1 = new ArrayList<>();
                ArrayList<String> al2 = new ArrayList<>();
                ArrayList<String> al3 = new ArrayList<>();
                ArrayList<String> al4 = new ArrayList<>();

                JSONArray js = obj.getJSONArray("films");
                if(js!=null  && js.length() > 0 ){
                    for(int i=0;i<js.length();i++){
                        al1.add(js.getString(i));
                    }
                }
                else{m="N/A";}
                JSONArray js2 = obj.getJSONArray("species");
                if(js2!=null && js2.length()>0){
                    for(int i=0;i<js2.length();i++){
                        al2.add(js2.getString(i));
                    }
                }
                else{n="N/A";}

                JSONArray js3 = obj.getJSONArray("vehicles");
                if(js3!=null&&js3.length()>0){
                    for(int i=0;i<js3.length();i++){
                        al3.add(js3.getString(i));
                    }
                }
                else{o="N/A";}

                JSONArray js4 = obj.getJSONArray("starships");
                if(js4!=null&&js4.length()>0){
                    for(int i=0;i<js4.length();i++){
                        al4.add(js4.getString(i));
                    }
                }
                else{p="N/A";}

                for(int i=0;i<al1.size();i++){
                    m=m+al1.get(i)+"\n";
                }
                for(int i=0;i<al2.size();i++){
                    n=n+al2.get(i)+"\n";
                }
                for(int i=0;i<al3.size();i++){
                    o=o+al3.get(i)+"\n";
                }
                for(int i=0;i<al4.size();i++){
                    p=p+al4.get(i)+"\n";
                }







                tx1.setText(obj.getString("name"));
                tx2.setText(obj.getString("birth_year"));
                tx3.setText(obj.getString("height"));
                tx4.setText(obj.getString("gender"));

                tx5.setText(m);
                tx6.setText(n);
                tx7.setText(o);
                tx8.setText(p);
                tx9.setText(obj.getString("created"));
                tx10.setText(obj.getString("edited"));


               /* if(obj.optInt("count")==87){


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

                        modelRecyclerArrayList.add(modelRecycler);


                    }
                    // Log.i("Done",modelRecyclerArrayList.get(0).toString());



                }else {
                    Toast.makeText(getApplicationContext(), obj.optString("message")+"", Toast.LENGTH_SHORT).show();
                }
*/
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }






}
