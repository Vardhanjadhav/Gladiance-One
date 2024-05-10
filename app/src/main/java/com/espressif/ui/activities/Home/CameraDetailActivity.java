package com.espressif.ui.activities.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.espressif.ui.utils.Util;
import com.espressif.wifi_provisioning.R;

public class CameraDetailActivity extends AppCompatActivity {
    public String TAG = "MainActivity";

    EditText txtIP;
    EditText txtPort;
    EditText txtLogin;
    EditText txtPassword;
    Button btnPlay;
    Button btnVIFInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_detail);

        txtIP = (EditText) findViewById(R.id.txtIP);
        txtPort = (EditText) findViewById(R.id.txtPort);
        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnVIFInfo = (Button) findViewById(R.id.btnVIFInfo);

        txtIP.setText("192.168.1.59");
        //txtPort.setText("80");
        txtLogin.setText("test");
        txtPassword.setText("cartoon7028");

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtIP.getText().toString().isEmpty()) {
                    Toast.makeText(CameraDetailActivity.this, "IP can not be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                String VIFUrl = Util.getVIFString(txtLogin.getText().toString(), txtPassword.getText().toString(), txtIP.getText().toString(), txtPort.getText().toString());
                if (!VIFUrl.isEmpty()) {
                    Intent intent = new Intent(CameraDetailActivity.this, PlayActivity.class);
                    intent.setData(Uri.parse(VIFUrl));
                    CameraDetailActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(CameraDetailActivity.this, "invalid url", Toast.LENGTH_LONG).show();

                }
            }
        });


        btnVIFInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtIP.getText().toString().isEmpty() || txtPort.getText().toString().isEmpty()) {
                    Toast.makeText(CameraDetailActivity.this, "IP and port can not be empty", Toast.LENGTH_LONG).show();
                }
                new CamInfoTask().execute("");

            }
        });
    }

    private class CamInfoTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... urls) {


            return "";
        }

        protected void onPostExecute(String result) {
            Log.d(TAG, "Done");
        }
    }
}