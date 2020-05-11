package com.bawei.dianshangjinmonth02.activity;

import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dianshangjinmonth02.R;
import com.bawei.dianshangjinmonth02.adapter.DataListAdapter;
import com.bawei.dianshangjinmonth02.base.BaseActivity;
import com.bawei.dianshangjinmonth02.bean.DataBean;
import com.bawei.dianshangjinmonth02.bean.SaveInfoBean;
import com.bawei.dianshangjinmonth02.contract.IContract;
import com.bawei.dianshangjinmonth02.dao.DaoMaster;
import com.bawei.dianshangjinmonth02.dao.SaveInfoBeanDao;
import com.bawei.dianshangjinmonth02.presenter.DataListPresenter;
import com.bawei.dianshangjinmonth02.util.RetrofitUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 页面布局
 */
public class MainActivity extends BaseActivity implements IContract.IView {
    //定义控件
    @BindView(R.id.data_list)
    protected ExpandableListView dataList;
    @BindView(R.id.chk_status)
    protected CheckBox chkStatus;
    @BindView(R.id.goods_count)
    protected TextView goodsNum;
    @BindView(R.id.total_price)
    protected TextView totalMoney;
    private DataListAdapter dataListAdapter;
    private DataListPresenter dataListPresenter;
    private SaveInfoBeanDao saveInfoBeanDao;
    //定义
    //布局
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    //初始化
    @Override
    protected void initView() {
        dataListAdapter = new DataListAdapter();
        dataList.setAdapter(dataListAdapter);
        dataListAdapter.setOnPriceChangeListener(new DataListAdapter.OnPriceChangeListener() {
            @Override
            public void onPriceChange(int goodsCount, double totalPrice, boolean isAll) {
                goodsNum.setText("共" + goodsCount + "件商品，合计：");
                totalMoney.setText("￥" + totalPrice);
                chkStatus.setChecked(isAll);
            }
        });
        dataListPresenter = new DataListPresenter(this);
        saveInfoBeanDao = DaoMaster.newDevSession(this,SaveInfoBeanDao.TABLENAME).getSaveInfoBeanDao();
    }
    //处理数据
    @Override
    protected void initData() {
        if(RetrofitUtil.getRetrofitUtil().hasNet()){
            dataListPresenter.request();
        } else {
            SaveInfoBean saveInfoBean = saveInfoBeanDao.queryBuilder().where(SaveInfoBeanDao.Properties.Id.eq(1)).unique();
            if(saveInfoBean != null){
                DataBean dataBean = new Gson().fromJson(saveInfoBean.getJsonData(), DataBean.class);
                dataListAdapter.getList().addAll(dataBean.getOrderData());
                dataListAdapter.notifyDataSetChanged();
                Toast.makeText(this,"已加载缓存数据！",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"没有网络！",Toast.LENGTH_LONG).show();
            }
        }
    }
    //全选点击事件
    @OnClick(R.id.chk_status)
    protected void checkAll(){
        dataListAdapter.checkAll(chkStatus.isChecked());
    }
    //释放
    @Override
    protected void initDestroy() {
        if(dataListAdapter != null){
            dataListAdapter.getList().clear();
            dataListAdapter = null;
        }
        if(dataListPresenter != null){
            dataListPresenter.destroy();
            dataListPresenter = null;
        }
    }
    @Override
    public void success(DataBean dataBean) {
        //添加数据
        String toJson = new Gson().toJson(dataBean);
        saveInfoBeanDao.insertOrReplaceInTx(new SaveInfoBean(1,toJson));
        dataListAdapter.getList().addAll(dataBean.getOrderData());
        dataListAdapter.notifyDataSetChanged();
    }
    @Override
    public void fail(String err) {
        Toast.makeText(this, "请求失败\n" + err, Toast.LENGTH_LONG).show();
    }
}
