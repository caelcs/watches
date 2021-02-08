package uk.co.caeldev.matches.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Tournament {
    UUID id;
    String name;
    List<Match> matches;
}
