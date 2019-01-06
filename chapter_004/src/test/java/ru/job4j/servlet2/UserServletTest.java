package ru.job4j.servlet2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateUser.class)
public class UserServletTest {
    ValidateUser validate = new ValidateStub();
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
        //verify(validateUser.add(new User("Anna", "anna", "anna@gmail.com", "aaa",Role.Roles.valueOf("ADMIN"))));
        List<User> list = validate.findAll();
        assertThat(list.get(0).getName(), is("Anna"));

    }
}