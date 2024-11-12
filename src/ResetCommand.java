public class ResetCommand implements CommandProcessor {
    @Override
    public void process(Counter counter) {
        counter.reset();
        System.out.println("Счётчик сброшен.");
    }
}