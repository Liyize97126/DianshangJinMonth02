package com.bawei.dianshangjinmonth02.presenter;

import com.bawei.dianshangjinmonth02.base.BasePresenter;
import com.bawei.dianshangjinmonth02.contract.IContract;

import io.reactivex.Observable;

/**
 * 数据请求Presenter
 */
public class DataListPresenter extends BasePresenter {
    public DataListPresenter(IContract.IView iView) {
        super(iView);
    }
    @Override
    protected Observable getObservable() {
        return iRequest.shoppingcart();
    }
}
