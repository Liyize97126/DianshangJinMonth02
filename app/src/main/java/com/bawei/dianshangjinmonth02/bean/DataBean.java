package com.bawei.dianshangjinmonth02.bean;

import java.util.List;

/**
 * 数据处理类
 */
public class DataBean {
    private int code;
    private List<OrderDataBean> orderData;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public List<OrderDataBean> getOrderData() {
        return orderData;
    }
    public void setOrderData(List<OrderDataBean> orderData) {
        this.orderData = orderData;
    }
    public static class OrderDataBean {
        private int shopId;
        private String shopName;
        private boolean check;
        private List<CartlistBean> cartlist;
        public int getShopId() {
            return shopId;
        }
        public void setShopId(int shopId) {
            this.shopId = shopId;
        }
        public String getShopName() {
            return shopName;
        }
        public void setShopName(String shopName) {
            this.shopName = shopName;
        }
        public List<CartlistBean> getCartlist() {
            return cartlist;
        }
        public void setCartlist(List<CartlistBean> cartlist) {
            this.cartlist = cartlist;
        }
        public boolean isCheck() {
            return check;
        }
        public void setCheck(boolean check) {
            this.check = check;
        }
        public static class CartlistBean {
            private int id;
            private int shopId;
            private String shopName;
            private String defaultPic;
            private int productId;
            private String productName;
            private String color;
            private String meal;
            private double price;
            private int count;
            private boolean check;
            public int getId() {
                return id;
            }
            public void setId(int id) {
                this.id = id;
            }
            public int getShopId() {
                return shopId;
            }
            public void setShopId(int shopId) {
                this.shopId = shopId;
            }
            public String getShopName() {
                return shopName;
            }
            public void setShopName(String shopName) {
                this.shopName = shopName;
            }
            public String getDefaultPic() {
                return defaultPic;
            }
            public void setDefaultPic(String defaultPic) {
                this.defaultPic = defaultPic;
            }
            public int getProductId() {
                return productId;
            }
            public void setProductId(int productId) {
                this.productId = productId;
            }
            public String getProductName() {
                return productName;
            }
            public void setProductName(String productName) {
                this.productName = productName;
            }
            public String getColor() {
                return color;
            }
            public void setColor(String color) {
                this.color = color;
            }
            public String getMeal() {
                return meal;
            }
            public void setMeal(String meal) {
                this.meal = meal;
            }
            public double getPrice() {
                return price;
            }
            public void setPrice(double price) {
                this.price = price;
            }
            public int getCount() {
                return count;
            }
            public void setCount(int count) {
                this.count = count;
            }
            public boolean isCheck() {
                return check;
            }
            public void setCheck(boolean check) {
                this.check = check;
            }
        }
    }
}
