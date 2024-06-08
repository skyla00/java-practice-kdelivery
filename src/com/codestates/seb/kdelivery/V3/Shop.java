package com.codestates.seb.kdelivery.V3;

import java.util.*;

public class Shop {

  private String shopName;
  //메뉴명과 가격을 저장하는 맵.
  private Map<String, Integer> menus = new HashMap<>();

  /**
   * @Shop() : 생성자 정의
   * 매장만 먼저 입력받도록 합니다.
   * 나머지 변수는 initValues() 에서 정의합니다.
   */
  // 생성자: 매장 이름을 받음.
  // 매장 이름을 인스턴스 변수에 할당
  public Shop(String shopName) {
    this.shopName = shopName;
  }

  //Getter 생성.
  public String getShopName() {
    return shopName;
  }

  ;

  public Map<String, Integer> getMenus() {
    return menus;
  }

  /**
   * @addFood() : 새 음식과 가격을 메뉴에 추가함.
   * * 해당 메서드는 음식명, 가격을 입력받아 객체에 저장
   * * 값이 저장될 때 마다 shopIdx 값 증가
   */

  public boolean addFood(String foodName, int price) {
    //menus 맵에 있는 key 에 foodName 이랑 같은 음식이 있으면
    if (menus.containsKey(foodName)) {
      //false 를 반환한다.
      return false;
    } else {
      menus.put(foodName, price);
      return true;
    }
  }

  // isExistMenu : 특정 음식이 메뉴에 존재하는지 확인함.
  // 확인할 음식의 이름.
  // boolean 메뉴에 음식이 존재 하면 true, 존재하지 않으면 false를 반환한다.
  // containsKey () 가 boolean 값으로 반환해줌.
  // 그래서 바로 return 을 해도 됨.
  public boolean isExistMenu(String foodName) {
    return menus.containsKey(foodName);
  }

}
