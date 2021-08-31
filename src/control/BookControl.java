/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import model.Author;
import model.Book;
import validation.CheckValidation;

/**
 *
 * @author DELL
 */
public class BookControl {

    private ArrayList<Author> authorList = new ArrayList<>();
    private ArrayList<Book> bookList = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void AddFromFile() {
        try {
            // File f = new File(fname);

            FileReader fr = new FileReader("BOOK.dat");  // read()
            BufferedReader bf = new BufferedReader(fr);  // readline()
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ";");
                String ISBN = stk.nextToken().toUpperCase();
                String title = stk.nextToken().toUpperCase();
                double price = Double.parseDouble(stk.nextToken());
                String authorID = stk.nextToken().toUpperCase();

                Book book = new Book(ISBN, title, price, authorID);
                bookList.add(book);
            }

            bf.close();

            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddFromFileAuthor() {

        try {
            //    File f = new File(fname);

            FileReader fr = new FileReader("AUTHOR.dat");  // read()
            BufferedReader bf = new BufferedReader(fr);  // readline()
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ";");
                String authorID = stk.nextToken().trim().toUpperCase();
                String name = stk.nextToken().trim().toUpperCase();

                Author author = new Author(authorID, name);
                authorList.add(author);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            //System.out.println("file lỗi");
            e.printStackTrace();
        }
    }

    public int checkExistOfAuthor(String idAuth) {
        int pos;
        if (bookList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < authorList.size(); i++) {
            if (authorList.get(i).getAuthorID().equalsIgnoreCase(idAuth)) {
                return i;
            }
        }
        return -1;

    }

    public Author searchBookByAuthor(String authorID) {
        if (authorList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < authorList.size(); i++) {
            if (authorList.get(i).getAuthorID().equalsIgnoreCase(authorID)) {
                return authorList.get(i);
            }
        }
        return null;
    }

    public void printAuthor() {
        if (authorList.isEmpty()) {
            System.out.println("The book list is empty. Nothing to print");
            return;
        }
        System.out.println("----------List of detailed information Author ----------");
        for (int i = 0; i < authorList.size(); i++) {
            authorList.get(i).showAuthor();
        }

    }

    public int checkDuplicationBookByID(String bookID) {
        int pos;
        if (bookList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getISBN().equalsIgnoreCase(bookID)) {
                return i;
            }
        }
        return -1;

    }

    public void addNewBook() {

        String ISBN, title, authorID, s;
        double price;
        int pos;
        do {
            do {
                ISBN = CheckValidation.getString("Input ISBN(Bxxxx): ", "ISBN is required.The format of id is BXXXXX (X stands for a digit)", "^[B|b]\\d{4}$");
                pos = checkDuplicationBookByID(ISBN);
                if (pos >= 0) {
                    System.out.println("The ISBN already exists. Please enter new ISBN");
                }
            } while (pos != -1);
            title = CheckValidation.getStringNoFormat("Input title: ", "Title is required");
            price = CheckValidation.getDoubleInAnInterval("Input price: ", "Price is required. ",
                    0, 500000);

            printAuthor();
            do {
                authorID = CheckValidation.getString("Input authorID: ", "AuthorID is required",
                        "^[A-Z]{2}\\d{2}$");
                pos = checkExistOfAuthor(authorID);
                if (pos == -1) {
                    System.out.println("Author ID must be included in list author");
                }
            } while (pos == -1);
            bookList.add(new Book(ISBN, title, price, authorID));
            System.out.println("\n------------ Add Successfully----------");
            s = CheckValidation.getString("Do you wanna continue adding Book (Y/N)",
                    "Input is required", "[Y-y|N-n]{1}");

        } while (s.equalsIgnoreCase("Y"));
    }

    public void addAuthor() {
        String name, authorID, s;

        int pos;
        do {
            do {
                authorID = CheckValidation.getString("Input ID(TGxx): ", "ID is required.The format of id is TGxx (x stands for a digit)", "^[TG|tg]{2}\\d{2}$");
                pos = checkExistOfAuthor(authorID);
                if (pos >= 0) {
                    System.out.println("The ID already exists. Please enter new ID");
                }
            } while (pos != -1);
            name = CheckValidation.getStringNoFormat("Input name: ", "Name is required");

            authorList.add(new Author(authorID, name));
            System.out.println("\n------------ Add Successfully----------");
            s = CheckValidation.getString("Do you wanna continue adding Author (Y/N)",
                    "Input is required", "[Y-y|N-n]{1}");

        } while (s.equalsIgnoreCase("Y"));

    }

    public void printBookByISBN() {
        if (bookList.isEmpty()) {
            System.out.println("The book list is empty. Nothing to print");
            return;
        }
        Collections.sort(bookList);
        System.out.println("----------*******----------");
        System.out.println("----------List of detailed information of the phones----------");
        String msg = String.format("|%-6s|%-20s|%-13s|%-9s|%-15s|",
                "ISBN", "Title", "Price", "AuthorID", "Name");
        System.out.println(msg);
        for (int i = 0; i < bookList.size(); i++) {
            bookList.get(i).showBook();
            for (int j = 0; j < authorList.size(); j++) {
                if (bookList.get(i).getAuthorID().contains(authorList.get(j).getAuthorID())) {
                    System.out.printf("%-15s|", authorList.get(j).getName());
                    System.out.println();
                }
            }
        }

    }

    public Book searchBookByISBN(String book) {
        if (bookList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getISBN().equalsIgnoreCase(book)) {
                return bookList.get(i);
            }
        }
        return null;
    }

    public void updateBook() {
        String ISBN, title, authID, s;
        double price = 0;
        Book b;
        boolean check;
        int pos;
        do {
            System.out.println("--------- UPDATE BOOK---------");

            ISBN = CheckValidation.getStringNoFormat("Input ISBN to update", "ISBN is required!");
            b = searchBookByISBN(ISBN);
            if (b == null) {
                System.out.println("Not found ISBN to update");
            } else {
                System.out.println("Input the new title: ");
                title = sc.nextLine();
                if (title.trim().isEmpty()) {
                    b.getTitle();
                } else {
                    b.setTitle(title);
                }

                // price input
                do {
                    System.out.println("Input the new price: ");
                    try {
                        String priceStr = sc.nextLine();
                        if (priceStr.trim().isEmpty()) {
                            b.getPrice();
                            break;
                        }
                        price = Double.parseDouble(priceStr);
                        check = true;

                    } catch (Exception e) {

                        System.out.println("Price must be positive interger");
                        check = false;
                    }
                    if (price < 0) {
                        System.out.println("Price must be positive interger");
                        check = false;
                    }
                    if (check) {
                        b.setPrice(price);
                    }
                } while (!check);
                ;

                // author input
                printAuthor();
                do {
                    System.out.println("Input a new authorID: ");
                    authID = sc.nextLine();

                    if (authID.trim().isEmpty()) {
                        b.getAuthorID();
                        break;
                    }
                    pos = checkExistOfAuthor(authID);
                    if (pos == -1) {
                        System.out.println("Author ID must be included in list author");
                    } else if (pos >= 0) {
                        b.setAuthorID(authID);
                        break;
                    }
                } while (pos == -1);

                System.out.println("The Book info is updated successfully!");
            }
            s = CheckValidation.getString("Do you wanna keep updating books(Y/N)",
                    "Input is required", "[Y-y|N-n]{1}");
        } while (s.equalsIgnoreCase("Y"));

    }

    public void removeABook() {
        String ISBN, s, confirm;
        Book b;
        do {
            ISBN = CheckValidation.getStringNoFormat("Input ISBN to remove", "ISBN is required");
            b = searchBookByISBN(ISBN);
            System.out.println("----------*******----------");
            if (b == null) {
                System.out.println("Couldn't find the ISBN of the book to delete");
            } else {
                System.out.println("----------Information of Book----------");
                String msg = String.format("|%-6s|%-20s|%-13s|%-9s|",
                        "ISBN", "Title", "Price", "AuthorID");
                System.out.println(msg);
                b.showBook();
                System.out.println();
                confirm = CheckValidation.getString("Do you wanna remove this book (Y/N)", "Input is required", "[Y-y|N-n]{1}");
                if (confirm.equalsIgnoreCase("Y")) {
                    bookList.remove(b);
                    System.out.println("The selected book is removed sucessfully!!!");
                }
            }
            s = CheckValidation.getString("Do you wanna continue removing (Y/N)",
                    "Input is required", "[Y-y|N-n]{1}");
        } while (s.equalsIgnoreCase("Y"));
    }

    public void removeAuthorID() {
        String auID, s, confirm;
        // Author a;
        do {
            auID = CheckValidation.getStringNoFormat("Input authorID to remove: ", "Author id is required");
            confirm = CheckValidation.getString("Do you wanna remove this author (Y/N)",
                    "Input is required", "[Y-y|N-n]{1}");
            if (confirm.equalsIgnoreCase("Y")) {
                for (Author a : authorList) {
                    if (a.getAuthorID().contains(auID)) {
                        for (Book b : bookList) {
                            if (a.getAuthorID().contains(b.getAuthorID())) {
                                System.out.println("This author has a book in the store, you cannot delete this author");
                            }
                        }
                    } else {
                        authorList.remove(a);
                        System.out.println("The selected author is removed sucessfully!!!");

                    }

                }

            }
            //          }

            s = CheckValidation.getString("Do you wanna continue removing (Y/N)",
                    "Input is required", "[Y-y|N-n]{1}");
        } while (s.equalsIgnoreCase("Y"));
    }

    public void findTheBookByTitle() {
        String title, notify;

        do {
            title = CheckValidation.getStringNoFormat("Input title to find: ", "Title is required");

            // ArrayList<Book> list = approximateSearchBookByTitle(title);
            if (bookList.isEmpty()) {
                System.out.println("Not Found");
            }
            if (bookList != null) {
                for (int i = 0; i < bookList.size(); i++) {
                    if (bookList.get(i).getTitle().contains(title)) {
                        bookList.get(i).showBook();
                        System.out.printf("\n");
                    }
                }

            }

            notify = CheckValidation.getString("Do you wanna continue searching book (Y/N)",
                    "Input is required", "[Y-y|N-n]{1}");
        } while (notify.equalsIgnoreCase("Y"));
    }

    public void findTheBookByPrice() {
        String notify;
        double price;

        do {
            price = CheckValidation.getDoubleInAnInterval("Input price: ", "Price is required. ",
                    0, 500000);

            if (bookList.isEmpty()) {
                System.out.println("Not Found");
            }
            if (bookList != null) {
                for (int i = 0; i < bookList.size(); i++) {
                    if (bookList.get(i).getPrice() >= price) {
                        bookList.get(i).showBook();
                        System.out.printf("\n");
                    }
                }

            }

            notify = CheckValidation.getString("Do you wanna continue searching book (Y/N)",
                    "Input is required", "[Y-y|N-n]{1}");
        } while (notify.equalsIgnoreCase("Y"));
    }

    public void findTheBookByAuthor() {
        String auName, notify;
        do {
            auName = CheckValidation.getStringNoFormat("Input author name to find: ", "Author name is required");
            if (authorList.isEmpty()) {
                System.out.println("Not Found");
            } else {
                for (Author author : authorList) {
                    if (author.getName().contains(auName)) {
                        for (Book book : bookList) {
                            if (author.getAuthorID().contains(book.getAuthorID())) {
                                book.showBook();
                                System.out.println();
                            }
                        }
                    }
                }
            }

            notify = CheckValidation.getString("Do you wanna continue searching book (Y/N)", "Input is required",
                    "[Y-y|N-n]{1}");
        } while (notify.equalsIgnoreCase("Y"));
    }

    public void savetoFile(String filename) {
        if (bookList.isEmpty()) {
            System.out.println("The list book is empty");
            return;
        }
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f); // write()
            PrintWriter pw = new PrintWriter(fw); // println()
            for (Book b : bookList) {
                pw.println(b.getISBN() + ";" + b.getTitle() + ";" + b.getPrice() + ";" + b.getAuthorID());
            }
            pw.close();
            fw.close();
            System.out.println("Save to file successfull");

        } catch (Exception e) {
            System.out.println("file loi");
        }
    }

    public void saveToFileAuthor(String filename) {
        if (authorList.isEmpty()) {
            System.out.println("The list author is empty");
            return;
        }
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f); // write()
            PrintWriter pw = new PrintWriter(fw); // println()
            for (Author a : authorList) {
                pw.println(a.getAuthorID() + ";" + a.getName());
            }
            pw.close();
            fw.close();
            System.out.println("Save to file successfull");

        } catch (Exception e) {
            System.out.println("file loi");
        }
    }

    public void searchTitleAndName() {
        Menu menu = new Menu(" Find books by title or author's name");
        menu.addOption("Select the options below:");
        menu.addOption("1.Search book by title");
        menu.addOption("2.Search book by Author");
        menu.addOption("3.Search book by price ");
        menu.addOption("4.Exit");
        int choice;
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    findTheBookByTitle();
                    break;
                case 2:
                    findTheBookByAuthor();
                    break;
                case 3:
                    findTheBookByPrice();
                    break;
                case 4:
                    System.out.println("Thank you!!!!");
                    break;
            }

        } while (choice != 4);

    }
}
