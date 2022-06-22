public class MemberInfo {
    private String uid, pwd, name;
    public MemberInfo(String uid, String pwd, String name){
        this.uid = uid;
        this.pwd = pwd;
        this.name = name;
    }

    public String getUid(){ return uid; }
    public String getPwd(){ return pwd; }
    public String getName(){ return name; }
}
