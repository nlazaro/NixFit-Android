package com.nlazaro.nixfit_android;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIClient  {
    protected OkHttpClient client;
    private final String url;
    APIClient(){
        System.out.println("Creating Client");
        client = new OkHttpClient();
        System.out.println("FINSHED CREATING CLIENT");
        url = "https://world.openfoodfacts.net/api/v2/product/";
    }

    public String GetBardcodeData(String code){
        System.out.println("Incoming Code:"+code);

        String requestUrl = url +"code";
        Request request = new Request.Builder()
                .url(requestUrl)
                .build();
        System.out.println("finished building request");
        try {
            System.out.println("sending request");
            final Response response = client.newCall(request).execute();
            System.out.println("BarCode to request" + response.toString());
            return response.toString();
        } catch (IOException e) {
            System.out.println("Error trying to send API call");
            throw new RuntimeException(e);
        }

    }
}
