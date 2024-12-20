public class IncrementCommand implements CommandProcessor {
    @Override
    public void process(Counter counter) {
        counter.increment();
        System.out.println("Счётчик увеличен до: " + counter.getValue());
        System.out.print("Пожалуйста, введите следующую команду: ");
    }
}