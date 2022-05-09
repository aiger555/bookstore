import models.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;


public class Main {
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Seller> sellers = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Order> orders = new ArrayList<>();

    public static void main(String[] args){
        Data();
        //
        String booksinfo = String.format("Total number of sold books %d which price is %f", getQuantityofSoldBooks(), getAllPriceOfSoldBooks());
        System.out.println(booksinfo);
        //
        for (Seller seller : sellers) {
            System.out.println(seller.getName() + " sold " + getProceedsofSeller(seller.getId()));
        }
        //
        ArrayList<Details> soldBooksQuantity = getQuantityofSoldBooksByType();
        HashMap<Booktype, Double> soldBooksPrices = getPriceOfSoldBooksByType();

        String soldBookStr = "By type: %s sold %d books with cost %f";

        for (Details bookAdditional : soldBooksQuantity) {
            double price = soldBooksPrices.get(bookAdditional.getType());
            System.out.println(String.format(
                                            soldBookStr,
                                            bookAdditional.getType().name(), bookAdditional.getQuantity(), price
                                            ));
                                            }
        //
        int age = 30;
        String analyzeTypeStr ="Customers under %d choose type %s";
        System.out.println(String.format(analyzeTypeStr, 30, getMostPopularTypeLessThanAge(30)));

        // задача №4
        String analyzeTypeStr2 ="Customers over %d choose type %s";
        System.out.println(String.format(analyzeTypeStr2, 30, getMostPopularTypeBiggerThanAge(30)));

    }

    public static void Data(){
        sellers.add(new Seller(1, "Begayim Beka", 21));
        sellers.add(new Seller(2, "Dinka Dinara", 19));
        sellers.add(new Seller(3, "Aika Aika", 19));

        customers.add(new Customer(1, "Sydykov Andrei", 31));
        customers.add(new Customer(2, "Romanov Sasha", 40));
        customers.add(new Customer(3, "Kutmanov Eldar", 18));

        books.add(new Book(3, "Dead soul", "Dostoevsky Fedor", 1300, Booktype.Art));
        books.add(new Book(4, "Man and woman", "Fraid Zigmond", 1200, Booktype.Psychology));
        books.add(new Book(6, "C++ start", "Zinich Roman", 1100, Booktype.Programming));

        orders.add(new Order(1, 1, 1, new long[]{6, 3}));
        orders.add(new Order(2, 2, 2, new long[]{3, 4}));
        orders.add(new Order(3, 3, 3, new long[]{4, 3, 6}));
    }

    public static double getPriceOfSoldBooksInOrder(Order order) {
        double price = 0;
        for (long bookId : order.getBooks()) {
            Book book = getbookid(bookId);
            if (book != null)
                price += book.getPrice();
        }
        return price;
    }

    public static double getAllPriceOfSoldBooks() {
        double price = 0;
        for (Order order : orders) {
            price += getPriceOfSoldBooksInOrder(order);
        }
        return price;
    }

    public static Book getbookid(long id){
        Book current_b = null;
        for (Book book : books) {
            if (book.getId() == id) {
                current_b = book;
                break;
            }
        }
        return current_b;

    }

    public static int getQuantityofSoldBooks() {
        int count = 0;
        for (Order order : orders) {
            count = count + order.getBooks().length;
        }
        return count;
    }

    public static Proceeds getProceedsofSeller(long employee_id) {
        int count = 0;
        double price = 0;
        for (Order order : orders) {
            if (order.getSeller_id() == employee_id) {
                price += getPriceOfSoldBooksInOrder(order);
                count += order.getBooks().length;
            }
        }
        return new Proceeds(count, price);
    }
    public static int getQuantityOfSoldByType(Order order, Booktype type) {
        int count = 0;
        for (long bookId : order.getBooks()) {
            Book book = getbookid(bookId);
            if (book != null && book.getType() == type) {
                count++;
            }
        }
        return count;
    }

    public static double getPriceOfSoldByType(Order order, Booktype type) {
        double price = 0;
        for (long bookId : order.getBooks()) {
            Book book = getbookid(bookId);
            if (book != null && book.getType() == type)
                price += book.getPrice();
        }
        return price;
    }

    public static HashMap<Booktype, Double> getPriceOfSoldBooksByType() {
        HashMap<Booktype, Double> result = new HashMap<>();
        double priceArt = 0;
        double pricePr = 0;
        double pricePs = 0;

        for (Order order : orders) {
            priceArt += getPriceOfSoldByType(order, Booktype.Art);
            pricePr += getPriceOfSoldByType(order, Booktype.Programming);
            pricePs += getPriceOfSoldByType(order, Booktype.Psychology);
        }
        result.put(Booktype.Art, priceArt);
        result.put(Booktype.Programming, pricePr);
        result.put(Booktype.Psychology, pricePs);
        return result;
    }

    public static ArrayList<Details> getQuantityofSoldBooksByType() {
        ArrayList<Details> result = new ArrayList<>();
        int qArt = 0;
        int qProgramming = 0;
        int qPsychology = 0;

        for (Order order : orders) {
            qArt += getQuantityOfSoldByType(order, Booktype.Art);
            qProgramming += getQuantityOfSoldByType(order, Booktype.Programming);
            qPsychology += getQuantityOfSoldByType(order, Booktype.Psychology);
        }
        result.add(new Details(Booktype.Art, qArt));
        result.add(new Details(Booktype.Programming, qProgramming));
        result.add(new Details(Booktype.Psychology, qPsychology));
        return result;
    }

    private static Booktype getMostPopularBookType(ArrayList<Long> customerId) {
        int qArt = 0, qProgramming =0, qPsychology = 0;

        for (Order order:orders) {
            if (customerId.contains(order.getCustomer_id())) {
                qArt += getQuantityOfSoldByType(order, Booktype.Art);
                qProgramming += getQuantityOfSoldByType(order, Booktype.Programming);
                qPsychology += getQuantityOfSoldByType(order, Booktype.Psychology);
            }
        }
        ArrayList<Details> result = new ArrayList<>();
        result.add(new Details(Booktype.Art, qArt));
        result.add(new Details(Booktype.Programming, qProgramming));
        result.add(new Details(Booktype.Psychology, qPsychology));

        result.sort(new Comparator<Details>() {
            @Override
            public int compare(Details left, Details right) {
                return right.getQuantity() - left.getQuantity();
            }
        });
        return result.get(0).getType();
    }

    public static Booktype getMostPopularTypeLessThanAge(int age) {
        ArrayList<Long> customerId = new ArrayList<>();
        for (Customer customer: customers) {
            if (customer.getAge() < age) {
                customerId.add(customer.getId());
            }
        }
        return getMostPopularBookType(customerId);
    }


    public static Booktype getMostPopularTypeBiggerThanAge(int age) {
        ArrayList<Long> customerIds = new ArrayList<>();
        for (Customer customer: customers) {
            if (customer.getAge() > age) {
                customerIds.add(customer.getId());
            }
        }

        return getMostPopularBookType(customerIds);
    }

}
