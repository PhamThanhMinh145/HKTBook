package model;

public class Book implements  Comparable<Book>{
    private String ISBN;
    private String title;
    private double price;
    private String authorID;
  

    public Book(String ISBN, String title, double price, String authorID) {
        this.ISBN = ISBN;
        this.title = title;
        this.price = price;
        this.authorID = authorID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    @Override
    public int compareTo(Book o) {
        return this.ISBN.compareToIgnoreCase(o.ISBN);
    }

    public void showBook(){
        String msg;
        msg = String.format("|%-6s|%-20s|%-10.2fVND|%-9s|"
                ,ISBN,title,price,authorID);
        System.out.printf(msg);

    }



}
