package com.codestates.seb.kdelivery.V2;

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
  // 새 음식을 메뉴에 추가함.
  // 중복된 음식 이름이 없을 때에만 배열에 추가함.
  // 성공, 실패를 boolean 값으로 반환함.
  public boolean addFood(String foodName, int price) {
    int currentIdx = -1;
    // foodNames 배열을 순회하면서
    for (int i = 0; i < foodNames.length; i++) {
      // 같은 이름의 음식이 이미 등록되어 있으면,
      if (foodNames[i].equals(foodName)) {
        // 같은 이름의 상품이 있다는 메시지 출력과 함께,
        System.out.println("[시스템] 같은 이름의 상품은 등록할 수 없습니다.");
        // false 를 리턴함.
        return false;
        // 같은 이름의 음식이 등록되어 있지 않는 비어있는 위치 찾기.
      } else if (foodNames[i].equals("")) {
        // 해당하는 Idx 값 반환하기.
        currentIdx = i;
        // for 문 탈출.
        break;
      }
    }
    // 비어있는 배열의 ids 값을 찾았으면,
    if (currentIdx != -1) {
      // 음식명과
      foodNames[currentIdx] = foodName;
      // 가격을 저장함.
      prices[currentIdx] = price;
      // 저장에 성공했다는 True 값 반환.
      return true;
    } else {
      // 비어있는 배열 위치가 없으면,
      System.out.println("[시스템] 더 이상 상품을 추가할 수 없습니다.");
      // 실패 반환.
      return false;
    }
  }

  public String getShopName() {
    return shopName;
  }

  public String[] getFoodNames() {
    return foodNames;
  }

  public int[] getPrices() {
    return prices;
  }
}
