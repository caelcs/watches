package uk.co.caeldev.matches;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import uk.co.caeldev.matches.domain.Match;
import uk.co.caeldev.matches.domain.SingleMatchLicense;
import uk.co.caeldev.matches.repositories.LicenseRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MatchesApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatchesIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private LicenseRepository licenseRepository;

    @Test
    @DisplayName("Should get the matches")
    public void happyPath() throws Exception {
        //Given
        UUID userId = randomUUID();
        UUID matchId = randomUUID();

        //And
        SingleMatchLicense license = new SingleMatchLicense();
        Match match = Match.builder().id(matchId).name("Test").playerA("German").playerB("Andrew").startDate(LocalDateTime.now().plusMinutes(15)).build();
        license.put(match);
        license.setUserId(userId);
        licenseRepository.addLicense(license);

        //When
        ResponseEntity<String> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/users/" + userId.toString() + "/matches", String.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
}
