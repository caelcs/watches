package uk.co.caeldev.matches.controler;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.caeldev.matches.domain.Match;
import uk.co.caeldev.matches.domain.SummaryType;
import uk.co.caeldev.matches.services.MatchService;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static uk.co.caeldev.matches.controler.ConverterFunctions.toMatchesResource;

@RestController
@AllArgsConstructor
public class MatchController {

    MatchService matchService;

    @GetMapping("/users/{userId}/matches")
    public ResponseEntity<MatchesResource> getMatches(@PathVariable("userId") UUID userId, @RequestParam(value = "summaryType", required = false) String summaryType) {
        List<Match> matches = matchService.getMatchesByUserId(userId);

        if (matches.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .ok(MatchesResource.builder()
                        .matches(toMatchesResource.apply(matches, isNull(summaryType) ? null : SummaryType.valueOf(summaryType)))
                        .build());
    }
}
