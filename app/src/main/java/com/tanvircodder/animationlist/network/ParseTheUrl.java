package com.tanvircodder.animationlist.network;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ParseTheUrl {
//    creating the variable that will give the access of the log tag..//
    private static final String LOG_TAG= ParseTheUrl.class.getSimpleName();
//    constant..///
    private static final String BASE_URI =
        "https://kitsu.io/api/edge/anime?";
    /*nwo i am going to
    * create a mathod that will */
    public static final URL buildUrl (){
//        now i am going to parse the uri
        final Uri buildUri = Uri.parse(BASE_URI).buildUpon().build();
//        creating the instance of the URL object..//
        URL newURL = null;
        try{
            newURL = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return newURL;
    }
    public static final String urlRequest(URL urlConnection) throws IOException {
        HttpURLConnection openConnection =(HttpURLConnection)
                urlConnection.openConnection();
        /*openConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:33.0) Gecko/20100101 Firefox/33.0");
        openConnection.setDoInput(true);*/
        try {
            InputStream inputStream = openConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean isNext = scanner.hasNext();
            if (isNext){
                return scanner.next();
            }else {
                return null;
            }
        }catch (IOException e){
            Log.e(LOG_TAG,"There is some problem" + urlConnection);
        }finally {
//            nwo we need to disconnect the httpconnectioan...//
            openConnection.disconnect();
        }
        return null;
    }

}
