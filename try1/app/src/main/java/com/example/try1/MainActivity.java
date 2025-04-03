package com.example.try1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;

    String serverURL="http://192.168.65.235:5000";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button=findViewById(R.id.buttton);
        button2=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("Hello");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage1("Hello");
            }

        });
    }

    private void sendMessage(String message){
        String feed;
        Log.d("sendMessage","called");
        MessageUpload messageUpload = new MessageUpload(this,serverURL,message);
        messageUpload.execute();

    }

    private void sendMessage1(String message){
        String feed;
        Log.d("sendMessage","called");
        MessageUpload1 messageUpload1 = new MessageUpload1(this,serverURL,message);
        messageUpload1.send();

    }
}