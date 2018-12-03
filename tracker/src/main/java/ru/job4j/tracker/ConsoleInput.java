package ru.job4j.tracker;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInput implements Input {
    Scanner scanner = new Scanner(System.in);
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
    public int ask(String question, ArrayList<Integer> range) throws MenuOutException {
        int key = Integer.valueOf(this.ask(question));
        boolean exit = false;
        for (int value : range) {
            if (key == value) {
                exit = true;
                break;
            }
        }
        if (!exit) {
            throw new MenuOutException("Out of menu range");
        }
        return key;
    }

}
