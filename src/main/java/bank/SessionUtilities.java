package bank;

public class SessionUtilities extends PropertyFileUtility{
    public static int session_id=-1;
    protected void createSession(String username ,String password){
        String adminUsername=getConfigProperties("admin_username");
        String adminPassword=getConfigProperties("admin_password");
        if(adminUsername.equals(username)&&adminPassword.equals(password)) {
            session_id=0;
        }
        else if(new CredDetails().createSession(username,password)!=-1){
            session_id=new CredDetails().createSession(username,password);
        }
        else
            System.out.println("You have entered a wrong credential");
    }

    protected void destroySession(){
        session_id=-1;
        System.out.println("Session destroyed");
    }
}