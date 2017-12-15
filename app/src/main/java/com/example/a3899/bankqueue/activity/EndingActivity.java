package com.example.a3899.bankqueue.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a3899.bankqueue.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EndingActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ending_textview)
    TextView textView;
    @BindView(R.id.ending_button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);

        ButterKnife.bind(this);
        button.setOnClickListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        Integer couterId = sharedPreferences.getInt("counterId", 0);
        String string = String.format(getString(R.string.ending_string), couterId);
        textView.setText(string);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(EndingActivity.this, BookActicvity.class);
        EndingActivity.this.startActivity(intent);
        EndingActivity.this.finish();

    }
}
