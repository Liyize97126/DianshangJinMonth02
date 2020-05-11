package com.bawei.dianshangjinmonth02.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.dianshangjinmonth02.R;
import com.bawei.dianshangjinmonth02.bean.DataBean;
import com.bawei.dianshangjinmonth02.view.AddReduceView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表适配器
 */
public class DataListAdapter extends BaseExpandableListAdapter {
    //定义
    private int goodsCount = 0;
    private List<DataBean.OrderDataBean> list = new ArrayList<>();
    public List<DataBean.OrderDataBean> getList() {
        return list;
    }
    //定义回调接口
    private OnPriceChangeListener onPriceChangeListener;
    public void setOnPriceChangeListener(OnPriceChangeListener onPriceChangeListener) {
        this.onPriceChangeListener = onPriceChangeListener;
    }
    @Override
    public int getGroupCount() {
        return list.size();
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getCartlist().size();
    }
    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getCartlist().get(childPosition);
    }
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        MyOrderDataListHouler myOrderDataListHouler;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_data_list_contents,parent,false);
            myOrderDataListHouler = new MyOrderDataListHouler();
            myOrderDataListHouler.shopName = convertView.findViewById(R.id.shop_name);
            convertView.setTag(myOrderDataListHouler);
        } else {
            myOrderDataListHouler = (MyOrderDataListHouler) convertView.getTag();
        }
        //设置数据
        DataBean.OrderDataBean orderDataBean = list.get(groupPosition);
        myOrderDataListHouler.shopName.setText(orderDataBean.getShopName());
        //选择框
        myOrderDataListHouler.shopName.setChecked(orderDataBean.isCheck());
        myOrderDataListHouler.shopName.setTag(orderDataBean);
        myOrderDataListHouler.shopName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBean.OrderDataBean o = (DataBean.OrderDataBean) v.getTag();
                CheckBox checkBox = (CheckBox) v;
                o.setCheck(checkBox.isChecked());
                //商品数量计数器（拓展）
                if(checkBox.isChecked()){
                    goodsCount+=o.getCartlist().size();
                } else {
                    goodsCount-=o.getCartlist().size();
                }
                //联动
                List<DataBean.OrderDataBean.CartlistBean> cartlist = o.getCartlist();
                for (int i = 0; i < cartlist.size(); i++) {
                    cartlist.get(i).setCheck(o.isCheck());
                }
                //价格计算调用
                calculateTotalPrice();
                //刷新适配器
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        MyCartListHouler myCartListHouler;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_contents,parent,false);
            myCartListHouler = new MyCartListHouler();
            myCartListHouler.check = convertView.findViewById(R.id.check);
            myCartListHouler.defaultPic = convertView.findViewById(R.id.default_pic);
            myCartListHouler.productName = convertView.findViewById(R.id.product_name);
            myCartListHouler.color = convertView.findViewById(R.id.color);
            myCartListHouler.price = convertView.findViewById(R.id.price);
            myCartListHouler.count = convertView.findViewById(R.id.count);
            convertView.setTag(myCartListHouler);
        } else {
            myCartListHouler = (MyCartListHouler) convertView.getTag();
        }
        //设置数据
        DataBean.OrderDataBean.CartlistBean cartlistBean = list.get(groupPosition).getCartlist().get(childPosition);
        myCartListHouler.productName.setText(cartlistBean.getProductName());
        myCartListHouler.color.setText(cartlistBean.getColor());
        myCartListHouler.price.setText("￥" + cartlistBean.getPrice());
        RequestOptions requestOptions = new RequestOptions()
                .fallback(R.drawable.zhan_pict)
                .error(R.drawable.zhan_pict)
                .placeholder(R.drawable.zhan_pict)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)));
        Glide.with(myCartListHouler.defaultPic.getContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(cartlistBean.getDefaultPic())
                .into(myCartListHouler.defaultPic);
        //加减器
        myCartListHouler.count.setCount(cartlistBean.getCount());
        myCartListHouler.count.setTag(cartlistBean);
        myCartListHouler.count.setOnCountBack(new AddReduceView.OnCountBack() {
            @Override
            public void onCount(AddReduceView view) {
                int count = view.getCount();
                DataBean.OrderDataBean.CartlistBean c = (DataBean.OrderDataBean.CartlistBean) view.getTag();
                c.setCount(count);
                //价格计算调用
                calculateTotalPrice();
            }
        });
        //选择框
        myCartListHouler.check.setChecked(cartlistBean.isCheck());
        myCartListHouler.check.setTag(cartlistBean);
        myCartListHouler.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBean.OrderDataBean.CartlistBean c = (DataBean.OrderDataBean.CartlistBean) v.getTag();
                CheckBox checkBox = (CheckBox) v;
                //商品数量计数器（拓展）
                if(checkBox.isChecked()){
                    goodsCount+=1;
                } else {
                    goodsCount-=1;
                }
                c.setCheck(checkBox.isChecked());
                //联动
                for (int i = 0; i < list.size(); i++) {
                    DataBean.OrderDataBean ord = list.get(i);
                    List<DataBean.OrderDataBean.CartlistBean> cartlist = ord.getCartlist();
                    boolean isGoodsChecked = true;
                    for (int j = 0; j < cartlist.size(); j++) {
                        DataBean.OrderDataBean.CartlistBean carBean = cartlist.get(j);
                        isGoodsChecked = carBean.isCheck() && isGoodsChecked;
                    }
                    ord.setCheck(isGoodsChecked);
                }
                //价格计算调用
                calculateTotalPrice();
                //刷新适配器
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    //全选方法
    public void checkAll(boolean isAll){
        int len = 0;
        for (int i = 0; i < list.size(); i++) {
            DataBean.OrderDataBean o = list.get(i);
            o.setCheck(isAll);
            for (int j = 0; j < o.getCartlist().size(); j++) {
                o.getCartlist().get(j).setCheck(isAll);
                if(isAll){
                    len++;
                }
            }
        }
        //商品数量计数器（拓展）
        if(isAll){
            goodsCount = len;
        } else {
            goodsCount = 0;
        }
        //价格计算调用
        calculateTotalPrice();
        //刷新适配器
        notifyDataSetChanged();
    }
    //价格计算
    public void calculateTotalPrice(){
        boolean isAll = true;
        double sumPrice = 0;
        for (int i = 0; i < list.size(); i++) {
            DataBean.OrderDataBean o = list.get(i);
            isAll = o.isCheck() && isAll;
            for (int j = 0; j < o.getCartlist().size(); j++) {
                DataBean.OrderDataBean.CartlistBean cartlistBean = o.getCartlist().get(j);
                if(cartlistBean.isCheck()){
                    sumPrice += (cartlistBean.getPrice() * cartlistBean.getCount());
                }
            }
        }
        //结果回调
        if(onPriceChangeListener != null){
            onPriceChangeListener.onPriceChange(goodsCount,sumPrice,isAll);
        }
    }
    //寄存器
    class MyOrderDataListHouler {
        CheckBox shopName;
    }
    class MyCartListHouler {
        CheckBox check;
        ImageView defaultPic;
        TextView productName,color,price;
        AddReduceView count;
    }
    //总价回调和全选设置
    public interface OnPriceChangeListener {
        void onPriceChange(int goodsCount,double totalPrice,boolean isAll);
    }
}
