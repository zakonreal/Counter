import java.io.Serializable;

public class Counter implements Serializable {
    private static final long serialVersionUID = 1L;

    private int value;

    public void increment() {
        this.value++;
    }

    public void reset() {
        this.value = 0;
    }

    public int getValue() {
        return value;
    }
}