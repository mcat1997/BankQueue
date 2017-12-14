package com.example.a3899.bankqueue.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a3899.bankqueue.R;
import com.example.a3899.bankqueue.common.RetrofitManager;
import com.example.a3899.bankqueue.entity.BookEntity;
import com.example.a3899.bankqueue.service.BookService;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class BookActicvity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.book_button_book)
    Button button;
    private BookService bookService = RetrofitManager.create(BookService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_acticvity);

        ButterKnife.bind(this);
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        Integer userId = preferences.getInt("userId", 0);

        bookService.book(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookEntity>() {
                    @Override
                    public void accept(BookEntity bookEntity) throws Exception {
                        if (!bookEntity.isStatus()) {
                            Toast.makeText(BookActicvity.this, bookEntity.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BookActicvity.this, bookEntity.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BookActicvity.this, WaitingActivity.class);
                            BookActicvity.this.startActivity(intent);
                            BookActicvity.this.finish();
                        }
                    }
                }, new Consumer<Throwable>() {
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(BookActicvity.this, R.string.error_internet, Toast.LENGTH_SHORT).show();
                    }

                });
    }
}
