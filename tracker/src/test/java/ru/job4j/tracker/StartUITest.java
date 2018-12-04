package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.trackersql.TrackerSQL;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
   /* @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() throws MenuOutException {
        TrackerSQL tracker = new TrackerSQL();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.getAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }
    @Test
    public void whenGettingAllItems() throws MenuOutException {
        TrackerSQL tracker = new TrackerSQL();
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("test1", "desc1"));
        items.add(new Item("test2", "desc2"));
        items.add(new Item("test3", "desc3"));
        tracker.add(items.get(0));
        tracker.add(items.get(1));
        tracker.add(items.get(2));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll(), is(items));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() throws MenuOutException {
        // создаём Tracker
        TrackerSQL tracker = new TrackerSQL();
        //Напрямую добавляем заявку
        Item item = new Item("test name", "desc");
        tracker.add(item);
        //создаём StubInput с последовательностью действий(производим замену заявки)
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteItem() throws MenuOutException {
        TrackerSQL tracker = new TrackerSQL();
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("test1", "desc1"));
        items.add(new Item("test2", "desc2"));
        tracker.add(items.get(0));
        tracker.add(items.get(1));
        Item item3 = tracker.add(new Item("test3", "desc3"));
        Input input = new StubInput(new String[]{"3", item3.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll(), is(items));
    }

    @Test
    public void whenSearchingById() throws MenuOutException {
        TrackerSQL tracker = new TrackerSQL();
        Item item1 = tracker.add(new Item("test1", "desc1"));
        Item item2 = tracker.add(new Item("test2", "desc2"));
        Input input = new StubInput(new String[]{"4", item2.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item2.getId()).getName(), is("test2"));
    }
    @Test
    public void whenSearchingByName() throws MenuOutException {
        TrackerSQL tracker = new TrackerSQL();
        *//*Item[] items = {(new Item("test2", "desc2")),
                        (new Item("test2", "desc3"))};*//*
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("test2", "desc1"));
        items.add(new Item("test2", "desc3"));
        tracker.add(items.get(0));
        tracker.add(items.get(1));
        tracker.add(new Item("test4", "desc4"));
        Input input = new StubInput(new String[]{"5", items.get(0).getName(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("test2"), is(items));
    }*/
}
