package com.example.android.okhttpsamplecode;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (TextView) findViewById(R.id.lv);
        //String str = networkCallUsingOkHttp();

        //lv.setText(str);
        //new OkHttpHandler.execute();
        OkHttpHandler okHttpHandler  = new OkHttpHandler();
        okHttpHandler.execute();
    }

    public class OkHttpHandler extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            //return new byte[0];
            String str =networkCallUsingOkHttp();
            return str;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.v("response>>>>>",s);
            lv.setText(s);
        }
    }
    public String networkCallUsingOkHttp() {
        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=15")
                .build();
        // for Synchronous Calling

        try{
            Response response = client.newCall(request).execute();
            return response.body().string();

        }
        catch (IOException e){
        }
        return null;


        // for Async Calling
        /*client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    //System.out.println(responseBody.string());
                    //return responseBody.toString();
                    lv.setText(responseBody.toString());
                }

            }
        });
        */
    }
}
