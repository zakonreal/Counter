public class IncrementCommand implements CommandProcessor {
    @Override
    public void process(Counter counter) {
        counter.increment();
        System.out.println("Счётчик увеличен до: " + counter.getValue());
    }
}