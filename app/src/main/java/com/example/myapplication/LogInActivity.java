package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class LogInActivity extends AppCompatActivity {
    private Button registerBtn,loginBtn;
    private EditText ed_username,ed_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        init();
        //注册按钮
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //提取编辑框中输入的用户名和密码，将他们写入到文件中
                String username=ed_username.getText().toString();
                String password=ed_password.getText().toString();
                if (UserInfo_SharePre.saveUserInfo(username,password,LogInActivity.this)){
                    Toast.makeText(LogInActivity.this,"注册成功！！！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LogInActivity.this,"注册失败！！！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //登陆按钮
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=ed_username.getText().toString();
                String password=ed_password.getText().toString();
                Map<String,String> userMap=UserInfo_SharePre.getUserInfo(LogInActivity.this);
                if(username.equals(userMap.get("username"))&&password.equals(userMap.get("password"))){
                    Toast.makeText(LogInActivity.this,"验证成功！！！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LogInActivity.this,"验证失败！！！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void init(){
        registerBtn=findViewById(R.id.register);
        loginBtn=findViewById(R.id.login);
        ed_username=findViewById(R.id.User_Name);
        ed_password=findViewById(R.id.Password);
    }
}