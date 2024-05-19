package hu.nye.vpe.nyeprogenv.user;

import java.util.Arrays;

import hu.nye.vpe.nyeprogenv.SecurityConfigurationTest;
import hu.nye.vpe.nyeprogenv.login.LoginController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(SecurityConfigurationTest.class)
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;


    @Test
    public void testShowUserList() throws Exception {
        // Given
        User user1 = new User();
        user1.setEmail("user1@email.com");
        user1.setPassword("password1");
        user1.setFirstName("user1");
        user1.setLastName("user1");
        user1.setEnabled(true);

        User user2 = new User();
        user2.setEmail("user2@email.com");
        user2.setPassword("password");
        user2.setFirstName("user2");
        user2.setLastName("user2");

        given(userService.listAll()).willReturn(Arrays.asList(user1, user2));

        mvc.perform(MockMvcRequestBuilders
                .get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("listUsers", hasSize(2)));

    }
}