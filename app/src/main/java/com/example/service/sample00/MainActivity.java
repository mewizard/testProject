package com.example.service.sample00;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emit0();
        emit1();

        Logger.i("master");
    }

    /**
     * 데이터를 그대로 출력.
     */
    private void emit0() {
        Observable.just("Hello", "RxJava 2!!")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.i("subscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.i(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });
    }

    private void emit1() {
        Disposable disposable = Observable.just("RED", "GREEN", "YELLOW")
                .subscribe(
                        onNext -> Logger.i(onNext),
                        onError -> {
                            onError.printStackTrace();
                            Logger.e(onError.getMessage());
                        },
                        () -> Logger.i("complete"));

        Logger.i("disposable.isDisposed(): %s", disposable.isDisposed());

        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
