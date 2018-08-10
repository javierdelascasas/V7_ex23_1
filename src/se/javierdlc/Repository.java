package se.javierdlc;

import java.util.List;

public interface Repository {
    Order getOrder(int orderID, int customerID);

    List<Order> getAllOrderForCustomer(int customerID);

    void deleteOrder(int orderID, int customerID);

    int createNewOrder(Order order);
}
