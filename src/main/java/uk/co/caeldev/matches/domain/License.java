package uk.co.caeldev.matches.domain;

import java.util.List;
import java.util.UUID;

public interface License<T> {
    UUID getUserId();

    LicenseType getType();

    List<Match> getMatches();

    void put(T source);
}
