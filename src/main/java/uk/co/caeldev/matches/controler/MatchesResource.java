package uk.co.caeldev.matches.controler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.caeldev.matches.domain.Match;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchesResource {
    List<Match> matches;
}
