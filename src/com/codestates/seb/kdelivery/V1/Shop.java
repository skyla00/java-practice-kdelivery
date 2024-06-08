package com.codestates.seb.kdelivery.V1;

public class Shop {
  /**
   * 등록 가능한 음식 수, 음식 초기화 변수, 가격 필드 생성
   * 매장명, 메뉴명, 가격 필드 생성
   */
  private static final int FOOD_MAX = 5;
  private static final String EMPTY_FOOD = "";
  private static final int EMPTY_PRICE = 0;
  private String shopName;
  private String[] foodNames;
  private int[] prices;

  /**
   * @Shop() : 생성자 정의
   * 매장만 먼저 입력받도록 합니다.
   * 나머지 변수는 initValues() 에서 정의합니다.
   */
  public Shop(String shopName) {
    this.shopName = shopName;
    initValues();
  }

  /**
   * @initValues() : 메뉴명와 가격정보를 담는 배열 생성 및 초기화
   * EMPTY_FOOD = "", EMPTY_PRICE = 0
   */
  private void initValues() {
    foodNames = new String[FOOD_MAX];
    prices = new int[FOOD_MAX];
    for (int i = 0; i < foodNames.length; i++) {
      this.foodNames[i] = EMPTY_FOOD;
    }
  }

  /**
   * @addFood() : 위 코드에서 정의된 변수를 받아 출력과 객체에 저장합니다.
   * * 해당 메서드는 음식명, 가격을 입력받아 객체에 저장
   * * 값이 저장될 때 마다 shopIdx 값 증가
   */
  public void addFood(String foodName, int price) {
    int currentIdx = -1;
    // 빈문자열이 없다.
    for (int i = 0; i < foodNames.length; i++) {
      if (foodNames[i].equals(EMPTY_FOOD)) {
        currentIdx = i;
        break;
        //빈 문자열이 처음 나왔을 때 멈춰야 한다.
      }
    }
    if (currentIdx != -1) {
      foodNames[currentIdx] = foodName;
      prices[currentIdx] = price;
    }
  }
}
