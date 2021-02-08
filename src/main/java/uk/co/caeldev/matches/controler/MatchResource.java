package uk.co.caeldev.matches.controler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.caeldev.matches.domain.Match;
import uk.co.caeldev.matches.domain.SummaryType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchResource {

    UUID id;
    String name;
    LocalDateTime startDate;
    String playerA;
    String playerB;
    String summary;

    public static MatchResource from(Match match, SummaryType summaryType) {
        return MatchResource.builder()
                .id(match.getId())
                .name(match.getName())
                .playerA(match.getPlayerA())
                .playerB(match.getPlayerB())
                .startDate(match.getStartDate())
                .summary(getSummary(match, summaryType))
                .build();
    }

    private static String getSummary(Match match, SummaryType summaryType) {
        return Optional.ofNullable(summaryType)
                .map(it -> summaryType == SummaryType.AvB? getAvBSummary(match): getAvBTime(match))
                .orElse(null);
    }

    private static String getAvBTime(Match match) {
        if (match.getStartDate().isAfter(LocalDateTime.now())) {
            return String.format("%s vs %s, starts in %s minutes", match.getPlayerA(), match.getPlayerB(),
                    LocalDateTime.now().until(match.getStartDate(), ChronoUnit.MINUTES));
        }
        return String.format("%s vs %s, started %x minutes ago", match.getPlayerA(), match.getPlayerB(),
                match.getStartDate().until(LocalDateTime.now(), ChronoUnit.MINUTES));
    }

    private static String getAvBSummary(Match match) {
        return String.format("%s vs %s", match.getPlayerA(), match.getPlayerB());
    }
}
