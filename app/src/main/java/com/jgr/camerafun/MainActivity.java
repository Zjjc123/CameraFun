package com.jgr.camerafun;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    private static final int MY_CAMERA_REQUEST_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }


        WebView mywebview = (WebView) findViewById(R.id.webView);
        mywebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });
        mywebview.loadUrl("https://www.google.com");
        /*
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        submitButton = findViewById(R.id.setButton);
        input = findViewById(R.id.timeInput);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    time = Integer.parseInt(input.getText().toString());
                    Toast.makeText(getApplicationContext(), "Timer: " + time, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error! " + input.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

         */

        Log.d("CameraFun","Start Timer");
        new CountDownTimer(6 * 1000, 1000) {
            public void onFinish() {
                openFrontFacingCamera();
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();
    }

    private Camera openFrontFacingCamera()
    {
        int cameraCount = 0;
        Camera cam = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        for ( int camIdx = 0; camIdx < cameraCount; camIdx++ ) {
            Camera.getCameraInfo( camIdx, cameraInfo );
            if ( cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT  ) {
                try {
                    cam = Camera.open( camIdx );
                } catch (RuntimeException e) {
                    Log.e("CameraFun", "Camera failed to open: " + e.getLocalizedMessage());
                }
            }
        }

        return cam;
    }
}
