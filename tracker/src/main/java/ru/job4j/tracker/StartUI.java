package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final ITracker tracker;
    private boolean working = true;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() throws MenuOutException {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        ArrayList<Integer> range = new ArrayList<>();
        menu.fillActions(this);
        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range.add(i);
        }
        do {
            //menu.show();
            menu.show((a) -> {
                for (UserAction action : a) {
                    System.out.println(action.info());
                }
            });
            menu.select(input.ask("Select", menu.menuRangesL()));
        } while (this.working);
    }
    public void stop() {
        this.working = false;
    }

        /**
         * Запускт программы.
         * @param args
         */
    public static void main(String[] args) throws MenuOutException {

        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();

    }
}

