package com.fanhl.recyclerview.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanhl.recyclerview.R;
import com.fanhl.recyclerview.mock.UserMock;
import com.fanhl.recyclerview.model.User;
import com.fanhl.recyclerview.ui.adapter.VerticalBarLayoutManagerAdapter;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InContainerActivity extends AppCompatActivity {
    private RecyclerView                    mRecyclerView;
    private VerticalBarLayoutManagerAdapter mAdapter;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, InContainerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        assignViews();
        initData();
        refreshData();
    }

    private void assignViews() {
        mRecyclerView = ((RecyclerView) findViewById(R.id.recycler_view));
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL/*方向*/,
                false/*是否反向*/
        ));
        mAdapter = new VerticalBarLayoutManagerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void refreshData() {
        Observable
                .create(new Observable.OnSubscribe<User>() {
                    @Override
                    public void call(Subscriber<? super User> subscriber) {
                        List<User> list = UserMock.list();
                        for (User user : list) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            subscriber.onNext(user);
                        }
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        mAdapter.addItem(user);
                    }
                });
    }
}
