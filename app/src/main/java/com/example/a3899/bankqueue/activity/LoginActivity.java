package com.example.a3899.bankqueue.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a3899.bankqueue.R;
import com.example.a3899.bankqueue.common.RetrofitManager;
import com.example.a3899.bankqueue.entity.LoginEntity;
import com.example.a3899.bankqueue.service.LoginService;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.login_button_login)
    Button button;
    @BindView(R.id.login_button_register)
    Button button1;
    @BindView(R.id.login_text_username)
    EditText usernameEditText;
    @BindView(R.id.login_text_password)
    EditText passwordEditText;
    private LoginService loginService = RetrofitManager.create(LoginService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button_login) {
            final String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            loginService.login(username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<LoginEntity>() {
                        @Override
                        public void accept(LoginEntity loginEntity) throws Exception {
                            if (loginEntity.isStatus()) {
                                SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("userId", loginEntity.getUserId());
                                editor.apply();

                                Intent intent = new Intent(LoginActivity.this, BookActicvity.class);
                                LoginActivity.this.startActivity(intent);
                                LoginActivity.this.finish();
                            } else {
                                Toast.makeText(LoginActivity.this, loginEntity.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Toast.makeText(LoginActivity.this, R.string.error_internet, Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(intent);
            LoginActivity.this.finish();
        }
    }

}

