package uk.co.caeldev.matches.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.caeldev.matches.domain.*;
import uk.co.caeldev.matches.repositories.LicenseInMemoryRepository;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock
    LicenseInMemoryRepository licenseInMemoryRepository;

    @InjectMocks
    MatchService matchService;

    @Test
    public void testGetMatchesByUserIdFromSingleMatchLicense() {
        //Given
        UUID userId = UUID.randomUUID();

        //And
        SingleMatchLicense singleMatchLicense = new SingleMatchLicense();
        singleMatchLicense.put(Match.builder().id(UUID.randomUUID()).build());
        when(licenseInMemoryRepository.findByUserId(userId)).thenReturn(List.of(singleMatchLicense));

        //When
        List<Match> matchesByUserId = matchService.getMatchesByUserId(userId);

        //Then
        assertThat(matchesByUserId).hasSize(1);
    }

    @Test
    public void testGetMatchesByUserIdFromTournamentLicense() {
        //Given
        UUID userId = UUID.randomUUID();

        //And
        License tournamentLicense = new TournamentLicense();
        tournamentLicense.put(Tournament.builder().id(UUID.randomUUID()).matches(List.of(Match.builder().id(UUID.randomUUID()).build())).build());
        when(licenseInMemoryRepository.findByUserId(userId)).thenReturn(List.of(tournamentLicense));

        //When
        List<Match> matchesByUserId = matchService.getMatchesByUserId(userId);

        //Then
        assertThat(matchesByUserId).hasSize(1);
    }
}