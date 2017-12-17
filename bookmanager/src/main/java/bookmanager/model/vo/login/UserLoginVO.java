package bookmanager.model.vo.login;

/**
 * Created by dela on 12/5/17.
 */
public class UserLoginVO {
    private int uid;
    private String name;
    private String password;

    public UserLoginVO() {

    }

    public UserLoginVO(int uid, String name, String password) {
        this.uid = uid;
        this.name = name;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginVO{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
