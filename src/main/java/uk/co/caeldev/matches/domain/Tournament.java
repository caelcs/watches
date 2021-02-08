package uk.co.caeldev.matches.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Tournament {
    UUID id;
    String name;
    List<Match> matches;
}
