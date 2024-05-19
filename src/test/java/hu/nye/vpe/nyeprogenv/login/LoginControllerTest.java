package hu.nye.vpe.nyeprogenv.login;

import hu.nye.vpe.nyeprogenv.SecurityConfigurationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
@Import(SecurityConfigurationTest.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void testLogin() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .get("/login"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
