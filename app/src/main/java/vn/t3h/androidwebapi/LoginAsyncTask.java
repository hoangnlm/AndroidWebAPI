package vn.t3h.androidwebapi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Hoang on 7/21/16.
 */

public class LoginAsyncTask extends AsyncTask<Pair<Context, Pair<String, String>>, Void, String> {
    private static final String TAG = "LoginAsyncTask";
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, Pair<String, String>>... params) {
        context = params[0].first;
        String name = params[0].second.first;
        String password = params[0].second.second;

        try {
            // Set up the request
            URL url = new URL("http://hoangproject1.appspot.com/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Build body data for Post request
            String content = "{\"user_name\":\"" + name + "\",\"pass\":\"" + password + "\"}";

            // Execute HTTP Post
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(content);
            writer.close();
            connection.connect();

            // Read response
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            }
            return "Error: " + connection.getResponseCode() + " " + connection.getResponseMessage();

        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        Gson gson = new GsonBuilder().create();
        LoginModel login = gson.fromJson(s, LoginModel.class);
        if (login.getStatus() == 1) {
            Toast.makeText(context, "Dang nhap thanh cong.\nToken cua ban la:\n" + login.getToken(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Dang nhap that bai.\n"+login.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
