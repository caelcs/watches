package uk.co.caeldev.matches.controler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.caeldev.matches.domain.Match;
import uk.co.caeldev.matches.services.MatchService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MatchController.class)
public class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchService matchService;

    @Test
    public void testGetAllMatchesWhenTournamentLicense() throws Exception {
        //Given
        UUID userId = UUID.randomUUID();
        UUID matchId = UUID.randomUUID();

        //And
        Match expectedMatch = Match.builder()
                .id(matchId)
                .name("test")
                .playerA("Raul")
                .playerB("German")
                .startDate(LocalDateTime.now()).build();
        when(matchService.getMatchesByUserId(userId)).thenReturn(List.of(expectedMatch));

        //When
        mockMvc.perform(get("/users/{userId}/matches", userId.toString()))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.matches[0].id").value(matchId.toString()));
    }

    @Test
    public void testGetNotFoundWhenThereAreNoMatches() throws Exception {
        //Given
        UUID userId = UUID.randomUUID();
        UUID matchId = UUID.randomUUID();

        //And
        when(matchService.getMatchesByUserId(userId)).thenReturn(Collections.emptyList());

        //When
        mockMvc.perform(get("/users/{userId}/matches", userId.toString()))
                .andExpect(status().is(404));
    }
}
