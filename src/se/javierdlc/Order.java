package se.javierdlc;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public int orderID;
    public int customerID;
    private List<OrderLine> orderlines;
    public String customerName;

    public Order(String customerName, int customerID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.orderlines = new ArrayList<>();
    }

    public void addOrderLine(OrderLine orderLine) {
        this.orderlines.add(orderLine);
    }

    public List<OrderLine> getAllOrderLines() {
        return orderlines;
    }

    @Override
    public String toString() {
        String print = customerName + "\n";
        for (OrderLine orderLine : orderlines) {
            print += "\t" + orderLine.toString() + "\n";
        }
        return print;
    }

    public Money getOrderTotal() {
        Money total = Money.of(CurrencyUnit.EUR, 0);
        for (OrderLine orderLine : orderlines) {
            total = total.plus(orderLine.getOrderLineTotalValue());
        }
        return total;
    }
}
