import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class PcMain {
    public static void main(String[] args) throws IOException
    {
        BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
        int menu=0, menu2=0, select = 0;
        Calendar date3 = Calendar.getInstance();
        boolean bool=true, bool2=true, bool3 = true;
        MemberManager ad = new MemberManager();
        FoodManager manager = new FoodManager("food.txt");
        while (bool3){
            System.out.println("=========================");
            System.out.println("0.pc방 메뉴");
            System.out.println("1.음식 코너");
            System.out.println("2.종료");
            System.out.println("=========================");
            System.out.print("입력 : ");
            Scanner sc = new Scanner(System.in);
            select = sc.nextInt();
            if(select == 0){
                while(bool){
                    System.out.println("=========================");
                    System.out.println("0.회원가입");
                    System.out.println("1.컴퓨터 등록");
                    System.out.println("2.등록된 컴퓨터 정보보기(로그인 정보 포함)");
                    System.out.println("3.컴퓨터 로그인");
                    System.out.println("4.컴퓨터 로그아웃");
                    System.out.println("5.컴퓨터 매출 정보 출력");
                    System.out.println("6.고객님이 지불해야하는 가격과 결제");
                    System.out.println("7.아이디 찾기");
                    System.out.println("8.비밀번호 찾기");
                    System.out.println("9.메인 메뉴로 돌아가기");
                    System.out.println("=========================");
                    System.out.print("입력 : ");
                    try{
                        menu = Integer.parseInt(dis.readLine());
                    }catch(Exception e)
                    {
                        System.out.println("올바르지않은 입력 입니다");
                    }
                    switch(menu)
                    {
                        case 0 :
                            ad.signUp();
                            break;
                        case 1 :
                            ad.register();
                            break;
                        case 2 :
                            ad.info();
                            break;
                        case 3 :
                            date3 = Calendar.getInstance();
                            ad.start(date3.get(Calendar.HOUR),date3.get(Calendar.MINUTE));
                            break;
                        case 4 :
                            date3 = Calendar.getInstance();
                            ad.stop(date3.get(Calendar.HOUR),date3.get(Calendar.MINUTE));
                            break;
                        case 5 :
                            ad.infor();
                            break;
                        case 6 :
                            ad.totalU();
                            break;
                        case 7 :
                            ad.searchId();
                            break;
                        case 8 :
                            ad.searchPwd();
                            break;
                        case 9 :
                            bool =false;
                    }
                }
                bool = true;
            }
            else if(select == 1){
                while (bool2){
                    System.out.println("=========================");
                    System.out.println("0.음식 메뉴보기");
                    System.out.println("1.음식 메뉴추가");
                    System.out.println("2.음식 회원 장바구니에 담기");
                    System.out.println("3.회원 장바구니 금액확인과 결제");
                    System.out.println("4.메인 메뉴로 돌아가기");
                    System.out.println("=========================");
                    System.out.print("입력 : ");
                    Scanner sc1 = new Scanner(System.in);
                    menu2 = sc1.nextInt();
                    manager.load();
                    switch (menu2){
                        case 0 :
                            manager.show();
                            manager.clear();
                            break;
                        case 1 :
                            String name = "";
                            int price = 0;
                            System.out.print("메뉴 이름 : ");
                            Scanner tname = new Scanner(System.in);
                            name = tname.next();
                            System.out.print("메뉴 가격 : ");
                            Scanner tprice = new Scanner(System.in);
                            price = tprice.nextInt();
                            manager.add(new Food(name, price));
                            manager.save();
                            break;
                        case 2 :
                            manager.addUser();
                            String uname = "";
                            System.out.print("음식을 담을 고객아이디 : ");
                            Scanner sc2 = new Scanner(System.in);
                            uname = sc2.next();
                            boolean bool4 = false;
                            for(int i=0; i<10; i++){
                                if(ad.getReg3(i).equals(uname)){
                                    manager.addUser1(uname);
                                    bool4 = true;
                                }
                            }
                            if(bool4 == false){
                                System.out.println("회원님이 로그인 되어 있지 않습니다.");
                            }
                            manager.clear();
                            break;
                        case 3:
                            System.out.print("아이디를 입력하세요 : ");
                            String cname = "";
                            Scanner sc3 = new Scanner(System.in);
                            cname = sc3.next();
                            manager.showcart(cname);
                            break;
                        case 4 :
                            bool2 = false;
                    }
                }
                bool2 = true;
            }
            else if(select == 2){
                bool3 = false;
            }
            else{
                System.out.println("잘못입력.");
                bool3 = false;
            }
        }
    }
}
