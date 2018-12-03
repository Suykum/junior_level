package ru.job4j.tracker;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {

    private Input input;
    private ITracker tracker;
    private ArrayList<UserAction> actions = new ArrayList<>();
    public MenuTracker(Input input, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    public void fillActions(StartUI ui) {
        this.actions.add(new AddItem(0, "Add new Item"));
        this.actions.add(new ShowItems(1, "Show all items"));
        this.actions.add(new EditItem(2, "Edit item"));
        this.actions.add(new DeleteItem(3, "Delete item"));
        this.actions.add(new FindItemById(4, "Find item by Id"));
        this.actions.add(new FindItemsByName(5, "Find items by name"));
        this.actions.add(new ExitProgram(ui, 6, "Exit Program"));
    }
    public ArrayList<Integer> menuRangesL() {
        ArrayList<Integer> menuList = new ArrayList<>();
        for (UserAction u : actions) {
            menuList.add(u.key());
        }
        return menuList;
    }
    /**
     * Метод для получения массива меню.
     *
     * @return длину массива
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }
    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
   /* public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }*/
   public void show(Consumer<List<UserAction>> consumer) {
       consumer.accept(this.actions);
   }
    static class AddItem extends BaseAction {
        public AddItem(int key, String name) {
           super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("------------ Adding new Item --------------");
            String name = input.ask("Enter a name of Item : ");
            String desc = input.ask("Enter description of Item: ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ New Item with Id : " + item.getId() + "-----------");

        }
    }
    static class ShowItems extends BaseAction {
        public ShowItems(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, ITracker tracker) {
            ArrayList<Item> allItems = tracker.getAll();
            if (allItems.size() != 0) {
                System.out.println("------------ All Items --------------");
                for (Item i : allItems) {
                    System.out.println(i.toString());
                }
            } else {
                System.out.println("No any Item");
            }
        }
    }
    static class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("------------ Edit Item--------------");
            String id = input.ask("Enter ID of the item: ");
            Item resultOfSearch = tracker.findById(id);
            if (resultOfSearch != null) {
                String name = input.ask("Enter the name of the item :");
                String desc = input.ask("Enter the description of the item :");
                Item item = new Item(name, desc);
                tracker.replace(id, item);
                System.out.println("---------------" + id + " id item is edited--------------");
            } else {
                System.out.println("Item not found");
            }
        }
    }
    static class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("------------ Deleting item --------------");
            String id = input.ask("Enter ID of item: ");
            if (tracker.delete(id)) {
                System.out.println("----------------" + id + " id item is deleted --------------");
            } else {
                System.out.println("Item not found");
            }
        }
    }
    static class FindItemById extends BaseAction {
        public FindItemById(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("------------ Searching by ID --------------");
            String id = input.ask("Enter ID of the item: ");
            Item resultOfSearch = tracker.findById(id);
            if (resultOfSearch != null) {
                System.out.println("------------ Result of searching: --------------");
                System.out.println(resultOfSearch.toString());
            } else {
                System.out.println("Item not found");
            }
        }
    }
    static class FindItemsByName extends BaseAction {
        public FindItemsByName(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("------------ Searching by name --------------");
            String key = input.ask("Enter the name of item: ");
            ArrayList<Item> resultOfSearch = tracker.findByName(key);
            if (resultOfSearch.size() != 0) {
                System.out.println("------------ Result of searching: --------------");
                for (Item i : resultOfSearch) {
                    System.out.println(i.toString());
                }
            } else {
                System.out.println("Item not found");
            }
        }
    }
    static class ExitProgram extends BaseAction {
        private final StartUI input;
        public ExitProgram(StartUI input, int key, String name) {
            super(key, name);
            this.input = input;
        }
        @Override
        public void execute(Input input, ITracker tracker) {
            this.input.stop();
        }
    }


}
