package ru.job4j.servlet2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateUser.class)
public class UserServletTest {
    Validate validate = new ValidateStub();
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpServletResponse resp = mock(HttpServletResponse.class);

    @Before
    public void beforeTest() {
        PowerMockito.mockStatic(ValidateUser.class);
        PowerMockito.when(ValidateUser.getValidateUserObject()).thenReturn(validate);
    }

    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException {
        when(req.getParameter("name")).thenReturn("Anna");
        when(req.getParameter("login")).thenReturn("anna");
        when(req.getParameter("email")).thenReturn("anna@gmail.com");
        when(req.getParameter("password")).thenReturn("aaa");
        when(req.getParameter("role")).thenReturn("ADMIN");
        new UserCreateServlet().doPost(req, resp);
        List<User> list = validate.findAll();
        assertThat(list.get(0).getName(), is("Anna"));
    }

    @Test
    public void whenUpdateUser() throws ServletException, IOException{
        User user = new User("Anna", "anna", "anna@gmail.com", "aaa", Role.Roles.ADMIN);
        validate.add(user);
        List<User> list = validate.findAll();
        when(req.getParameter("id")).thenReturn(String.valueOf(validate.findById(list.get(0).getId()).getId()));
        when(req.getParameter("name")).thenReturn("Anna");
        when(req.getParameter("login")).thenReturn("annish");
        when(req.getParameter("email")).thenReturn("anna@gmail.com");
        when(req.getParameter("password")).thenReturn("aaa");
        when(req.getParameter("role")).thenReturn("ADMIN");
        new UserUpdateServlet().doPost(req, resp);
        List<User> list2 = validate.findAll();
        assertThat(list2.get(0).getLogin(), is("annish"));
    }

    @Test
    public void whenUserDeleted() throws ServletException, IOException {
        User user1 = new User("Anna", "anna", "anna@gmail.com", "aaa", Role.Roles.ADMIN);
        User user2 = new User("Bob", "bob", "bob@gmail.com", "bbb", Role.Roles.USER);
        validate.add(user1);
        validate.add(user2);
        List<User> list = validate.findAll();
        when(req.getParameter("id")).thenReturn(String.valueOf(validate.findById(list.get(0).getId()).getId()));
        new UserDeleteServlet().doGet(req, resp);
        List<User> list2 = validate.findAll();
        assertThat(list2.size(), is(1));
    }

    @Test
    public void whenGetAllUsers() throws ServletException, IOException {
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Mockito.when(req.getRequestDispatcher("WEB-INF/views/Users.jsp")).thenReturn(dispatcher);
        User user1 = new User("Anna", "anna", "anna@gmail.com", "aaa", Role.Roles.ADMIN);
        User user2 = new User("Bob", "bob", "bob@gmail.com", "bbb", Role.Roles.USER);
        validate.add(user1);
        validate.add(user2);

        new UsersServlet().doGet(req, resp);

        //List<User> list = (List<User>) req.getAttribute("users");
        //assertThat(list.size(), is(2));

    }


}