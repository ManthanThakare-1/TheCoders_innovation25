package com.example.try1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;
    Button button3;
    TextView textView;


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
        button3=findViewById(R.id.button3);
        textView=findViewById(R.id.textView);
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
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRscan();
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
    private void QRscan(){
        GmsBarcodeScanner scanner = GmsBarcodeScanning.getClient(this);
        scanner
                .startScan()
                .addOnSuccessListener(
                        barcode -> {
                            String rawValue = barcode.getRawValue();
                            textView.setText(rawValue);
                        })
                .addOnCanceledListener(
                        () -> {
                            // Task canceled
                        })
                .addOnFailureListener(
                        e -> {
                            // Task failed with an exception
                        });

    }
}