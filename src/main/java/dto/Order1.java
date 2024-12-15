package dto;

import java.util.List;

public class Order1 {

    private boolean success;
    private List<Order1> orders;
    private int total;
    private int totalToday;

    public boolean isSuccess() {
        return success;
    }

    public List<Order1> getOrders() {
        return orders;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalToday() {
        return totalToday;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "success=" + success +
                ", orders=" + orders +
                ", total=" + total +
                ", totalToday=" + totalToday +
                '}';
    }
}
