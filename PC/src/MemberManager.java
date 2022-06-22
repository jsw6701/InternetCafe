import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MemberManager {
    private MemberHandler handler = new MemberHandler(10);
    private int com=0;
    private int c =0,z=0,tmp=1;
    private int hour[] = new int[10];
    private int min[] = new int[10];
    private int sum[] = new int[10];
    private int total[] = new int[10];
    private int total2[] = new int[10];
    private boolean reg[] = {false,false,false,false,false,false,false,false,false,false};
    private boolean reg2[] = {false,false,false,false,false,false,false,false,false,false};
    private String reg3[] = {"","","","","","","","","",""};
    private String signup[] = new String[10];
    BufferedReader ad = new BufferedReader(new InputStreamReader(System.in));

    public void signUp(){
        handler.memberInsert();
        signup[tmp] = handler.getUids();
        tmp++;
    }
    public void register() throws IOException
    {
        int com=0;
        System.out.print("몇대의 컴퓨터를 등록하시겠습니까? : ");
        try{
            com = Integer.parseInt(ad.readLine());
            this.com += com;
        }catch(Exception e)
        {
            System.out.println("올바르지 않은 입력입니다.");
        }
        for(int a =0+z; a<com+z; a++)
        {
            reg[a] = true;
            c++;
        }
        z=c;
    }

    public void start(int hour,int min){
        int num =0;
        while(true){
            System.out.print("사용하려는 컴퓨터 번호를 누르세요. : ");
            try{
                num = Integer.parseInt(ad.readLine());
            }catch(Exception e)
            {
                System.out.println("잘못된 입력입니다.");
            }
            if(com == 0)
            {System.out.println("등록된 컴퓨터가 없습니다.");
                break;}
            else if(num>com)
            {System.out.println("등록되지 않은 컴퓨터 번호입니다.");}
            else if(reg2[num]==true)
            {System.out.println("이미 시작된 컴퓨터 입니다.");break;}
            else
            {
                handler.memberLogin();
                System.out.println(num+"번 컴퓨터의 사용을 시작합니다.");
                System.out.println("사용시작 시간 :"+hour+"시"+min+"분");
                this.sum[num] = (hour*60)+min;
                this.reg2[num] = true;
                this.reg3[num] = handler.getUids();

                break;
            }
        }
    }

    public void stop(int hour,int min){
        int lastsum = (hour*60)+min;
        int num=0;
        int price=0;;
        System.out.print("종료하려는 컴퓨터 번호를 누르세요. : ");

        try{
            num = Integer.parseInt(ad.readLine());
        }catch(Exception e)
        {
            System.out.println("잘못된 입력입니다.");
        }
        if(reg2[num]==true){
            price = (lastsum-this.sum[num])*100;
            System.out.println("종료시간 : "+hour+"시"+min+"분");
            System.out.println(num+"번 컴퓨터를 사용하신 "+ reg3[num] + "고객님은 :"+price+"원 입니다.");
            this.total[num] += price;
            this.reg2[num] = false;
            for(int i =0; i<10; i++){
                if(reg3[num].equals(signup[i])){
                    total2[i] += price;
                }
            }
            this.reg3[num] = "";
        }
        else
        {System.out.println(num+"번 컴퓨터는 시작되지 않았습니다.");}
    }

    public void infor(){
        int num = 0;
        System.out.println("컴퓨터 매출 정보입니다.");
        for(int i = 0 ;i <10; i++)
        {
            if(reg[i]==true)
                System.out.println(i+"번 컴퓨터의 총 매출은 :"+total[i]+"입니다.");
        }
    }

    public void info(){
        System.out.println("등록된 컴퓨터 정보 입니다.");
        for(int i = 0; i <10; i++)
        {
            if(reg[i]==true)
                if(reg3[i].equals("")) System.out.println("컴퓨터 번호 : "+i+" 로그인 중인 회원이 없습니다.");
                else System.out.println("컴퓨터 번호 : "+i+" 현재 로그인 되어있는 아이디 : " + reg3[i]);
        }
    }
    public void totalU(){
        String tmp ="";
        System.out.print("검색하실 회원님의 아이디를 입력하시오 : ");
        Scanner sc = new Scanner(System.in);
        tmp = sc.next();
        for(int i=1; i<10; i++){
            if(tmp.equals(signup[i])){
                if(total2[i] == 0){
                    System.out.println(signup[i] + " 회원님은 결제할 내역이 없습니다.");
                }
                else{
                    System.out.print(signup[i] + " 회원님이 내셔야 할 총금액입니다 : ");
                    System.out.println(total2[i] + "원");
                    total2[i] = 0;
                    System.out.println(signup[i] + " 회원님 결제가 완료되었습니다.");
                }

            }
        }
    }
    public void searchId(){
        handler.searchId();
    }
    public void searchPwd(){
        handler.searchPwd();
    }
    public String getReg3(int i) {
        return reg3[i];
    }
}