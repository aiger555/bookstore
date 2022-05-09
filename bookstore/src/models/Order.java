package models;

public class Order {
    private long id;
    private long employee_id;
    private long customer_id;
    private long[] books;


    public Order(long id, long employee_id, long customer_id, long[] books) {
        this.id = id;
        this.employee_id = employee_id;
        this.customer_id = customer_id;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSeller_id() {
        return employee_id;
    }

    public void setSeller_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long[] getBooks() {
        return books;
    }

    public void setBooks(long[] books) {
        this.books = books;
    }
}
