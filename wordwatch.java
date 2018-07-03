package com.example.root.word_watch;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button retrieve_log = null;
    private TextView results = null;
    private Cipher cipher;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieve_log = (Button)findViewById(R.id.send);
        results = (TextView)findViewById(R.id.result);
        retrieve_log.setOnClickListener(this);


    }

    private InputStream openhttpconnection(String urlstring) throws IOException {
        InputStream inputStream = null;
        int response = -1;

        URL url = new URL(urlstring);
        URLConnection connection = url.openConnection();

        if(!(connection instanceof HttpURLConnection)){
            throw new IOException("not a http connection");
        }

        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setRequestProperty("Cache-Control","no-cache");
        httpURLConnection.setRequestMethod("GET");

        httpURLConnection.connect();

        response = httpURLConnection.getResponseCode();

        if(response == HttpURLConnection.HTTP_OK){
            inputStream = httpURLConnection.getInputStream();
        }

        return inputStream;
    }

    private String downloadtext(String url){
        int buffersize = 2000;
        InputStream inputStream = null;

        try{
            inputStream = openhttpconnection(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        int charRead;
        String str = "";

        char[] inputBuffer = new char[buffersize];

        try {
            while((charRead = inputStreamReader.read(inputBuffer))>0){

                String read = String.copyValueOf(inputBuffer,0,charRead);
                str += read;
                inputBuffer = new char[buffersize];

            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    private class downloadtexttask extends AsyncTask<String, Void, String>{

        protected String doInBackground(String... params){
            return downloadtext(params[0]);
        }

        protected void onPostExecute(String s){


           results.setText(s);

        }
    }

    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.send) {
            String url = "fill with the valid link";
            new downloadtexttask().execute(url);


        }
    }
}

