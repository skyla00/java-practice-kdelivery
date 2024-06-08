package com.codestates.seb.kdelivery.V3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// 클래스를 정의 합니다.
public class KDeliveryMain {
  // Arraylist로 객체를 받을 수 있도록 함.
  // 배열에서 필요했던, 등록 가능 횟수는 없어도 됨.
  // initValues()로 배열 초기화도 안해도 됨.
  private ArrayList<Shop> shops = new ArrayList<>();
  private ArrayList<Order> orders = new ArrayList<>();
  private ArrayList<Feedback> feedbacks = new ArrayList<>();

  private Scanner s; // 사용자의 입력을 받는 객체 생성

  /**
   * @KDeliveryMainV1() : 매장 정보, 주문 정보, 리뷰 정보 초기화
   * initValues() 메서드 사용
   */
  public KDeliveryMain() {
    this.s = new Scanner(System.in);
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
    if(!isValidNumber(choiceNumber)) {
      System.out.println("[시스템] 숫자만 입력해야 합니다.");
    } else {
      // string 으로 받은 값을 정수로 변환.
      choice = Integer.parseInt(choiceNumber);
    }
    return choice;
  }

  /**
   * 1) [사장님용] 음식점 등록하기
   *
   * @selectAddShopMenu() : 음식점의 정보를 등록합니다
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
    // shops ArrayList에 입력된 shopName이랑 같은 게 있는지 확인.
    int shopIndex = getShopIndex(shops, shopName);
    // 있으면 index 값 반환.

    //같은 가게 이름이 있으면
    if (shopIndex != -1) {
      boolean isValid = shops.get(shopIndex).addFood(foodName, price);
      // addFood() : menus 맵에 foodName, price 를 넣는 것.
      // 같은 foodName이 있으면 false, 아니면 menus에 넣음.
      if (!isValid) {
        // 같은 foodName이 있을 때. 같은 이름의 메뉴는 추가할 수 없다는 메시지 출력.
        System.out.println("[시스템] 동일한 이름의 메뉴를 추가할 수 없습니다.");
        // 이 메서드를 나가야 함.
        return;

      } else {
        // 아니면, 같은 가게 이름이 없는 것. 가게 이름을 넣어야 하고,
        // 새로운 가게 객체를 생성해서, arraylist에 넣기.
        Shop shop = new Shop(shopName);
        shops.add(shop);
        // 음식 이름도 넣어야 함.
        shop.addFood(foodName, price);
      }
    }

    System.out.printf("[안내] %s에 음식(%s, %d) 추가되었습니다.%n", shopName, foodName, price);
    System.out.println("[시스템] 가게 등록이 정상 처리되었습니다.");

  }

  /**
   * @selectDashboardMenu() : 해당 메서드는 등록된 가게 정보를 출력합니다.
   * Feedback.java 파일의 클래스 및 메서드를 활용합니다.
   */
  public void selectDashboardMenu() {
    if (feedbacks.size() == 0) {
      System.out.println("[시스템] 현재 등록된 평가가 없습니다.");
    } else {
      for (Feedback feedback : feedbacks) {

        if (feedback == null) {
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

    // shops ArrayList에 입력된 shopName이랑 같은 게 있는지 확인.
    int shopIndex = getShopIndex(shops, shopName);
    // 있으면 index 값 반환.

    //입력된 괎과 같은 가게 이름이 있으면
    if (shopIndex != -1) {
      // 입력된 값과 같은 메뉴가 있는지 확인.
      if (shops.get(shopIndex).isExistMenu(foodName)) {
        // 같은 게 있으면 order 객체를 생성.
        Order order = new Order(customerName, shopName, foodName);
        orders.add(order);
      } else {
        // 같은 메뉴가 없으면 메뉴가 없다는 메시지를 출력하고, 메서드 종료.
        System.out.printf("[시스템] %s 가게에는 해당 메뉴가 존재하지 않습니다.%n", shopName);
        return;
      }
      // Order 객체를 넣고, arraylist에 잘 넣었다면, 잘 됐다는 메시지 출력.
      System.out.printf("[안내] %s에 음식%s이 추가되었습니다.%n", shopName, foodName);
      System.out.println("[시스템] 가게 등록이 정상 처리되었습니다.");
    } else {
      // 입력된 값과 같은 가게 이름이 없으면 !
      System.out.println("[시스템] 해당 점포가 존재하지 않습니다.");
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

    // isChecked 변수 : order 내역이 있는지 확인했다는 걸 알려주기 위한 변수.
    // 찾았으면 true, 못찾았으면 false 를 반환할 것.
    boolean isChecked = false;
    for (Order order : orders) {
      if (order.isExistOrder(customerName, shopName, foodName)) {
        isChecked = true;
      }
    }
    // 못 찾았으면??????
    // !isChecked 인지, isChecked 인지.
    // false 로 흘러 내려 왔어. > 주문이 틀렸단 얘기.
    // 주문을 확인하라는 멘트를 줘야 되는데,
    // if 안의 조건식은 true일 때만 실행되니까.
    // true로 바꿔주려고 !를 쓰는 것임.
    if(!isChecked) {
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
    // order 내용, Grade 숫자 확인까지 끝났으면
    // feedback 에 새로운 객체를 만들고,
    Feedback feedback = new Feedback(customerName, shopName, foodName, grade);
    // arraylist feedbacks 에 넣음.
    feedbacks.add(feedback);
    // 등록이 잘 되었다는 걸 출력.
    System.out.println("[안내] 리뷰 등록이 완료되었습니다.");
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
          System.out.println("아래는 상점과 메뉴 목록입니다.");
          for(Shop shop: kDeliveryMain.shops) {
              System.out.println(shop.getShopName());
              System.out.println(shop.getMenus());
          }
          System.out.println("아래는 주문 목록입니다.");
          System.out.println(kDeliveryMain.orders);
          System.out.println("아래는 피드백 목록입니다.");
          System.out.println(kDeliveryMain.feedbacks);
        }
      }
    } while (count != 5); // 5 선택시 종료.

    kDeliveryMain.close();  // 리소스 정리
    System.out.println("[안내] 이용해주셔서 감사합니다.");  // 종료 메시지
  }

  public int getShopIndex(ArrayList<Shop> shopList, String shopName) {
    int currentIdx = -1;
    for (int i = 0; i < shopList.size(); i++) {
      // shopList라는 ArrayList에서 get()로 해당 인덱스에 있는 객체를 데리고 와서
      // getShopName()으로 그 객체의 shopName을 데려와서
      // .equals() 로 비교하라.
      if (shopList.get(i).getShopName().equals(shopName)) {
        return i;
      }
    }
    /// 없으면 -1을 데리고 와라.
    return currentIdx;
  }

  public boolean isValidNumber(String str) {
    if(str.isEmpty()) return false;
    char[] arr = str.toCharArray();
    String table = "0123456789";

    for (char c : arr) {
      if(table.indexOf(c) == -1) return false;

    }
    return true;
  }
}