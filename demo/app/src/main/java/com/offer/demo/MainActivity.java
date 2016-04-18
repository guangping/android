package com.offer.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.offer.demo.common.utils.HttpUtils;
import com.offer.demo.common.utils.L;
import com.offer.demo.common.utils.T;
import com.offer.demo.pojo.APIResult;
import com.offer.demo.pojo.User;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ExecutorService es = Executors.newFixedThreadPool(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* String userName = SharedPreferencesUtils.getString(getApplicationContext(), "userName");
        if (StringUtils.isNoneBlank(userName)) {
            Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
            startActivity(intent);
            return;
        }*/
        setContentView(R.layout.login);


        this.init();
    }

    private Handler loginHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            L.i("处理登录返回信息:");
            String result = msg.getData().getString("loginResult");
            try {
                APIResult<User> apiResult = JSONObject.parseObject(result, new TypeReference<APIResult<User>>() {
                });
                if (apiResult.isSuccess()) {
                    T.show(getApplicationContext(), "登录失败!", Toast.LENGTH_LONG);
                } else {
                    T.show(getApplicationContext(), result, Toast.LENGTH_LONG);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void init() {
        Button login = (Button) findViewById(R.id.login);
        final EditText userNameText = (EditText) findViewById(R.id.userName);
        final EditText passwordText = (EditText) findViewById(R.id.password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //T.show(getApplicationContext(),"登录", Toast.LENGTH_LONG);
                String userName = userNameText.getText().toString();
                String password = passwordText.getText().toString();
                if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
                    T.show(getApplicationContext(), "用户名或密码不能为空!", Toast.LENGTH_LONG);
                    return;
                }
                //进度条提示

                //http请求验证
                es.execute(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        String url = "http://114.55.0.118/index.json";
                        String result = HttpUtils.get(url);
                        if (StringUtils.isNoneBlank(result)) {
                            L.i("返回结果:" + result);

                            Bundle bundle = new Bundle();
                            bundle.putString("loginResult", result);

                            Message message = new Message();
                            message.setData(bundle);
                            loginHandler.sendMessage(message);
                        } else {
                            T.show(getApplicationContext(), "返回结果22:", Toast.LENGTH_LONG);
                        }
                    }
                });
             /*   //存储信息
                SharedPreferencesUtils.put(getApplicationContext(), "userName", userName);
                SharedPreferencesUtils.put(getApplicationContext(), "password", password);


                //跳转
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);*/
            }
        });

    }


}
