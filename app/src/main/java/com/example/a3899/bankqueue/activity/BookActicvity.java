package com.example.a3899.bankqueue.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.a3899.bankqueue.R;

public class BookActicvity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_acticvity);

        textView = findViewById(R.id.user);
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        Integer userId = preferences.getInt("userId", 0);
        textView.setText(userId.toString());
    }
}
