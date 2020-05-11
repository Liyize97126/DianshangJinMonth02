package com.bawei.dianshangjinmonth02.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bawei.dianshangjinmonth02.R;

/**
 * 自定义加减器
 */
public class AddReduceView extends FrameLayout {
    //定义
    private TextView countText;
    private OnCountBack onCountBack;
    public void setOnCountBack(OnCountBack onCountBack) {
        this.onCountBack = onCountBack;
    }
    private int count;
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
        countText.setText(String.valueOf(count));
    }
    //方法实现
    public AddReduceView(@NonNull Context context) {
        super(context);
        initView();
    }
    public AddReduceView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public AddReduceView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    //初始化视图
    private void initView(){
        View view = View.inflate(getContext(), R.layout.view_add_reduce, this);
        countText = view.findViewById(R.id.count_text);
        view.findViewById(R.id.reduce_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 1){
                    count--;
                    countText.setText(String.valueOf(count));
                } else {
                    Toast.makeText(getContext(),"数量不能少于1个！",Toast.LENGTH_LONG).show();
                }
                if(onCountBack != null){
                    onCountBack.onCount(AddReduceView.this);
                }
            }
        });
        view.findViewById(R.id.add_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                countText.setText(String.valueOf(count));
                if(onCountBack != null){
                    onCountBack.onCount(AddReduceView.this);
                }
            }
        });
    }
    //回调
    public interface OnCountBack{
        void onCount(AddReduceView view);
    }
}
