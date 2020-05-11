package com.bawei.dianshangjinmonth02.contract;

import com.bawei.dianshangjinmonth02.bean.DataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 契约类
 */
public interface IContract {
    interface IView{
        void success(DataBean dataBean);
        void fail(String err);
    }
    interface IRequest{
        @GET("api/mall/shoppingcart.json")
        Observable<DataBean> shoppingcart();
    }
}
