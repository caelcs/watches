package uk.co.caeldev.matches.controler;

import org.junit.jupiter.api.DisplayName;
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

import static org.hamcrest.Matchers.containsString;
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
    @DisplayName("should get all the matches where there is a single match license")
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
    @DisplayName("should fail and return 404 when there is no matches")
    public void testGetNotFoundWhenThereAreNoMatches() throws Exception {
        //Given
        UUID userId = UUID.randomUUID();

        //And
        when(matchService.getMatchesByUserId(userId)).thenReturn(Collections.emptyList());

        //When
        mockMvc.perform(get("/users/{userId}/matches", userId.toString()))
                .andExpect(status().is(404));
    }

    @Test
    @DisplayName("should build the summary when the summaryType is AvB")
    public void testGetSummaryWhenSummaryTypeIsAvB() throws Exception {
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
        mockMvc.perform(get("/users/{userId}/matches", userId.toString()).queryParam("summaryType", "AvB"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.matches[0].summary").value("Raul vs German"));
    }

    @Test
    @DisplayName("should build the summary when the summaryType is AvBTime and the startDate is in the future")
    public void testGetSummaryWhenSummaryTypeIsAvBTimeAndStartDateIsInFuture() throws Exception {
        //Given
        UUID userId = UUID.randomUUID();
        UUID matchId = UUID.randomUUID();

        //And
        Match expectedMatch = Match.builder()
                .id(matchId)
                .name("test")
                .playerA("Raul")
                .playerB("German")
                .startDate(LocalDateTime.now().plusMinutes(15)).build();
        when(matchService.getMatchesByUserId(userId)).thenReturn(List.of(expectedMatch));

        //When
        mockMvc.perform(get("/users/{userId}/matches", userId.toString()).queryParam("summaryType", "AvBTime"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.matches[0].summary").value(containsString(", starts in")));
    }

    @Test
    @DisplayName("should build the summary when the summaryType is AvBTime and the startDate is in the past")
    public void testGetSummaryWhenSummaryTypeIsAvBTimeAndStartDateIsInPast() throws Exception {
        //Given
        UUID userId = UUID.randomUUID();
        UUID matchId = UUID.randomUUID();

        //And
        Match expectedMatch = Match.builder()
                .id(matchId)
                .name("test")
                .playerA("Raul")
                .playerB("German")
                .startDate(LocalDateTime.now().minusMinutes(15)).build();
        when(matchService.getMatchesByUserId(userId)).thenReturn(List.of(expectedMatch));

        //When
        mockMvc.perform(get("/users/{userId}/matches", userId.toString()).queryParam("summaryType", "AvBTime"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.matches[0].summary").value(containsString(", started")));
    }
}
