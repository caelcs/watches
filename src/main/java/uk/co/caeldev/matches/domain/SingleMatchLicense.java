package uk.co.caeldev.matches.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SingleMatchLicense extends BaseLicense<Match> {
    List<Match> matches;

    public SingleMatchLicense() {
        matches = new ArrayList<>();
    }

    @Override
    public LicenseType getType() {
        return LicenseType.SINGLE_MATCH;
    }

    @Override
    public void put(Match source) {
        matches.add(source);
    }
}
