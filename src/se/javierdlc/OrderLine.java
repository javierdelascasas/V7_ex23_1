package se.javierdlc;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class OrderLine {
    public String productName;
    public int quantity;
    public Money price;

    public OrderLine(String productName, int quantity, Money price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String toString() {
        return String.format("%s, %d item/s, price per item EUR %s", productName, quantity, price);
    }

    public Money getOrderLineTotalValue() {
        return price.multipliedBy(quantity);
    }
}
