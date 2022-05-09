package models;

public class Proceeds {
    private int quantity_of_sold_books;
    private double total_price;

    public Proceeds(int quantity_of_sold_books, double total_price) {
        this.quantity_of_sold_books = quantity_of_sold_books;
        this.total_price = total_price;
    }

    public int getQuantity_of_sold_books() {
        return quantity_of_sold_books;
    }

    public void setQuantity_of_sold_books(int quantity_of_sold_books) {
        this.quantity_of_sold_books = quantity_of_sold_books;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
    @Override
    public String toString(){
        return " total " + quantity_of_sold_books +
                " books for " + total_price + " price";
    }
}
