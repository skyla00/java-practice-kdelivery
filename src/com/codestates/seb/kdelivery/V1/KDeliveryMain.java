package com.codestates.seb.kdelivery.V1;

import java.util.Scanner;

// 클래스를 정의 합니다.
public class KDeliveryMain {
  /**
   * 음식점 등록 개수, 음식 주문 가능 횟수, 리뷰 등록 가능 횟수 정의
   * */
  private static int SHOP_MAX = 5;
  private static int ORDER_MAX= 5;
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
   * */
  public KDeliveryMain() {
    this.s = new Scanner(System.in);
    initValues();
  }
  /**
   * @initValues() : 객체에 저장될 수 있는 크기 지정
   * SHOP_MAX, ORDER_MAX, FEEDBACK_MAX = 5
   * */
  private void initValues() {
    this.shops = new Shop[SHOP_MAX];
    this.orders = new Order[ORDER_MAX];
    this.feedbacks = new Feedback[FEEDBACK_MAX];
  }

  /**
   * @close() : 프로그램 종료를 위해 사용되는 메서드
   * 사용자가 종료를 선언하면 동작합니다.
   * main()에서 활용됩니다.
   * */
  public void close() {s.close();}

  /**
   * selectMainMenu() : 기능을 나열하며, 사용자가 원하는 기능을 정수로 받습니다.
   * */
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
    // 사용자 입력 받아 정수로 변환
    return Integer.parseInt(s.nextLine());
  }

  /** 1) [사장님용] 음식점 등록하기
   * @selectAddShopMenu() : 음식점의 정보를 등록합니다.
   * @shops   : 가게 정보를 저장합니다.
   * @shopIdx : 가게 정보의 인덱스
   * */
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
    int price = Integer.parseInt(s.nextLine());

    /**
     * @Shop.java 의 Shop 클래스를 활용한 객체 생성
     * @public 클래스 : 동일 패키지 및 다른 패키지에서 사용가능
     *
     */
    // shop 객체 생성
    Shop shop = new Shop(shopName);
    shop.addFood(foodName, price);

    int currentIndex = isShopIndex(SHOP_MAX);
    if(currentIndex != -1) {
      shops[currentIndex] = shop;
      System.out.printf("[안내] %s에 음식(%s, %d) 추가되었습니다.", shopName, foodName, price);
      System.out.println("[시스템] 가게 등록이 정상 처리되었습니다.");
    } else {
      System.out.println("[시스템] 더 이상 새로운 가게를 등록할 수 없습니다.");
    }



  }

  /**
   * @selectDashboardMenu() : 해당 메서드는 등록된 가게 정보를 출력합니다.
   * Feedback.java 파일의 클래스 및 메서드를 활용합니다.
   * */
  public void selectDashboardMenu() {
    if (feedbacks[0] == null ) {
      System.out.println("[시스템] 현재 등록된 평가가 없습니다.");
    } else {
      for (Feedback feedback : feedbacks) {

        if(feedback == null) {
          break;
        }
        feedback.printInfo();
        // feedback 을 도는 동안에 printInfo() 를 해라.
        // 근데 feedback 이 없으면 멈취라.
      }
    }
  }


  /**
   * @selectOrderMenu() : 주문 기능
   * 사용자의 입력을 받아 orders 객체에 저장
   * */
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

    Order order = new Order(customerName, shopName, foodName);
    int currentIndex = isOrderValidIndex(ORDER_MAX);
    if (currentIndex != -1) {
      orders[currentIndex] = order;
      System.out.printf("[안내] %s님!", customerName);
      System.out.printf("[안내] %s 매장에 %s 주문이 완료되었습니다.", shopName, foodName);
    }else {
      System.out.println("[시스템] 더 이상 주문을 할 수 없습니다.");
    }
  }

  /**
   * @selectFeedbackMenu() : 메뉴의 피드백을 입력받는 기능
   * */
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

    System.out.println("[안내] 음식 맛은 어떠셨나요? (1점 ~ 5점)");
    System.out.println(">>>");
    int grade = Integer.parseInt(s.nextLine());

    Feedback feedback = new Feedback(customerName, shopName, foodName, grade);
    int currentIndex = isOrderValidIndex(FEEDBACK_MAX);  // 유효한 인덱스 확인
    if(currentIndex != -1) {
      feedbacks[currentIndex] = feedback;  // 배열에 Feedback 객체 저장
      System.out.println("[안내] 리뷰 등록이 완료되었습니다.");
    } else {
      System.out.println("[시스템] 더 이상 리뷰 등록이 불가능합니다.");
    }
  }


  public static void main(String[] args) {
    KDeliveryMain kDeliveryMain = new KDeliveryMain();
    int count = 5;

    do {
      count = kDeliveryMain.selectMainMenu();
      switch (count) {
        case 1:  { kDeliveryMain.selectAddShopMenu(); break; }
        case 2:  { kDeliveryMain.selectDashboardMenu(); break; }
        case 3:  { kDeliveryMain.selectOrderMenu(); break; }
        case 4:  { kDeliveryMain.selectFeedbackMenu(); break; }
      }
    } while(count != 5); // 5 선택시 종료.

    kDeliveryMain.close();  // 리소스 정리
    System.out.println("[안내] 이용해주셔서 감사합니다.");  // 종료 메시지
  }

  //isValidIndex 메서드 : 입력된 배열의 첫번재 빈 위치를 찾아 반환합니다.
  public int isOrderValidIndex(int maxLength) {
    int currentIdx = -1;
    // 빈문자열이 없다.
    for (int i = 0; i < maxLength; i++) {
      // orders 를 받는 이유는
      if (orders[i] == null ) {
        currentIdx = i;
        break;
        //빈 문자열이 처음 나왔을 때 멈춰야 한다.
      }
    }
    return currentIdx;
  }

  public int isShopIndex(int maxLength) {
    int currentIdx = -1;
    // 빈문자열이 없다.
    for (int i = 0; i < maxLength; i++) {
      // orders 를 받는 이유는
      if (shops[i] == null ) {
        currentIdx = i;
        break;
        //빈 문자열이 처음 나왔을 때 멈춰야 한다.
      }
    }
    return currentIdx;
  }
}