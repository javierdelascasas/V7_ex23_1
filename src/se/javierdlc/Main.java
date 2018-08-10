package se.javierdlc;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class Main {
    public static void main(String[] args) {
        Repository repo = new InMemoryRepository();
        int customerID = 42;
        Order order1 = new Order("Edument AB", customerID);
        order1.addOrderLine(new OrderLine("Widget A", 10, Money.of(CurrencyUnit.EUR, 3.14)));
        order1.addOrderLine(new OrderLine("Widget B", 15, Money.of(CurrencyUnit.EUR, 9.95)));
        Order order2 = new Order("Edument AB", customerID);
        order2.addOrderLine(new OrderLine("Widget C", 2, Money.of(CurrencyUnit.EUR, 5.95)));
        order2.addOrderLine(new OrderLine("Widget D", 1, Money.of(CurrencyUnit.EUR, 250)));

        int order1ID = 0;
        int order2ID = 0;

        try {
            order1ID = repo.createNewOrder(order1); //should be #1
        } catch (IllegalArgumentException e) {
            System.out.println("Could not create order1");
        }
        try {
            order2ID = repo.createNewOrder(order2); // should be #2
        } catch (IllegalArgumentException e) {
            System.out.println("Could not create order2");
        }
        System.out.println("Order1 ID=" + order1ID + ", Order2 ID=" + order2ID);
        try {
            // Get all orders, should return #2
            System.out.println("Number of items: " + repo.getAllOrderForCustomer(customerID).size());
        } catch (IllegalArgumentException e) {
            System.out.println("Could not get all orders for " + customerID);
        }
        try {
            repo.deleteOrder(order2ID, customerID);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not delete order 2");
        }
        try {
            // Get all orders, should return #1
            System.out.println("Number of items: " + repo.getAllOrderForCustomer(customerID).size());
        } catch (IllegalArgumentException e) {
            System.out.println("Could not get all new orders for " + customerID);
        }
    }
}
