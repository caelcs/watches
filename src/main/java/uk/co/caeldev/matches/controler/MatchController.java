package uk.co.caeldev.matches.controler;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.co.caeldev.matches.domain.Match;
import uk.co.caeldev.matches.services.MatchService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class MatchController {

    MatchService matchService;

    @GetMapping("/users/{userId}/matches")
    public ResponseEntity<MatchesResource> getMatches(@PathVariable("userId") UUID userId) {
        List<Match> matches = matchService.getMatchesByUserId(userId);

        if (matches.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .ok(MatchesResource.builder().matches(matches).build());
    }
}
