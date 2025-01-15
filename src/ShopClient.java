import java.util.*;

public class ShopClient implements Ibonus {
    /**
     * сумма покупок
     */
    double summa_shop;
    /**
     * сумма текущей покупки
     */
    double summa_buy;

    public void New_Buy() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сумму покупки");
        summa_buy = scanner.nextDouble();
        summa_shop += summa_buy;
    }

    public double getSumma_buy() {
        return summa_buy;
    }

    public void setSumma_buy(double summa) {
        this.summa_buy = summa;
    }

    public double bonus() {
        // Логика начисления бонусов для покупок
        return 0;
    }
}