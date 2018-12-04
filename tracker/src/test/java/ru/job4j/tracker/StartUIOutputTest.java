package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.trackersql.TrackerSQL;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUIOutputTest {
   /* // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    TrackerSQL tracker = new TrackerSQL();
    Item[] items = {(new Item("test1", "test2")),
                    (new Item("test2", "desc2")),
                    (new Item("test3", "desc3")),
                    (new Item("test3", "desc4"))};
    String ls = System.lineSeparator();

    public String getMenu() {
        StringBuilder sb = new StringBuilder();
        //sb.append("Menu.").append(ls);
        sb.append("0 : Add new Item").append(ls);
        sb.append("1 : Show all items").append(ls);
        sb.append("2 : Edit item").append(ls);
        sb.append("3 : Delete item").append(ls);
        sb.append("4 : Find item by Id").append(ls);
        sb.append("5 : Find items by name").append(ls);
        sb.append("6 : Exit Program");
        return sb.toString();
    }
    StringBuilder sb = new StringBuilder(getMenu());
    @Before
    public void addItems() {
        System.out.println("Before method.");
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        tracker.add(items[3]);
        System.setOut(new PrintStream(this.out));
    }
    @After
    public void afterTest() {
        System.setOut(this.stdout);
        System.out.println("After method.");
    }
    @Test
    public void whenAddingNewItem() throws MenuOutException {
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        sb.append(ls).append("------------ Adding new Item --------------").append(ls);
        sb.append("------------ New Item with Id : " + (tracker.findByName("test name")).get(0).getId() + "-----------").append(ls);
        sb.append(getMenu()).append(ls);
        assertThat(new String(this.out.toByteArray()), is(sb.toString()));
    }
    @Test
    public void whenGettingAllItems() throws MenuOutException {
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        sb.append(ls).append("------------ All Items --------------").append(ls);
        for (Item i : items) {
            sb.append(i).append(ls);
        }
        sb.append(getMenu()).append(ls);
        assertThat(new String(this.out.toByteArray()), is(sb.toString()));
    }
    @Test
    public void whenDeleteItem() throws MenuOutException {
        Input input = new StubInput(new String[]{"3", "789", "6"});
        new StartUI(input, tracker).init();
        sb.append(ls).append("------------ Deleting item --------------").append(ls);
        sb.append("Item not found").append(ls);
        sb.append(getMenu()).append(ls);
        assertThat(new String(this.out.toByteArray()), is(sb.toString()));
    }

    @Test
    public void whenEditing() throws MenuOutException {
        Input input = new StubInput(new String[]{"2", items[0].getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        sb.append(ls).append("------------ Edit Item--------------").append(ls);
        sb.append("---------------" + items[0].getId() + " id item is edited--------------").append(ls);
        sb.append(getMenu()).append(ls);
        assertThat(new String(this.out.toByteArray()), is(sb.toString()));
    }
    @Test
    public void whenSearchingById() throws MenuOutException {
        Input input = new StubInput(new String[]{"4", items[1].getId(), "6"});
        new StartUI(input, tracker).init();
        sb.append(ls).append("------------ Searching by ID --------------").append(ls);
        sb.append("------------ Result of searching: --------------").append(ls);
        sb.append(items[1].toString()).append(ls);
        sb.append(getMenu()).append(ls);
        assertThat(new String(this.out.toByteArray()), is(sb.toString()));
    }

    @Test
    public void whenSearchingByName() throws MenuOutException {
        Input input = new StubInput(new String[]{"5", items[2].getName(), "6"});
        new StartUI(input, tracker).init();
        sb.append(ls).append("------------ Searching by name --------------").append(ls);
        sb.append("------------ Result of searching: --------------").append(ls);
        sb.append(items[2].toString()).append(ls);
        sb.append(items[3].toString()).append(ls);
        sb.append(getMenu()).append(ls);
        assertThat(new String(this.out.toByteArray()), is(sb.toString()));
    }*/
}

