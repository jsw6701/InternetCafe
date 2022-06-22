import java.util.*;
public class MemberHandler {
    private MemberInfo[] members;
    private int idx;
    private int tmp;

    public MemberHandler(int num){
        members = new MemberInfo[num];
        idx = 0;
        tmp = 0;
    }

    public void memberInsert(){
        String uid, pwd, name;
        Scanner sc = new Scanner(System.in);
        System.out.print("아이디 : "); uid = sc.nextLine();
        if (!isUniqueID(uid))
        {
            System.out.println("이미 사용중인 아이디 입니다. \n");
            return;
        }
        System.out.print("암 호 : "); pwd = sc.nextLine();
        System.out.print("이 름 : "); name = sc.nextLine();

        members[idx] = new MemberInfo(uid, pwd, name);
        idx++;
        System.out.println("가입 완료!! \n");
    }
    public void searchId(){
        String fname = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("이름을 입력하시오 : ");
        fname = sc.next();
        for(int i =0; i<idx; i++){
            if(members[i].getName().equals(fname)){
                System.out.println(members[i].getName() + "님의 아이디는 :" + members[i].getUid());
            }
            else{
                System.out.println("회원가입 된 이름과 일치하는 이름이 없습니다.");
            }
        }
    }
    public void searchPwd(){
        String fname = "";
        String fuid = "";
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        System.out.print("이름을 입력하시오 : ");
        fname = sc.next();
        System.out.print("아이디를 입력하시오 : ");
        fuid = sc1.next();
        for(int i =0; i<idx; i++){
            if(members[i].getName().equals(fname)){
                if(members[i].getUid().equals(fuid)){
                    System.out.println(members[i].getName() + "님의 비밀번호는 :" + members[i].getPwd());
                }
                else{
                    System.out.println("회원가입 된 아이디와 일치하는 아이디가 없습니다.");
                }
            }
            else{
                System.out.println("회원가입 된 이름과 일치하는 이름이 없습니다.");
            }
        }
    }
    private boolean isUniqueID(String uid){
        if (idx == 0) return true;

        for (int i = 0 ; i < idx ; i ++)
        {
            if (members[i].getUid().equals(uid))
            {
                return false;
            }
        }
        return true;
    }

    public void memberLogin(){
        Scanner sc = new Scanner(System.in);
        System.out.print("아이디 : "); String uid = sc.nextLine();
        System.out.print("패스워드 : "); String pwd = sc.nextLine();
        String msg = "존재하지 않는 아이디 입니다.";

        for (int i = 0 ; i < idx ; i++ )
        {
            if (members[i].getUid().equals(uid))
            {
                if (members[i].getPwd().equals(pwd))
                {
                    tmp = 0;
                    msg = "로그인 되었습니다.";
                    tmp = i;
                } else{
                    msg = "암호가 잘못되었습니다.";
                }
            }
        }
        System.out.println(msg);
    }

    public String getUids() {
        return members[tmp].getUid();
    }

}
