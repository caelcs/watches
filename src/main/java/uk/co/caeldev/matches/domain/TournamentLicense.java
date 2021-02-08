package uk.co.caeldev.matches.domain;

import lombok.Data;

import java.util.List;

@Data
public class TournamentLicense extends BaseLicense<Tournament> {

    Tournament tournament;

    @Override
    public LicenseType getType() {
        return LicenseType.TOURNAMENT;
    }

    @Override
    public List<Match> getMatches() {
        return null;
    }

    @Override
    public void put(Tournament source) {

    }
}
