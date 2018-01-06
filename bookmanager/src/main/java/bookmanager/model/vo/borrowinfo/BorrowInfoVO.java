package bookmanager.model.vo.borrowinfo;

/**
 * Created by dela on 1/6/18.
 */
public class BorrowInfoVO {
    private String date;
    private String owner;
    private String user;
    private String bookName;

    public BorrowInfoVO() { }

    public BorrowInfoVO(String date, String owner, String user, String bookName) {
        this.date = date;
        this.owner = owner;
        this.user = user;
        this.bookName = bookName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
