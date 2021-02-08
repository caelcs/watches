package uk.co.caeldev.matches.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TournamentLicense extends BaseLicense<Tournament> {

    Tournament tournament;

    @Override
    public LicenseType getType() {
        return LicenseType.TOURNAMENT;
    }

    @Override
    public List<Match> getMatches() {
        return tournament.getMatches();
    }

    @Override
    public void put(Tournament source) {
        tournament = source;
    }
}
