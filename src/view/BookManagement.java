package view;

import control.BookControl;
import control.Menu;

public class BookManagement {

    public static void main(String[] args) {

        Menu menu = new Menu("HKT Book Store");
        menu.addOption("Select the options below:");
        menu.addOption("1.Show the book list");
        menu.addOption("2.Add new book");
        menu.addOption("3.Update book");
        menu.addOption("4.Delete book");
        menu.addOption("5.Search book");
        menu.addOption("6.Store data to file");
        menu.addOption("7.Add Author");
        menu.addOption("8.Print Author");
        
        menu.addOption("9.Exit");
        boolean change = false;

        // Book control
        BookControl book = new BookControl();
        book.AddFromFile();
        book.AddFromFileAuthor();

        int choice;
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    book.printBookByISBN();
                    break;
                case 2:
                    book.addNewBook();
                    change = true;
                    break;
                case 3:
                    book.updateBook();
                    change = true;
                    break;
                case 4:
                    book.removeABook();
                    change = true;
                    break;
                case 5:
                   book.searchTitleAndName();
                    break;

                case 6:
                    book.savetoFile("book.dat");
                    break;

                case 7:
                    book.addAuthor();
                    book.saveToFileAuthor("author.dat");
                    change = true;
                    break;
                case 8:
                    book.printAuthor();
                    break;    
                case 9:
                    if (change) {
                        book.savetoFile("BOOK.dat");
                        book.saveToFileAuthor("author.dat");
                    }
                    System.out.println("Thank you. See you again!!!");
                    break;
            }
        } while (choice != 9);
    }
}
