package com.codestates.seb.kdelivery.V2;

public class Feedback {
  private String customerName;
  private String shopName;
  private String foodName;
  private int grade;

  /**
   * @Feedback() : 정보를 저장합니다
   */
  public Feedback(String customerName, String shopName, String foodName, int grade) {
    this.customerName = customerName;  // 고객 이름을 클래스 변수에 저장합니다.
    this.shopName = shopName;          // 매장 이름을 클래스 변수에 저장합니다.
    this.foodName = foodName;          // 음식 이름을 클래스 변수에 저장합니다.
    this.grade = grade;                // 평점을 클래스 변수에 저장합니다.
  }
  // 고객 이름을 반환하는 메서드
  public String getCustomerName() {
    return customerName;
  }

  // 매장 이름을 반환하는 메서드
  public String getShopName() {
    return shopName;
  }

  // 음식 이름을 반환하는 메서드
  public String getFoodName() {
    return foodName;
  }


  /**
   * @getStars() : 사용자가 입력한 점수가 별점으로 전환
   */
  private String getStars() {
    String star = "";
    for (int i = 0; i < this.grade ; i ++) {
      star += "★";
    }
    return star;
  }

  /**
   * @printInfo() : 출력
   */
  public void printInfo() {
    System.out.println(this.customerName + " [고객님]");
    System.out.println("-------------------------");
    System.out.println("주문 매장 : " + this.shopName);
    System.out.println("주문 메뉴 : " + this.foodName);
    System.out.println("별점 : " + getStars());
  }

}
