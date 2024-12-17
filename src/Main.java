import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int earnings = 0;    // доходы
        int spendings = 0;   // расходы

        Scanner scanner = new Scanner(System.in);//Цикл будет работать, пока пользователь не введет `end`
        while (true) {
            // Выводим меню пользователя
            System.out.println("Выберите операцию и введите её номер:");
            System.out.println("1. Добавить новый доход");
            System.out.println("2. Добавить новый расход");
            System.out.println("3. Выбрать систему налогообложения");
            System.out.println("Выберите действие или введите end для завершения программы: ");

            String input = scanner.nextLine();
            int operation = Integer.parseInt(input);

            // Если end завершаем программу
            if ("end".equals(input)) {
                break;
            }

            //Логика меню
            switch (operation) {
                case 1:
                    System.out.println("Введите сумму дохода: ");
                    String moneyStr = scanner.nextLine();
                    int money = Integer.parseInt(moneyStr);
                    earnings += money;
                    break;
                case 2:
                    System.out.println("Введите сумму расходов: ");
                    String spendingsStr = scanner.nextLine();
                    int spend = Integer.parseInt(spendingsStr);
                    spendings += spend;
                    break;
                case 3:
                    selectedSystem(earnings, spendings);
                    break;
                default:
                    System.out.println("Некорректный ввод. Пожалуйста выберите правильный вариант");
            }
        }
        System.out.println("Программа завершена!");
    }

    //Метод выбора системы налогообложения
    private static void selectedSystem(int earnings, int spendings) {
        int taxSystem1 = calculateSystem1(earnings); // УСН доходы (6%)
        int taxSystem2 = calculateSystem2(earnings, spendings); // УСН доходы минус расходы (15%)

        System.out.println("Налог по системе УСН доходы: " + taxSystem1);
        System.out.println("Налог по системе УСН доходы минус расходы: " + taxSystem2);

        if (taxSystem1 < taxSystem2) {
            System.out.println("Наиболее выгодная система налогообложения: УСН доходы");
            System.out.println("Экономия составит: " + (taxSystem2 - taxSystem1));
        } else if (taxSystem1 > taxSystem2) {
            System.out.println("Наиболее выгодная система налогообложения: УСН доходы минус расходы");
            System.out.println("Экономия составит: " + (taxSystem1 - taxSystem2));
        } else {
            System.out.println("Можете выбрать любую систему налогообложения. Налоги равны.");
        }
    }

    // Метод для расчета налога по системе УСН доходы - налог 6% от доходов
    private static int calculateSystem1(int earnings) {
        return (int) (earnings * 0.06); //Налог 6%
    }

    // Метод для расчета налога по системе УСН доходы минус расходы - налог 15% от разницы доходов и расходов
    private static int calculateSystem2(int earnings, int spendings) {
        int deferent = earnings - spendings;
        //Налог не может быть отрицательным
        if (deferent < 0) {
            return 0;
        }
        return (int) (deferent * 0.15); //Налог 15%
    }
}