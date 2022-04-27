package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class Login extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private EditText etAccount;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        Map<String, String> userMap = SaveAccount.getUserInfo(this);
        if (userMap != null) {
            etAccount.setText(userMap.get("userName"));
            etPassword.setText(userMap.get("password"));
        }
    }

    private void init() {
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        textView = findViewById(R.id.tv_register);
        button = findViewById(R.id.btn_login);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString();
                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(Login.this, "请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                // 保存用户信息
//                boolean isSaveSuccess = FileSaveQQ.saveUserInfo(this, account, password);
                boolean isSaveSuccess = SaveAccount.saveUserInfo(Login.this, account, password);
                if (isSaveSuccess) {
                    Toast.makeText(Login.this, "保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}