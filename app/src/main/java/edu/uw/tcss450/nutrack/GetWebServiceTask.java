package edu.uw.tcss450.nutrack;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetWebServiceTask extends AsyncTask<String, Void, String> {

    private final String SERVICE = "_get.php";

    private LoginCompleted mCallback;

    public GetWebServiceTask(Context theContext) {
        mCallback = (LoginCompleted) theContext;
    }

    @Override
    protected String doInBackground(String... strings) {
        /*
        if (strings.length != 2) {
            throw new IllegalArgumentException("Two String arguments required.");
        }
        */
        String response = "";
        HttpURLConnection urlConnection = null;
        String url = strings[0];
        String args = "?email=" + strings[1] + "&password=" + strings[2] + "&login_mode=" + strings[3];
        try {
            URL urlObject = new URL(url + SERVICE + args);
            urlConnection = (HttpURLConnection) urlObject.openConnection();
            InputStream content = urlConnection.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
            String s = "";
            while ((s = buffer.readLine()) != null) {
                response += s;
            }
        } catch (Exception e) {
            response = "Unable to connect, Reason: "
                    + e.getMessage();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            mCallback.onLoginCompleted(jsonObject.getInt("result_code"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public interface LoginCompleted {
        public void onLoginCompleted(int resultCode);
    }
}