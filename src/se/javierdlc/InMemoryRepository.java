package se.javierdlc;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements Repository {
    private List<Order> orders = new ArrayList<>();
    private int orderID = 0;

    public Order getOrder(int orderID, int customerID) {
        for (Order order : orders) {
            if (order.orderID == orderID && order.customerID == customerID) {
                return order;
            }
        }
        throw new IllegalArgumentException("OrderID: " + orderID + " for CustomerID: " + customerID + " was not found.");
    }

    public List<Order> getAllOrderForCustomer(int customerID) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.customerID == customerID) {
                result.add(order);
            }
        }
        return result;
    }

    public void deleteOrder(int orderID, int customerID) {
        Order selection = getOrder(orderID, customerID);
        if (selection != null) {
            orders.remove(selection);
        } else {
            throw new IllegalArgumentException("OrderID: " + orderID + " for CustomerID: " + customerID + " was not found.");
        }
        // Kanske ska man skicka både orderID och customerID för extra säkerhet?
        // Då kanske man kan använda sig av customerID för att försäkra sig om att bara ägaren kan ta bort sin order?
    }

    public int createNewOrder(Order order) {
        if (order.customerName == null || order.customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer Name in Order is null or empty.");
        } else if (order.customerID < 1) {
            throw new IllegalArgumentException("Customer ID is zero or less than zero.");
        } else if (order.orderID != 0) {
            throw new IllegalArgumentException("Order ID is not zero.");
        } else {
            order.orderID = ++orderID;
            orders.add(order);
            return orderID;
        }
    }
}
