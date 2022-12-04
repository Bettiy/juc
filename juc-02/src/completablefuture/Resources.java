package completablefuture;

public class Resources {

    private String message = "";

    public String getMessage() {
        return message;
    }

    public synchronized void setMessage(String message) {
        this.message += message + "\t";
        System.out.println(Thread.currentThread().getName() + " " + this.message);
    }
}
