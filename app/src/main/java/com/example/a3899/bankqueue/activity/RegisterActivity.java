package com.example.a3899.bankqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a3899.bankqueue.R;
import com.example.a3899.bankqueue.common.RetrofitManager;
import com.example.a3899.bankqueue.entity.RegisterEntity;
import com.example.a3899.bankqueue.service.RegisterService;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.register_button_login)
    Button button;
    @BindView(R.id.register_button_register)
    Button button1;
    @BindView(R.id.register_text_username)
    EditText usernameEditText;
    @BindView(R.id.register_text_password)
    EditText passwordEditText;
    @BindView(R.id.register_text_repeat_password)
    EditText repeatPasswordEditText;
    private RegisterService registerService = RetrofitManager.create(RegisterService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_button_register) {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String repeatPassword = repeatPasswordEditText.getText().toString();

            if (!password.equals(repeatPassword)) {
                Toast.makeText(this, R.string.error_diffPass, Toast.LENGTH_SHORT).show();
                return;
            }

            registerService.register(username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<RegisterEntity>() {
                        @Override
                        public void accept(RegisterEntity registerEntity) throws Exception {
                            if (registerEntity.isStatus()) {
                                Toast.makeText(RegisterActivity.this, R.string.point_register_success, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                                RegisterActivity.this.finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, registerEntity.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.e("", "", throwable);
                            Toast.makeText(RegisterActivity.this, R.string.error_internet, Toast.LENGTH_SHORT).show();
                        }
                    });


        } else {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            RegisterActivity.this.startActivity(intent);
            RegisterActivity.this.finish();
        }

    }
}
