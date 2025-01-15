import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
interface Ibank {
    void put(double summa);
    void get(double summa);
}

public class Client implements Ibank {
    private String name; // имя клиента
    private double summa_bank; // сумма вклада

    // конструктор
    public Client(String name, double summa_bank) {
        this.name = name;
        this.summa_bank = summa_bank;
    }

    // реализация методов интерфейса Ibank
    // Положить деньги на счет
    public void put(double summa) {
        summa_bank += summa;
    }

    // Снять деньги со счета
    public void get(double summa) {
        if (summa <= summa_bank)
            summa_bank -= summa;
    }
} // класс Client


public class Client {
    interface Iaccount {
        // Положить деньги на счет
        void put(double summa);

        // Снять деньги со счета
        void get(double summa);

        // Начислить проценты
        void percent();
    }

    interface Ibonus {
        // начислить бонус
        double bonus();
    }
    static class Bank_Client implements Iaccount, Ibonus {
        String name; // имя клиента
        int passport; // номер паспорта
        double summa_bank; // сумма вклада
        LocalDate date; // дата открытия счета

        public double getSumma_bank() { // свойство
            return summa_bank;
        }

        // конструктор
        public Bank_Client(String name,
                           int passport, double summa_bank,
                           int year, int month, int day) {
            this.name = name;
            this.passport = passport;
            this.summa_bank = summa_bank;
            date = LocalDate.of(year, month, day);
        }

        // вывод информации о клиенте
        public void Person_Display() {
            System.out.println(" " + name + " " + passport +
                    " " + summa_bank + " " + date);
        }

        // реализация методов интерфейса Iaccount
        // Положить деньги на счет
        public void put(double summa) {
            summa_bank += summa;
        }

        // Снять деньги со счета
        public void get(double summa) {
            if (summa <= summa_bank)
                summa_bank -= summa;
        }

        // Начислить проценты в размере 10% годовых.
        // Проценты начисляются один раз, если вклад
        // пролежал год
        public void percent() {
            LocalDate today = LocalDate.now();
            if (ChronoUnit.DAYS.between(date, today) == 365)
                summa_bank *= 1.1;
        }

        // реализация метода интерфейса Ibonus
        // Начислить бонус в размере 0,5% в последний
        // день  года, если вклад более 1000000 рублей
        // пролежал более 6 месяцев
        public double bonus() {
            double add_bonus = 0.0;
            LocalDate today = LocalDate.now();
            LocalDate endOfYear = today.withMonth(12).withDayOfMonth(31);
            if (today.equals(endOfYear)) {
                long summa_days = ChronoUnit.DAYS.between(date, endOfYear);
                if (summa_bank > 1000000 &&
                        summa_days > 180)
                    add_bonus = summa_bank * 0.005;
                System.out.println(" Бонус начислен" + add_bonus);
            }
            return (add_bonus);
        }
    } // класс Bank_Client

    static class Shop_Client implements Ibonus {
        double summa_shop; // сумма покупок
        double summa_buy; // сумма текущей покупки
        public void New_Buy() // оплата покупки
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите сумму покупки");
            summa_buy = scanner.nextDouble();
            summa_shop += summa_buy;
        }
    }

    public static void main(String[] args) {
        Bank_Client client = new Bank_Client("Ivanov", 1234567, 2000000, 2015, 1, 9);
        client.put(50000);
        client.get(20000);
        client.Person_Display();
        client.percent();
        // в конце года к вкладу добавляется бонус
        client.put(client.bonus());
        System.out.println (" сумма вклада: " + client.getSumma_bank());
        //===================================
        Shop_Client client1 = new Shop_Client();
        client1.New_Buy(); // первая покупка
        client1.setSumma_buy(client1.getSumma_buy() - client1.bonus());
        System.out.println
                ("к оплате: " + client1.getSumma_buy());
        client1.New_Buy(); // вторая покупка
        client1.setSumma_buy(client1.getSumma_buy() - client1.bonus());
        System.out.println ("к оплате: " + client1.getSumma_buy());
    }
}
