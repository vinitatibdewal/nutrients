package com.vinita.nutrients;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DisplayMessageActivity extends AppCompatActivity {

    TextView tvnext;
    int message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        tvnext = (TextView)findViewById(R.id.tvnext);
        message = intent.getIntExtra("message",0);
        loadDataFromAsset();
    }
    public void loadDataFromAsset() {

        BufferedReader br = null;
        String fileText = "";
        int lineCtr=0;
        int lineNo=0;
        try {
            String str = "";
            StringBuffer buffer = new StringBuffer();
            AssetManager assetManager = getAssets();
            InputStream stream = assetManager.open("abc.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    stream));

            lineCtr = 0;
            while ((reader.readLine()) != null)   {
                lineCtr++;
            }

            if (stream != null) {
                while ((str = reader.readLine()) != null) {
                    for (lineNo = 1; lineNo <= lineCtr; lineNo++) {
                            if (lineNo == message) {
                                    while(str!="\n")
                                    buffer.append(str);
                            }
                            else
                                reader.readLine();
                    }
                }
            }
            fileText = buffer.toString();
            tvnext.setText(new String(fileText));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null)
                    br.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}