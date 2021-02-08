package uk.co.caeldev.matches.repositories;

import org.springframework.stereotype.Component;
import uk.co.caeldev.matches.domain.License;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class LicenseInMemoryRepository implements LicenseRepository {

    List<License<?>> licenses;

    @Override
    public List<License<?>> findByUserId(UUID userId) {
        return licenses.stream()
                .filter(it -> it.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public void addLicense(License<?> license) {
        licenses.add(license);
    }

    @Override
    public void addAllLicenses(List<License<?>> licenses) {
        this.licenses.addAll(licenses);
    }

}
