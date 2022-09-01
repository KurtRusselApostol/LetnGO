package login.system;

//This class acts like a temporary storage for the shared preference
public class User {
    String ema, pass;

    public User() {

    }

    public User(String ema, String pass) {
        this.ema = ema;
        this.pass = pass;
    }

    public String getEma() {
        return ema;
    }

    public void setEma(String ema) {
        this.ema = ema;
    }

    public String getPass() { return pass; }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
