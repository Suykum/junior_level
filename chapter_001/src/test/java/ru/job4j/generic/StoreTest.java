package ru.job4j.generic;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StoreTest {
    UserStore userStore = new UserStore(5);
    RoleStore roleStore = new RoleStore(5);
    @Test
    public void whenUserAdded() {
        userStore.add(new User("111", "Nick"));
        userStore.add(new User("222", "Anna"));
        String result = userStore.findById("111").getName();
        assertThat(result, is("Nick"));
    }

    @Test
    public void whenUserReplaced() {
        userStore.add(new User("111", "Nick"));
        userStore.add(new User("222", "Anna"));
        userStore.replace("222", new User("222", "Janna"));
        String result = userStore.findById("222").getName();
        assertThat(result, is("Janna"));
    }

    @Test
    public void whenDeleteFromRoleStore() {
        roleStore.add(new Role("111", "Manager"));
        roleStore.add(new Role("222", "Engineer"));
        boolean result = roleStore.delete("111");
        assertThat(result, is(true));
    }

    @Test
    public void whenFindingByIdOfRole() {
        roleStore.add(new Role("111", "Manager"));
        roleStore.add(new Role("222", "Engineer"));
        String result = roleStore.findById("222").getRoleName();
        assertThat(result, is("Engineer"));
    }


}
