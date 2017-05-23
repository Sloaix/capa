package com.lsxiao.capa.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lsxiao.capa.R;
import com.lsxiao.capa.library.CapaLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CapaLayout capaLayout;
    Button btnLoad;
    Button btnError;
    Button btnEmpty;
    Button btnContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        capaLayout = (CapaLayout) findViewById(R.id.capa_layout);
        btnLoad = (Button) findViewById(R.id.btn_load);
        btnError = (Button) findViewById(R.id.btn_error);
        btnEmpty = (Button) findViewById(R.id.btn_empty);
        btnContent = (Button) findViewById(R.id.btn_content);

        capaLayout.setOnClickListener(this);
        btnLoad.setOnClickListener(this);
        btnError.setOnClickListener(this);
        btnEmpty.setOnClickListener(this);
        btnContent.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load:
                capaLayout.toLoad();
                break;
            case R.id.btn_error:
                capaLayout.toError();
                break;
            case R.id.btn_empty:
                capaLayout.toEmpty();
                break;
            case R.id.btn_content:
                capaLayout.toContent();
                break;
            case R.id.capa_layout:
                break;
        }
    }

}
