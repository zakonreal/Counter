import java.io.*;
import java.util.Scanner;

public class Main {

    private static final String COUNTER_FILE_NAME = "counter.ser";
    private static Scanner scanner = new Scanner(System.in);

    private Counter loadCounterFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COUNTER_FILE_NAME))) {
            Counter counter = (Counter) ois.readObject();
            System.out.printf("Счётчик загружен, значение '%d'%n", counter.getValue());
            return counter;
        } catch (FileNotFoundException e) {
            System.err.println("Файл со значением счётчика не найден. Создаётся новый счётчик.");
            return new Counter(); // Если файл отсутствует, создаём новый объект счётчика
        }
    }

    private void saveCounterToFile(Counter counter) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COUNTER_FILE_NAME))) {
            oos.writeObject(counter);
            System.out.println("Состояние счётчика успешно сохранено.");
        }
    }

    public static void main(String[] args) throws Exception {
        Main app = new Main();

        // Загружаем счётчик из файла
        Counter counter = app.loadCounterFromFile();

        while (true) {
            // Выводим текущее значение счётчика
            System.out.print("Текущее значение: ");
            System.out.println(counter.getValue());

            // Считываем команду от пользователя
            String command = scanner.nextLine().trim();

            if ("/inc".equals(command)) {
                app.processCommand(new IncrementCommand(), counter);
            } else if ("/stop".equals(command)) {
                System.out.println("Завершаю работу");
                app.saveCounterToFile(counter);
                break;
            } else if ("/reset".equals(command)) {
                app.processCommand(new ResetCommand(), counter);
            } else {
                System.err.println("Неизвестная команда!");
            }
        }

        scanner.close();
    }

    private void processCommand(CommandProcessor processor, Counter counter) {
        processor.process(counter);
    }
}