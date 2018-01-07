package bookmanager.model.vo.borrowinfo;

/**
 * Created by dela on 1/6/18.
 */
public class BorrowInfoVO {
    private String borrow_date;
    private String name;
    private String ugk_name;

    public BorrowInfoVO() { }

    public BorrowInfoVO(String borrow_date, String name, String ugk_name) {
        this.borrow_date = borrow_date;
        this.name = name;
        this.ugk_name = ugk_name;
    }

    public String getBorrow_date() {
        return borrow_date.substring(0, 19);
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUgk_name() {
        return ugk_name;
    }

    public void setUgk_name(String ugk_name) {
        this.ugk_name = ugk_name;
    }

    @Override
    public String toString() {
        return "BorrowInfoVO{" +
                "date='" + getBorrow_date() + '\'' +
                ", user='" + name + '\'' +
                ", ugk_name='" + ugk_name + '\'' +
                '}';
    }
}
