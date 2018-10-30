package ru.job4j.controltask;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreTest {
    List<Store.User> previous = new ArrayList<>();
    List<Store.User> current = new ArrayList<>();
    @Before
    public void beforeTest() {

        previous.add(new Store.User(1, "Tom"));
        previous.add(new Store.User(2, "Bim"));
        previous.add(new Store.User(3, "Anna"));
        previous.add(new Store.User(4, "Kate"));

        current.add(new Store.User(1, "Tim"));
        current.add(new Store.User(3, "Ann"));
        current.add(new Store.User(5, "Can"));
        current.add(new Store.User(6, "Billi"));
        current.add(new Store.User(7, "Simay"));
    }

    @Test
    public void whenThreeUsersAdd() {
        Store store = new Store();
        Info info = store.diff(previous, current);
        assertThat(info.getAddCount(), is(3));
    }

    @Test
    public void whenTwoUsersDeleted() {
        Store store = new Store();
        Info info = store.diff(previous, current);
        assertThat(info.getDeleteCount(), is(2));
    }

    @Test
    public void whenTwoUsersEdited() {
        Store store = new Store();
        Info info = store.diff(previous, current);
        assertThat(info.getEditCount(), is(2));
    }
}