package com.fanhl.recyclerview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fanhl.recyclerview.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.linear_layout_manager)
    void linear_layout_manager() {
        LinearLayoutManagerActivity.launch(this);
    }

    @OnClick(R.id.grid_layout_manager)
    void grid_layout_manager() {
        GridLayoutManagerActivity.launch(this);
    }

    @OnClick(R.id.in_other_container)
    void in_other_container() {
        InContainerActivity.launch(this);
    }
}
