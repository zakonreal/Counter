public class StopCommand implements CommandProcessor {
    @Override
    public void process(Counter counter) {
        System.out.println("Текущий счётчик: " + counter.getValue());
        System.exit(0); // Завершаем выполнение программы
    }
}
