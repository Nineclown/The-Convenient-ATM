public class Admin {
    private String id;
    private String password;
    private String contact;

    public Admin(String id, String password, String contact) {
        this.id = id;
        this.password = password;
        this.contact = contact;
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getContact() {
        return this.contact;
    }

    public boolean checkAdminAccount(String adminID, String adminPW)
    {
        if(this.id.equals(adminID))
        {
            if(this.password.equals(adminPW))
            {
                return true;
            }
            else
            {
                java.lang.System.out.println("비밀번호가 틀립니다");
                return false;
            }
        }
        else
        {
            java.lang.System.out.println("일치하는 ID가 없습니다");
            return false;
        }
    }
}
