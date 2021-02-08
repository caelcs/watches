package uk.co.caeldev.matches.controler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

}
