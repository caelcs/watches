package uk.co.caeldev.matches.controler;

import uk.co.caeldev.matches.domain.Match;
import uk.co.caeldev.matches.domain.SummaryType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConverterFunctions {

    private static Function<Match, String> buildAvBSummary = (Match match) -> String.format("%s vs %s", match.getPlayerA(), match.getPlayerB());

    private static BiFunction<Match, SummaryType, String> buildAvBTimeSummary = (Match match, SummaryType summaryType) -> {
        if (match.getStartDate().isAfter(LocalDateTime.now())) {
            return String.format("%s vs %s, starts in %s minutes", match.getPlayerA(), match.getPlayerB(),
                    LocalDateTime.now().until(match.getStartDate(), ChronoUnit.MINUTES));
        }
        return String.format("%s vs %s, started %x minutes ago", match.getPlayerA(), match.getPlayerB(),
                match.getStartDate().until(LocalDateTime.now(), ChronoUnit.MINUTES));
    };

    public static BiFunction<Match, SummaryType, String> buildSummary = (Match match, SummaryType summaryType) ->
            Optional.ofNullable(summaryType)
                    .map(it -> summaryType == SummaryType.AvB ? buildAvBSummary.apply(match) : buildAvBTimeSummary.apply(match, summaryType))
                    .orElse(null);


    public static BiFunction<Match, SummaryType, MatchResource> toMatchResource = (Match match, SummaryType summaryType) ->
            MatchResource.builder()
                    .id(match.getId())
                    .name(match.getName())
                    .playerA(match.getPlayerA())
                    .playerB(match.getPlayerB())
                    .startDate(match.getStartDate())
                    .summary(buildSummary.apply(match, summaryType))
                    .build();


    public static BiFunction<List<Match>, SummaryType, List<MatchResource>> toMatchesResource = (List<Match> matches, SummaryType summaryType) ->
            matches.stream()
                    .map(it -> toMatchResource.apply(it, summaryType))
                    .collect(Collectors.toList());
}
