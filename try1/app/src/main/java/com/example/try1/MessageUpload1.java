package com.example.try1;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MessageUpload1 {
    private String message;

    private String serverURL;

    String responseMessage;

    Context context;




    public MessageUpload1(Context context, String serverUrl, String message) {
        this.serverURL = serverUrl;
        this.message = message;
        this.context = context;
    }

//    public void execute()

//        ExecutorService executor = Executors.newSingleThreadExecutor();


//        executor.execute(new Runnable() {
//            @Override
//            public void run()

    public void send(){

        try {
            URL url = new URL(serverURL + "/send");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setDoOutput(true);

            String jsonInputString = "{\"message\": \"" + message + "\"}";

            try (OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
//                        return response.toString();
//                        Log.d("Response",response.toString());
                JSONObject jsonResponse = new JSONObject(response.toString());
                responseMessage = jsonResponse.getString("response");

                Log.d("ResponseMessage", responseMessage);
                // Toast.makeText(context,responseMessage,Toast.LENGTH_SHORT).show();

//
            }

//                    uiHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
////                            Toast.makeText(context,responseMessage,Toast.LENGTH_SHORT).show();
//                        }
//                    });
        } catch (Exception e) {
            e.printStackTrace();
//                    return "Error: " + e.getMessage();
            responseMessage = e.getMessage();
            Log.e("Error", e.getMessage());
        }
    }

}
