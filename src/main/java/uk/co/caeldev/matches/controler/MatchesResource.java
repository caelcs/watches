package uk.co.caeldev.matches.controler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.caeldev.matches.domain.Match;
import uk.co.caeldev.matches.domain.SummaryType;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchesResource {
    List<MatchResource> matches;

    public static List<MatchResource> from(List<Match> matches, SummaryType summaryType) {
        return matches.stream()
                .map(it ->MatchResource.from(it, summaryType))
                .collect(Collectors.toList());
    }

}
