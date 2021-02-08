package uk.co.caeldev.matches.repositories;

import uk.co.caeldev.matches.domain.License;

import java.util.List;
import java.util.UUID;

public interface LicenseRepository {

    List<License<?>> findByUserId(UUID userId);
    void addLicense(License<?> license);
    void addAllLicenses(List<License<?>> licenses);
}
