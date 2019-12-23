package com.jgr.camerafun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    TextView input;
    int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.setButton);
        input = findViewById(R.id.timeInput);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    time = Integer.parseInt(input.getText().toString());
                    Toast.makeText(getApplicationContext(), "Timer: " + time, Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error! " + input.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
