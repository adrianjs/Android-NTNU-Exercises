package com.example.adrst.ex5;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.util.Map;

/**
 * Created by adrst on 06.03.2018.
 */

class HttpConnection {
    private MainActivity mainActivity;
    private final String TAG = "HttpConnection";
    private final String ENCODING = "ISO-8859-1";

    HttpConnection(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    }

    void startNewThread(Map<String, String> parameters) {
        new HttpThread().execute(parameters);
    }

    public class HttpThread extends AsyncTask<Map<String, String>, String, String> {
        @SafeVarargs
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        protected final String doInBackground(Map<String, String>... v) {
            try {
                Log.i(TAG, "START GET REQUEST");
                String URL = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp?";
                String url = URL + encodeParameters(v[0]);
                URLConnection urlConnection = new URL(url).openConnection();
                urlConnection.setRequestProperty("Accept-Charset", ENCODING);

                try (InputStream in = urlConnection.getInputStream()) {
                    return readResponse(in, getCharset(urlConnection));
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
                return "";
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                return e.getMessage();
            }
        }

        protected void onPostExecute(String response) {
            mainActivity.displayResponse(response);
        }
    }

    private String encodeParameters(Map<String, String> parameterList) {
        StringBuilder s = new StringBuilder();
        for (String param: parameterList.keySet()) {
            String value = parameterList.get(param);
            try {
                s.append(param).append("=");
                s.append(URLEncoder.encode(value, ENCODING));
                s.append("&");
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, e.toString());
            }
        }
        s.deleteCharAt(s.length()-1);
        return s.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String readResponse(InputStream is, String charset) {
        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset))) {
            for (String line; (line = reader.readLine()) != null;) {
                body.append(line);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            body.append("Something wrong happened while reading response");
        }
        return body.toString();
    }

    private String getCharset(URLConnection urlConnection) {
        String contentType = urlConnection.getHeaderField("Content-Type");
        String charset = null;

        for (String param : contentType.replace(" ", "").split(";")) {
            if (param.startsWith("charset=")) {
                charset = param.split("=", 2)[1];
                break;
            }
        }
        Log.i(TAG, "Content-Type: " + contentType);
        Log.i(TAG, "Charset = " + charset);
        return charset;
    }
}
