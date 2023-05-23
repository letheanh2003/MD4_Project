package ra.model.entity;

import java.util.Date;

public class Order {
    private  int orderId;
    private  double total;
    private Date createDate;
    private  String phone;
    private String address;
    private int status;

    public Order() {
    }

    public Order(int orderId, double total, Date createDate, String phone, String address, int status) {
        this.orderId = orderId;
        this.total = total;
        this.createDate = createDate;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }

    public Order(int orderId,String phone, String address, double total) {
        this.orderId = orderId;
        this.phone = phone;
        this.address = address;
        this.total = total;
    }



    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
