package com.codestates.seb.kdelivery.V3;

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

  public boolean isExistOrder(String customerName, String shopName, String foodName) {
    // 고객이름, 매장이름, 음식이름과 비교를 해서,
    // 논리값을 반환하는 것.
    // 세개가 다 같으면 true, 셋 중에 하나라도 아니면 false 를 반환함.
    return this.customerName.equals(customerName) &&
            this.shopName.equals(shopName) &&
            this.foodName.equals(foodName);
  }
}
