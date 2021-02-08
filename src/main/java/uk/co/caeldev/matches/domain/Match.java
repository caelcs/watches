package uk.co.caeldev.matches.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Match {
    UUID id;
    String name;
    LocalDateTime startDate;
    String playerA;
    String playerB;
    SummaryType summaryType;
    Tournament tournament;
}
