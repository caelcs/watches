package uk.co.caeldev.matches.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class Match {
    UUID id;
    String name;
    LocalDateTime startDate;
    String playerA;
    String playerB;
    SummaryType summaryType;
    Tournament tournament;
}
