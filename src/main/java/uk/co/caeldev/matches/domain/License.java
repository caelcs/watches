package uk.co.caeldev.matches.domain;

import java.util.List;

public interface License<T> {
    LicenseType getType();
    List<Match> getMatches();
    void put(T source);
}
