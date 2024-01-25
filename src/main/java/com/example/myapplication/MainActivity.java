package com.example.myapplication;

import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private static final String AURL = "https://api.open-meteo.com/v1/forecast?latitude=30.28&longitude=-97.76&hourly=temperature_2m,relative_humidity_2m,rain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue reqq= Volley.newRequestQueue(this);
        StringRequest requrl = new StringRequest(Request.Method.GET, AURL,
                new Response.Listener<String>(){
                    public void onResponse(String response){
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray time = obj.getJSONObject("hourly").getJSONArray("time");
                            JSONArray temp = obj.getJSONObject("hourly").getJSONArray("temperature_2m");
                            JSONArray humidity = obj.getJSONObject("hourly").getJSONArray("relative_humidity_2m");
                            JSONArray rain = obj.getJSONObject("hourly").getJSONArray("rain");
                            String[] dates =  new String[7];
                            Double[] temps = new Double[7];
                            Double[] humidities = new Double[7];
                            Double[] rains = new Double[7];

                            int datappd = time.length()/7;
                            DecimalFormat df = new DecimalFormat("#.##");

                            for(int i = 0; i <7; i++){
                                int sidx = i*datappd;
                                int eidx = (i+1)*datappd;

                                double tempSum = 0.0;
                                double humSum = 0.0;
                                double rainSum = 0.0;
                                for(int j = sidx; j<eidx;j++){
                                    tempSum+=temp.getDouble(j);
                                    humSum += humidity.getDouble(j);
                                    rainSum += rain.getDouble(j);
                                }
                                double dayavgTemp = tempSum/datappd;
                                double dayavgHum = humSum/datappd;
                                double dayTotRain = rainSum;
                                dates[i] = "Day " + (i+1);
                                temps[i] = dayavgTemp;
                                humidities[i] = dayavgHum;
                                rains[i] = dayTotRain;
                            }
                            TextView tempdayone = (TextView) findViewById(R.id.day1temp);
                            String formattedTemp = df.format(temps[0]);
                            tempdayone.setText(formattedTemp);

                            TextView tempdaytwo = (TextView) findViewById(R.id.day2temp);
                            formattedTemp = df.format(temps[1]);
                            tempdaytwo.setText(formattedTemp);

                            TextView tempdaythree = (TextView) findViewById(R.id.day3temp);
                            formattedTemp = df.format(temps[2]);
                            tempdaythree.setText(formattedTemp);

                            TextView tempday4 = (TextView) findViewById(R.id.day4temp);
                            formattedTemp = df.format(temps[3]);
                            tempday4.setText(formattedTemp);

                            TextView tempday5 = (TextView) findViewById(R.id.day5temp);
                            formattedTemp = df.format(temps[4]);
                            tempday5.setText(formattedTemp);

                            TextView tempday6 = (TextView) findViewById(R.id.day6temp);
                            formattedTemp = df.format(temps[5]);
                            tempday6.setText(formattedTemp);

                            TextView tempday7 = (TextView) findViewById(R.id.day7temp);
                            formattedTemp = df.format(temps[6]);
                            tempday7.setText(formattedTemp);

                            TextView rainday1 = (TextView) findViewById(R.id.day1rainfall);
                            formattedTemp = df.format(rains[0]);
                            rainday1.setText(formattedTemp);

                            TextView rainday2 = (TextView) findViewById(R.id.day2rain);
                            formattedTemp = df.format(rains[1]);
                            rainday2.setText(formattedTemp);

                            TextView rainday3 = (TextView) findViewById(R.id.day3rain);
                            formattedTemp = df.format(rains[2]);
                            rainday3.setText(formattedTemp);

                            TextView rainday4 = (TextView) findViewById(R.id.day4rain);
                            formattedTemp = df.format(rains[3]);
                            rainday4.setText(formattedTemp);

                            TextView rainday5 = (TextView) findViewById(R.id.day5rain);
                            formattedTemp = df.format(rains[4]);
                            rainday5.setText(formattedTemp);

                            TextView rainday6 = (TextView) findViewById(R.id.day6rain);
                            formattedTemp = df.format(rains[5]);
                            rainday6.setText(formattedTemp);

                            TextView rainday7 = (TextView) findViewById(R.id.day7rain);
                            formattedTemp = df.format(rains[6]);
                            rainday7.setText(formattedTemp);


                            TextView humidityday1 = (TextView) findViewById(R.id.day1humidity);
                            formattedTemp = df.format(humidities[0]);
                            humidityday1.setText(formattedTemp);

                            TextView humidityday2 = (TextView) findViewById(R.id.day2hum);
                            formattedTemp = df.format(humidities[1]);
                            humidityday2.setText(formattedTemp);

                            TextView humidityday3 = (TextView) findViewById(R.id.day3hum);
                            formattedTemp = df.format(humidities[2]);
                            humidityday3.setText(formattedTemp);

                            TextView humidityday4 = (TextView) findViewById(R.id.day4hum);
                            formattedTemp = df.format(humidities[3]);
                            humidityday4.setText(formattedTemp);

                            TextView humidityday5 = (TextView) findViewById(R.id.day5hum);
                            formattedTemp = df.format(humidities[4]);
                            humidityday5.setText(formattedTemp);

                            TextView humidityday6 = (TextView) findViewById(R.id.day6hum);
                            formattedTemp = df.format(humidities[5]);
                            humidityday6.setText(formattedTemp);

                            TextView humidityday7 = (TextView) findViewById(R.id.day7hum);
                            formattedTemp = df.format(humidities[6]);
                            humidityday7.setText(formattedTemp);



                        }
                        catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener(){
                    public void onErrorResponse(VolleyError error){

                    }
                }

        );
    reqq.add(requrl);

    }
}