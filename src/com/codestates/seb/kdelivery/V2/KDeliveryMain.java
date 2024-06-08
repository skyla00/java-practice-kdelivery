package com.codestates.seb.kdelivery.V2;

import java.util.Arrays;
import java.util.Scanner;

// 클래스를 정의 합니다.
public class KDeliveryMain {
  /**
   * 음식점 등록 개수, 음식 주문 가능 횟수, 리뷰 등록 가능 횟수 정의
   */
  private static int SHOP_MAX = 5;
  private static int ORDER_MAX = 5;
  private static int FEEDBACK_MAX = ORDER_MAX;
  /**
   * 배열을 담을 수 있는 객체 생성
   * 사용 범위, 객체 타입, 객체 이름
   */
  private Shop[] shops;
  private Order[] orders;
  private Feedback[] feedbacks;

  // 해당 변수를 제어하는 Idx변수를 정의하고 초기화


  private Scanner s; // 사용자의 입력을 받는 객체 생성

  /**
   * @KDeliveryMainV1() : 매장 정보, 주문 정보, 리뷰 정보 초기화
   * initValues() 메서드 사용
   */
  public KDeliveryMain() {
    this.s = new Scanner(System.in);
    initValues();
  }

  /**
   * @initValues() : 객체에 저장될 수 있는 크기 지정
   * SHOP_MAX, ORDER_MAX, FEEDBACK_MAX = 5
   */
  private void initValues() {
    this.shops = new Shop[SHOP_MAX];
    this.orders = new Order[ORDER_MAX];
    this.feedbacks = new Feedback[FEEDBACK_MAX];
  }

  /**
   * @close() : 프로그램 종료를 위해 사용되는 메서드
   * 사용자가 종료를 선언하면 동작합니다.
   * main()에서 활용됩니다.
   */
  public void close() {
    s.close();
  }

  /**
   * selectMainMenu() : 기능을 나열하며, 사용자가 원하는 기능을 정수로 받습니다.
   */
  public int selectMainMenu() {
    System.out.println("  [치킨의 민족 프로그램 V1] ");
    System.out.println("-------------------------");
    System.out.println("1) [사장님용] 음식점 등록하기");
    System.out.println("2) [고객님과 사장님용] 음식점 별점 조회하기");
    System.out.println("3) [고객님용] 음식 주문하기");
    System.out.println("4) [고객님용] 별점 등록하기");
    System.out.println("5) 프로그램 종료하기");
    System.out.println("-------------------------");
    System.out.println("[시스템] 무엇을 도와드릴까요?");
    // 사용자 입력은 string 으로 받고
    String choiceNumber = s.nextLine();

    // 반환하기 위한 값은 int로 받아야 함.
    int choice = 6;
    // 숫자인지 아닌지 확인함.
    // 만약 숫자가 아니라면
    if(!isValidNumber(choiceNumber)) {
      // 숫자만 입력하라는 메시지를 출력
      System.out.println("[시스템] 숫자만 입력해야 합니다.");
    } else {
      // String 으로 받은 값을 Int 타입으로 변환.
      choice = Integer.parseInt(choiceNumber);
    }
    return choice;
  }

  /**
   * 1) [사장님용] 음식점 등록하기
   *
   * @selectAddShopMenu() : 음식점의 정보를 등록합니다.
   * @shops : 가게 정보를 저장합니다.
   * @shopIdx : 가게 정보의 인덱스
   */
  public void selectAddShopMenu() {
    // 음식점 상호 받기
    System.out.println("[안내] 반갑습니다. 가맹주님!");
    System.out.println("[안내] 음식점 상호는 무엇인가요?");
    System.out.print(">>>");
    String shopName = s.nextLine();

    // 메뉴 이름 입력 받기
    System.out.println("[안내] 대표 메뉴 이름은 무엇인가요?");
    System.out.print(">>>");
    String foodName = s.nextLine();

    // 가격 입력 받기
    System.out.println("[안내] 해당 메뉴 가격은 얼마인가요?");
    System.out.print(">>>");

    // 숫자인지 아닌지 판단하기.
    String priceStr = s.nextLine();
    int price;
    // 받은 문자열이 숫자 이면, 타입을 int로 전환하기.
    if(isValidNumber(priceStr)) {
      price = Integer.parseInt(priceStr);
    } else {
      // 아니면 숫자를 입력하라는 메시지를 출력.
      System.out.println("[시스템] 숫자만 입력해야 합니다.");
      return;
    }

    /**
     * @Shop.java 의 Shop 클래스를 활용한 객체 생성
     * @public 클래스 : 동일 패키지 및 다른 패키지에서 사용가능
     *
     */

    // 1. shops 배열이 비어있는 Index가 있는지 확인함.
    int currentIndex = isValidIndex(shops);

    // 2. 만약에 shops 배열이 비어있는 Index가 있으면
    if (currentIndex != -1) {
      boolean isAddFood = true;
      // 2-1. 기존에 있는 shop 이름하고, 등록할 shopName 이 같은 Index가 있는지 확인함.
      int shopIndex = getShopIndex(shops, currentIndex, shopName);

      // 2-2. 같은 Index가 있으면
      if (shopIndex != -1) {
        Shop existShop = shops[shopIndex];
        // 2-2-1. 존재하는 Shop에 addFood를 함.
        isAddFood = existShop.addFood(foodName, price);
      } else {
        // 2-2-2. 아니면, shops의 현재 Index에 새로운 shopName을 넣어주어 객체 생성.
        shops[currentIndex] = new Shop(shopName);
        // 2-2-3. 그리고 음식 추가까지 함.
        shops[currentIndex].addFood(foodName, price);
      }
      // 음식추가가 되었으면 되었다고 반환하는 메시지 출력.
      // 가게 등록까지 완료.
      if (isAddFood) {
        System.out.printf("[안내] %s에 음식(%s, %d) 추가되었습니다.", shopName, foodName, price);
        System.out.println("[시스템] 가게 등록이 정상 처리되었습니다.");
      }
    } else {
      // shops 배열이 비어있지 않으면 가게를 등록할 수 없다는 메시지 출력.
      System.out.println("[시스템] 더 이상 새로운 가게를 등록할 수 없습니다.");
    }


  }

  /**
   * @selectDashboardMenu() : 해당 메서드는 등록된 가게 정보를 출력합니다.
   * Feedback.java 파일의 클래스 및 메서드를 활용합니다.
   */
  //
  public void selectDashboardMenu() {
    // feedback의 배열에 아무것도 안들어 가 있으면
    // 애초에 index 0이 Null 임.
    if (feedbacks[0] == null) {
      // 아무것도 없을 때 등록된 평가가 없다는 메시지 출력.
      System.out.println("[시스템] 현재 등록된 평가가 없습니다.");
    } else {
      // 배열에 feedback 이 들어가 있으면
      for (Feedback feedback : feedbacks) {
        // 만약에 Feedback 이 Null 값이면,
        if (feedback == null) {
          // For문을 멈추고, 반복문에서 완전히 벗어닌다.
          break;
        }
        //null 값이 아니라면,
        feedback.printInfo();
        // printInfo() 를 해라.
      }
      // Null 값일 때 break를 하면 이리로 와서. 끝남.
      // break: 가장 가까이에 있는 하나의 반복문을 벗어나기 위해 사용함.
      // 만약에 return을 넣었다면 쓰여진 해당 함수에서의 탈출을 의미.
          // 반복문을 포함하는 메서드 자체를 종료시킴.
    }
  }


  /**
   * @selectOrderMenu() : 주문 기능
   * 사용자의 입력을 받아 orders 객체에 저장
   */
  public void selectOrderMenu() {
    // 주문 정보 입력 받기
    System.out.println("[안내] 고객님! 메뉴 주문을 진행하겠습니다!");
    System.out.println("[안내] 주문자 이름을 알려주세요!");
    System.out.println(">>>");
    String customerName = s.nextLine();  // 주문자 이름 입력 받기

    System.out.println("[안내] 주문할 음식점 상호는 무엇인가요?");
    System.out.println(">>>");
    String shopName = s.nextLine();  // 음식점 이름 입력 받기

    System.out.println("[안내] 주문할 메뉴 이름을 알려주세요!");
    System.out.println(">>>");
    String foodName = s.nextLine();  // 메뉴 이름 입력 받기

    int currentIndex = isValidIndex(orders);

    // 1. Orders에 주문할 내용이 존재하는지 확인하는 메서드를 만들어서,
    // 없으면 주문 자체가 불가능 하도록 함.
    if (currentIndex != -1) {

      // shops 라는 배열에서, isValidIndex(shops)로 비어있는 shops의 값을 maxLength로 한 뒤에
      // 순회를 하면서 입력된 shopName이 기존 shops에 있는 shopName하고 같은 게 있는지
      // 확인하도록 함.
      int shopIndex = getShopIndex(shops, isValidIndex(shops), shopName);
      // 가게 이름이 존재한다는 뜻.
      if (shopIndex != -1) {
        // 2. 메뉴가 있는지 확인하는 메서드.
        // 현재 해당하는 shopIndex의 shop 배열에 foodName이 같은 게 있는지 확인하는 것.
        if (isExistMenuName(shops[shopIndex], foodName)) {
          // 있으면 Order객체를 만들어라.
          Order order = new Order(customerName, shopName, foodName);
          // orders 배열에도 Order 객체를 넣어줘야 함.
          // 178 번 코드를 통해서 비어있는 Order index를 알게 되었음. >> currentIndex 값.
          orders[currentIndex] = order;
        } else {
          // 해당 메뉴가 존재하지 않으면! 메시지를 보냄.
          System.out.printf("[시스템] %s 가게에는 해당 메뉴가 존재하지 않습니다.%n", shopName);
          return;
        }
      } else {
        // 가게이름이 존재하지 않으면 메시지를 보냄.
        System.out.println("[시스템] 정확한 가게 이름을 입력해 주세요.");
        return;
      }
      // order 를 다 하고 나서 빠져나와서 주문 됐다는 걸 알려주는 메시지 출력.
      System.out.printf("[안내] %s님!", customerName);
      System.out.printf("[안내] %s 매장에 %s 주문이 완료되었습니다.", shopName, foodName);
    } else {
      // shops 배열이 이미 꽉 차있으면 더 이상 주문 할 수 없다는 메시지 출력.
      System.out.println("[시스템] 더 이상 주문을 할 수 없습니다.");
    }
  }

  /**
   * @selectFeedbackMenu() : 메뉴의 피드백을 입력받는 기능
   */
  public void selectFeedbackMenu() {
    System.out.println("[안내] 고객님! 별점 등록을 진행합니다.");
    System.out.println("[안내] 주문자 이름은 무엇인가요?");
    System.out.println(">>>");
    String customerName = s.nextLine();

    System.out.println("[안내] 음식점 상호는 무엇인가요?");
    System.out.println(">>>");
    String shopName = s.nextLine();

    System.out.println("[안내] 주문하신 음식 이름은 무엇인가요?");
    System.out.println(">>>");
    String foodName = s.nextLine();

    // 평점을 남기기 위해 기록한 내용이 orders 에 있는지 확인해야 함.
    // orders 배열에 customerName, shopName, foodName 이 같은게 없다면
    if (! isExistOrder(orders, customerName, shopName, foodName)) {
      // Feedback 을 받지 않고, 주문을 다시 확인하도록 함.
      System.out.println("[시스템] 주문을 다시 확인해주세요.");
      return;
    }
    //같은 게 있으면 빠져나와서 별점을 입력할 수 있도록 함.
    System.out.println("[안내] 음식 맛은 어떠셨나요? (1점 ~ 5점)");
    System.out.println(">>>");
    String gradeStr = s.nextLine();
    int grade;
    if (isValidNumber(gradeStr)) {
      // 숫자이면 int로 변환 함.
      grade = Integer.parseInt(gradeStr);
    } else {
      System.out.println("[시스템] 숫자만 입력해야 합니다.");
      return;
    }
    // order 내용, grade 숫자 확인까지 끝났으면
    // feedback 에 새로운 객체를 만들고,
    Feedback feedback = new Feedback(customerName, shopName, foodName, grade);
    // feedbacks 배열 중 비어있는 곳 인덱스를 확인함.
    int currentIndex = isValidIndex(feedbacks);

    // 인덱스가 비어있으면!
    if(currentIndex != -1) {
      // 최근 인덱스에 feedback 객체를 넣음.
      feedbacks[currentIndex] = feedback;
      System.out.println("[안내] 리뷰 등록이 완료되었습니다.");
    } else {
      // feedback 에 더이상 넣을 곳이 없으면 리턴함.
      System.out.println("[시스템] 더 이상 리뷰 등록이 불가능합니다.");
    }
  }


  public static void main(String[] args) {
    KDeliveryMain kDeliveryMain = new KDeliveryMain();
    int count = 5;

    do {
      count = kDeliveryMain.selectMainMenu();
      switch (count) {
        case 1: {
          kDeliveryMain.selectAddShopMenu();
          break;
        }
        case 2: {
          kDeliveryMain.selectDashboardMenu();
          break;
        }
        case 3: {
          kDeliveryMain.selectOrderMenu();
          break;
        }
        case 4: {
          kDeliveryMain.selectFeedbackMenu();
          break;
        }
        case 5 : {
          System.out.println("아래는 상점 목록입니다.");
          System.out.println(Arrays.toString(kDeliveryMain.shops));
          for(Shop shop: kDeliveryMain.shops) {
            if(shop != null) {
              System.out.println(shop.getShopName());
              System.out.println(Arrays.toString(shop.getFoodNames()));
              System.out.println(Arrays.toString(shop.getPrices()));
            }
          }
          System.out.println("아래는 주문 목록입니다.");
          System.out.println(Arrays.toString(kDeliveryMain.orders));
          System.out.println("아래는 피드백 목록입니다.");
          System.out.println(Arrays.toString(kDeliveryMain.feedbacks));
        }
      }
    } while (count != 5); // 5 선택시 종료.

    kDeliveryMain.close();  // 리소스 정리
    System.out.println("[안내] 이용해주셔서 감사합니다.");  // 종료 메시지
  }

  // arr에 있는 ShopName 이랑 입력값이 같으면 그에 해당하는 인덱스를 데리고 와라
  public int getShopIndex(Shop[] arr, int maxLength, String shopName) {
    int currentIdx = -1;
    for (int i = 0; i < maxLength; i++) {
      if (arr[i].getShopName().equals(shopName)) {
        return i;
      }
    }
    /// 없으면 -1을 데리고 와라.
    return currentIdx;
  }

  // 객체 shop 과 foodName을 받음.
  public boolean isExistMenuName(Shop shop, String foodName) {
    // shop 에 있는 음식 이름을 menuArr 에 담아서
    String[] menuArr = shop.getFoodNames();
    // 순회하면서
    for (String str : menuArr) {
      // 외부에서 받은 foodName 이랑 같은 게 있으면
      if (str.equals(foodName)) {
        //true 를 반환해라.
        return true;
      }
    }
    // 같은게 없으면 false 를 반환해라.
    return false;
  }

  public boolean isExistOrder (Order[] orderArr, String customerName,
                               String shopName, String foodName) {
    for(Order order: orderArr) {
      // order이 null이면 false 반환
      if(order == null ) {
        return false;
      } else {

        // order 의 shopName, customerName, foodName 이 같아야 true를 리턴함.
        if(order.getShopName().equals(shopName) &&
                order.getCustomerName().equals(customerName) &&
                order.getFoodName().equals(foodName)) {
          return true;
        }
      }
    }
    // 셋 중에 하나라도 아니면 false 반환.
    return false;
  }


  //isValidIndex 메서드 : 입력된 배열의 첫번재 빈 위치를 찾아 반환합니다.
  public int isValidIndex(Object[] arr) {
    int currentIdx = -1;
    // 빈문자열이 없다.
    for (int i = 0; i < arr.length; i++) {
      // orders 를 받는 이유는
      if (arr[i] == null) {
        return i;
        //빈 문자열이 처음 나왔을 때 멈춰야 한다.
      }
    }
    return currentIdx;
  }

  // 받은 문자열이 문자 인지 아닌지 판별하는 메서드
  public boolean isValidNumber(String str) {
    // String 문자열을 char형 배열로 바꿔서 반환.
    char[] arr = str.toCharArray();
    // 숫자를 판별할 수 있도록 해주는 table.
    String table = "0123456789";

    // char로 변환한 값을 순회하면서
    for (char c : arr) {
      // 만약 c 의 값이 숫자가 아니면 false 를 반환.
      if(table.indexOf(c) == -1) return false;
    }
    // 숫자이면 true 를 반환.
    return true;
  }
}