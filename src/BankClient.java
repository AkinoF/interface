import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BankClient implements Iaccount, Ibonus {
    /**
     * имя клиента
     */
    String name;
    /**
     * номер паспорта
     */
    int passport;
    /**
     * сумма вклада
     */
    double summa_bank;
    /**
     * дата открытия счета
     */
    LocalDate date;

    public BankClient(String name, int passport, double summa_bank, int year, int month, int day) {
        this.name = name;
        this.passport = passport;
        this.summa_bank = summa_bank;
        this.date = LocalDate.of(year, month, day);
    }

    public void Person_Display() {
        System.out.println("Клиент: " + name + " Номер паспорта: " + passport +
                " Сумма вклада: " + summa_bank + " Дата открытия счёта: " + date);
    }

    public void put(double summa) {
        summa_bank += summa;
    }

    public void get(double summa) {
        if (summa <= summa_bank)
            summa_bank -= summa;
    }

    public void percent() {
        LocalDate today = LocalDate.now();
        if (ChronoUnit.DAYS.between(date, today) == 365)
            summa_bank *= 1.1;
    }

    public double bonus() {
        double add_bonus = 0.0;
        LocalDate today = LocalDate.now();
        LocalDate endOfYear = today.withMonth(12).withDayOfMonth(31);
        if (today.equals(endOfYear)) {
            long summa_days = ChronoUnit.DAYS.between(date, endOfYear);
            if (summa_bank > 1000000 && summa_days > 180)
                add_bonus = summa_bank * 0.005;
            System.out.println("Бонус начислен: " + add_bonus);
        }
        return add_bonus;
    }

    public double getSummaBank() {
        return summa_bank;
    }
}