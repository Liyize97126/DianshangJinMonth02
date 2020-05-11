package com.bawei.dianshangjinmonth02.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 页面基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    //定义
    private Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        getSupportActionBar().hide();
        initView();
        initData();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        initDestroy();
        unbinder.unbind();
    }
    //方法封装
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initDestroy();
}
