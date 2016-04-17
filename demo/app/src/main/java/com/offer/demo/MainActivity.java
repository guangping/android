package com.offer.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.offer.demo.common.utils.SharedPreferencesUtils;
import com.offer.demo.common.utils.T;

import org.apache.commons.lang3.StringUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userName=SharedPreferencesUtils.getString(getApplicationContext(),"userName");
        if(StringUtils.isNoneBlank(userName)){
            Intent intent=new Intent(getApplicationContext(),InfoActivity.class);
            startActivity(intent);
            return;
        }
        setContentView(R.layout.login);


        this.init();
    }

    private void init(){
        Button login=(Button)findViewById(R.id.login);
        final EditText userNameText=(EditText)findViewById(R.id.userName);
        final EditText passwordText=(EditText)findViewById(R.id.password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //T.show(getApplicationContext(),"登录", Toast.LENGTH_LONG);
                String userName=userNameText.getText().toString();
                String password=passwordText.getText().toString();
                if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
                    T.show(getApplicationContext(),"用户名或密码不能为空!",Toast.LENGTH_LONG);
                    return;
                }
                //进度条提示

                //http请求验证


                //存储信息
                SharedPreferencesUtils.put(getApplicationContext(),"userName",userName);
                SharedPreferencesUtils.put(getApplicationContext(),"password",password);


                //跳转
                Intent intent=new Intent(getApplicationContext(),InfoActivity.class);
                startActivity(intent);
            }
        });

    }


}
