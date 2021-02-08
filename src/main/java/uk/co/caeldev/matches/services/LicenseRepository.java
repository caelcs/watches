package uk.co.caeldev.matches.services;

import org.springframework.stereotype.Component;
import uk.co.caeldev.matches.domain.License;

import java.util.List;
import java.util.UUID;

@Component
public class LicenseRepository {

    public List<License<?>> findByUserId(UUID userId) {
        return null;
    }
}
