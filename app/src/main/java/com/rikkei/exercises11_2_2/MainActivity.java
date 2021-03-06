package com.rikkei.exercises11_2_2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.sql.BatchUpdateException;

public class MainActivity extends AppCompatActivity {
    private Thread thread;
    private EditText edtURL;
    private Button btnGet;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtURL = (EditText) findViewById(R.id.edt_url);
        btnGet = (Button) findViewById(R.id.btn_get_number);
        tvResult = (TextView) findViewById(R.id.tv_result);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edtURL.getText().toString();
                new AsyncTask<String, Void, String>(){

                    @Override
                    protected String doInBackground(String... params) {
                        HttpClient client = new DefaultHttpClient();
                        HttpGet httpGet = new HttpGet(params[0]);
                        ResponseHandler<String> handler =
                                new BasicResponseHandler();
                        try {
                            return(client.execute(httpGet, handler));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        int length = s.length();
                        tvResult.setText(length+"");
                    }
                }.execute(url);

            }
        });

    }

    public static String urlContent(String address)
            throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(address);
        ResponseHandler<String> handler =
                new BasicResponseHandler();
        return(client.execute(httpGet, handler));
    }
}
