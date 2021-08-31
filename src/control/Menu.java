package control;

import validation.CheckValidation;

import java.util.ArrayList;

public class Menu {
    private String title;
    private ArrayList<String> option = new ArrayList<>();

    public Menu(String title) {
        this.title = title;
    }

    public void addOption(String newOption){
        option.add(newOption);
    }

    public void showMenu(){
        if(option.isEmpty()) {
            System.out.println("There is no option in the menu");
            return;
        }
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Welcome to " + title );

        for (String s : option)
            System.out.println(s);
    }

    public int getChoice(){
        int maxOption = option.size() - 1;
        String inputMsg = "Chooce from 1 to " + maxOption ;
        String error = "Try again. You have to choose opntion from 1 to " + maxOption;
        return CheckValidation.getIntInAnInterval(inputMsg, error, 1, maxOption);

    }
}
