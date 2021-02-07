package uk.co.caeldev.matches.controler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LicensesController.class)
public class LicensesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllMatchesWhenTournamentLicense() throws Exception {
        UUID userId = UUID.randomUUID();
        mockMvc.perform(get("/users/{userId}/matches", userId.toString()))
                .andExpect(status().is(200));
    }
}
