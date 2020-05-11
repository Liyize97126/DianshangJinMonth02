package com.bawei.dianshangjinmonth02.base;

import com.bawei.dianshangjinmonth02.bean.DataBean;
import com.bawei.dianshangjinmonth02.contract.IContract;
import com.bawei.dianshangjinmonth02.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter基类
 */
public abstract class BasePresenter {
    //定义
    private IContract.IView iView;
    protected IContract.IRequest iRequest;
    public BasePresenter(IContract.IView iView) {
        this.iView = iView;
        iRequest = RetrofitUtil.getRetrofitUtil().create(IContract.IRequest.class);
    }
    //请求
    public void request(){
        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DataBean>() {
                    @Override
                    public void accept(DataBean dataBean) throws Exception {
                        iView.success(dataBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        iView.fail(throwable.getMessage());
                    }
                });
    }
    //释放
    public void destroy(){
        if(iView != null){
            iView = null;
        }
    }
    //方法构造
    protected abstract Observable getObservable();
}
