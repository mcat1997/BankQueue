package com.example.a3899.bankqueue.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3899.bankqueue.R;
import com.example.a3899.bankqueue.common.RetrofitManager;
import com.example.a3899.bankqueue.entity.WaitingEntity;
import com.example.a3899.bankqueue.service.WaitingService;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class WaitingActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.waiting_textview)
    TextView textView;
    @BindView(R.id.waiting_button)
    Button button;
    private WaitingService waitingService = RetrofitManager.create(WaitingService.class);

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        ButterKnife.bind(this);
        button.setOnClickListener(this);

        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        final Integer userId = preferences.getInt("userId", 0);

        disposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .flatMap(new Function<Long, Observable<WaitingEntity>>() {
                    @Override
                    public Observable<WaitingEntity> apply(Long aLong) throws Exception {
                        return waitingService.waiting(userId);
                    }
                })
                .filter(new Predicate<WaitingEntity>() {
                    @Override
                    public boolean test(WaitingEntity waitingEntity) throws Exception {
                        return true;
                    }
                })
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WaitingEntity>() {
                    @Override
                    public void accept(WaitingEntity waitingEntityObservable) throws Exception {
                        if (!waitingEntityObservable.isStatus()) {
                            Intent intent = new Intent(WaitingActivity.this, BookActicvity.class);
                            WaitingActivity.this.startActivity(intent);
                            WaitingActivity.this.finish();
                        } else {
                            if (waitingEntityObservable.getCounterId() == -1) {
                                String string = String.format(getString(R.string.point_waiting), waitingEntityObservable.getNum());

                                textView.setText(string);
                            } else {
                                SharedPreferences preferences1 = getSharedPreferences("user", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences1.edit();
                                editor.putInt("couterId", waitingEntityObservable.getCounterId());
                                editor.apply();

                                Intent intent = new Intent(WaitingActivity.this, EndingActivity.class);
                                WaitingActivity.this.startActivity(intent);
                                WaitingActivity.this.finish();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("", "", throwable);
                        Toast.makeText(WaitingActivity.this, R.string.error_internet, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    @Override
    public void onClick(View v) {

    }
}
