package models;

public class Details {
    private Booktype type;
    private int quantity;

    public Details(Booktype type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public Booktype getType() {
        return type;
    }

    public void setType(Booktype type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
