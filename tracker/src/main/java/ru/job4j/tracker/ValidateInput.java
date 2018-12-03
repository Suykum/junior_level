package ru.job4j.tracker;

import java.util.ArrayList;

public class ValidateInput implements Input {
    private final Input input;
    public ValidateInput(final Input input) {
        this.input = input;
    }
    public String ask(String question) {
        return this.input.ask(question);
    }
    public int ask(String question, ArrayList<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select number from a menu");
            } catch (NumberFormatException nfe) {
            System.out.println("Please enter valid data again");
            }
        } while (invalid);
            return value;

    }

}
