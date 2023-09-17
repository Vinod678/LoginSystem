import java.util.HashMap;

public class IDandPasswords {
    HashMap<String,String> loginInfo = new HashMap<String,String>();

    IDandPasswords(){
        loginInfo.put("userName1","pswd1");
        loginInfo.put("a","1");
        loginInfo.put("userName2","pswd2");
        loginInfo.put("userName3","pswd3");
    }

    HashMap getLoginInfo(){
        return loginInfo;
    }
}
