package com.offer.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.common.utils.SharedPreferencesUtils;

import org.apache.commons.lang3.StringUtils;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        init();

    }

    private void init(){
        TextView view=(TextView)findViewById(R.id.viewName);

        String userName=SharedPreferencesUtils.getString(getApplicationContext(),"userName");
        if(StringUtils.isNoneBlank(userName)){
            view.setText("登录用户:"+userName);
        }

    }

}
