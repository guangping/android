package com.offer.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SystemActivity extends AppCompatActivity implements View.OnClickListener {

    private Button cameraBtn = null;
    private Button imgBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        this.init();
    }

    private void init() {
        cameraBtn=(Button)findViewById(R.id.camera);
        imgBtn=(Button)findViewById(R.id.img);

        cameraBtn.setOnClickListener(this);
        imgBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.camera:
                this.camera();
            case R.id.img:
                this.img();
            default:

        }

    }


    private void camera() {

    }

    private void img() {

    }
}
