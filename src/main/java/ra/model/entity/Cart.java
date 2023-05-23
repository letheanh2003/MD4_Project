package ra.model.entity;

public class Cart {
    private  int orderId;
    private  int user_id;
    private  float total;
    private String createdDate ;
    private boolean type;
    private boolean status ;
    private String phone ;
    private String address ;

    public Cart() {
    }

    public Cart(int orderId, int user_id, float total, String createdDate, boolean type, boolean status, String phone, String address) {
        this.orderId = orderId;
        this.user_id = user_id;
        this.total = total;
        this.createdDate = createdDate;
        this.type = type;
        this.status = status;
        this.phone = phone;
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
}
