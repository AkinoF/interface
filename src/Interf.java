public class Interf {
    public static void main(String[] args) {
        BankClient client = new BankClient("Максим", 14355, 2000000, 2015, 1, 9);
        client.put(50000);
        client.get(20000);
        client.Person_Display();
        client.percent();
        client.put(client.bonus()); // добавление бонуса к вкладу

        System.out.println("Сумма вклада: " + client.getSummaBank());

        ShopClient client1 = new ShopClient();
        client1.New_Buy(); // первая покупка
        client1.setSumma_buy(client1.getSumma_buy() - client1.bonus());
        System.out.println("К оплате: " + client1.getSumma_buy());

        client1.New_Buy(); // вторая покупка
        client1.setSumma_buy(client1.getSumma_buy() - client1.bonus());
        System.out.println("К оплате: " + client1.getSumma_buy());
    }
}