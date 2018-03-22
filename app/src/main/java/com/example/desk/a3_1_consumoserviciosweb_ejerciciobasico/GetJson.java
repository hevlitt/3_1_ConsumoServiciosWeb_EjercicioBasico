package com.example.desk.a3_1_consumoserviciosweb_ejerciciobasico;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by desk on 21/03/18.
 */

public class GetJson extends AsyncTask<Void,Void,Void> {

    String data ="";
    String dataParsed = "";
    String singleParsed ="";


    @Override
    protected Void doInBackground(Void... voids) {
        try{
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Tepic,mx&APPID=a306b487f94188d2d73b17054ff28dbb");
            HttpURLConnection httpURLConnection = (HttpURLConnection)
                    url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject JO = new JSONObject(data);

            JSONObject JOMain = JO.getJSONObject("main");
            JSONObject JOSys = JO.getJSONObject("sys");

            singleParsed="Ciudad: " + JO.get("name") +
                    "\n Pais: " + JOSys.get("country") + "\n"+
                    "Humedad: " + JOMain.get("humidity") + "\n"+
                    "Temperatura: " + JOMain.get("temp") + "\n"+
                    "Temp. Minima: " + JOMain.get("temp_min") + "\n"+
                    "Temp. Maxima: " + JOMain.get("temp_max") + "\n"+
                    "Presion: " + JOMain.get("pressure") + "\n";
            dataParsed = dataParsed + singleParsed +"\n" ;

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setText(this.dataParsed);
    }
}
