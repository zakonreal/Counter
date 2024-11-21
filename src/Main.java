import java.io.*;
import java.util.Scanner;

public class Main {

    private static final String COUNTER_FILE_NAME = "counter.ser";
    private static Scanner scanner = new Scanner(System.in);

    private Counter loadCounterFromFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COUNTER_FILE_NAME))) {
            Counter counter = (Counter) ois.readObject();
            System.out.printf("Счётчик загружен, текущее значение '%d'%n", counter.getValue());
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

    private static void printMenu() {
        System.out.println("Добро пожаловать в приложение - Счётчик!");
        System.out.println("Команды приложения:");
        System.out.println("/inc - увеличить значение счётчика на 1");
        System.out.println("/stop - завершить работу");
        System.out.println("/reset - сбросить значение счётчика\n");
    }

    public static void main(String[] args) throws Exception {
        printMenu();

        Main app = new Main();

        // Загружаем счётчик из файла
        Counter counter = app.loadCounterFromFile();

        System.out.print("Пожалуйста, введите команду: ");

        while (true) {
            // Считываем команду от пользователя
            String command = scanner.nextLine().trim();

            if ("/inc".equals(command)) {
                app.processCommand(new IncrementCommand(), counter);
            } else if ("/stop".equals(command)) {
                System.out.printf("Завершаю работу, текущее значение '%d'%n", counter.getValue());
                app.saveCounterToFile(counter);
                break;
            } else if ("/reset".equals(command)) {
                app.processCommand(new ResetCommand(), counter);
            } else {
                System.err.println("Неизвестная команда!");
                System.err.print("Пожалуйста, введите корректную команду: ");
            }
        }

        scanner.close();
    }

    private void processCommand(CommandProcessor processor, Counter counter) {
        processor.process(counter);
    }
}