package PaymentGatewaySystem.models;

import java.util.Map;

public class Order extends BaseModel{

    private CustomerSession customerSession;
    private Map<MenuItem, Integer> orderedItems;

    public CustomerSession getCustomerSession() {
        return customerSession;
    }

    public void setCustomerSession(CustomerSession customerSession) {
        this.customerSession = customerSession;
    }

    public Map<MenuItem, Integer> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Map<MenuItem, Integer> orderedItems) {
        this.orderedItems = orderedItems;
    }
}
//we need to generate the bill and for that we need to know about the order.

//the order class gives us the detail of the customer session, we will generate the bill, only and only
//when the customer session will end. Once the customer session returns ended, we will be able to make
//hold of all the menu items that have been ordered and in what quantity. To do so, we have created
//a map. Map holds for every MenuItem, quantity. It is the MenuItem itseld which describes what is the
//price of the menuItem.