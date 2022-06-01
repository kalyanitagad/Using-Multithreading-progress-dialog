package com.example.multithreading_progress_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    private void initViews()
    {
        btnDownload=findViewById(R.id.btnDownload);
    }
    private void initListeners()
    {
        btnDownload.setOnClickListener(new BtnDownloadListeners());
    }
    private class BtnDownloadListeners implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {
            new DownloadThread().execute((String) null);

        }
    }
    class DownloadThread extends AsyncTask<String,Integer,Float>//Async supports only wrapper classes
    {
         private  ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() { //main thread
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Nirja Movie");
            progressDialog.setMessage("Downloading....");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }
        @Override
        protected Float doInBackground(String... strings)//working thread
        {
            for(int i =1;i<=100;i++)
            {
               progressDialog.setProgress(i);
               try{
                   Thread.sleep(200);

               }catch(InterruptedException e)
               {
                   e.printStackTrace();
               }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Float aFloat) //main thread
        {
            super.onPostExecute(aFloat);
            progressDialog.dismiss();
        }
    }
}