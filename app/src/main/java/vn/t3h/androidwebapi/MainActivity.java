package vn.t3h.androidwebapi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuPopupHelper;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button btLogin;
    private EditText etName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new ServletPostAsyncTask().execute(new Pair<Context, String>(MainActivity.this, "Hoang"));
//                Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                login();
            }
        });
        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPass);
    }

    private void login() {
        new LoginAsyncTask().execute(new Pair<Context, Pair<String, String>>(this, new Pair<>(etName.getText().toString(), etPassword.getText().toString())));
    }
}
