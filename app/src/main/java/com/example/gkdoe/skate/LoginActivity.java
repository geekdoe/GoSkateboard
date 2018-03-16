package com.example.gkdoe.skate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private CheckBox rememberPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences pref2 = getSharedPreferences("first",MODE_PRIVATE);
        SharedPreferences.Editor editor2 = pref2.edit();
        editor2.putBoolean("status",false);
        editor2.commit();

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.edit_text_account);
        passwordEdit = (EditText) findViewById(R.id.edit_text_password);
        rememberPassword = (CheckBox) findViewById(R.id.remember_password);
        Button button_login = (Button) findViewById(R.id.button_login);
        boolean isRemember = pref.getBoolean("remember_password", false);

        if(isRemember){
            //将账号和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPassword.setChecked(true);
        }

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(account.equals("admin")&&password.equals("123")){
                    editor = pref.edit();
                    if(rememberPassword.isChecked()){
                        //检查复选框是否被选中
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    }
                    else{
                        editor.clear();
                    }
                    editor.commit();
                    switch (v.getId()){
                        case R.id.button_login:
                            String inputText = accountEdit.getText().toString();
                            Toast.makeText(LoginActivity.this, "你好，"+inputText, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        default:
                            break;
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
