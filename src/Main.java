import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProcessorText processorText = new ProcessorText();
        Scanner scanner = new Scanner(System.in);

        // Бесконечный цикл для ввода текста с консоли
        while (true) {
            System.out.println("input text to convert or exit in order complete request ");
            String input = scanner.nextLine();

            // Если введен 'exit', выходим из программы
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Program is executed");
                break;
            }

            // Обрабатываем введенный текст
            String result = processorText.textModifier(input);
            System.out.println("Результат: " + result);
        }

        scanner.close();
    }
}