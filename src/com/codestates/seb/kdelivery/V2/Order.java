package com.codestates.seb.kdelivery.V2;

public class Order {
  private String customerName;
  private String shopName;
  private String foodName;

  /**
  *@Order():주문 정보를 저장합니다.
  **/

  public Order(String customerName, String shopName, String foodName) {
    this.customerName = customerName;
    this.shopName = shopName;
    this.foodName = foodName;
  }

  public String getCustomerName() {
    return customerName;
  }

  public String getShopName() {
    return shopName;
  }

  public String getFoodName() {
    return foodName;
  }
}
