package uk.co.caeldev.matches.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.caeldev.matches.domain.License;
import uk.co.caeldev.matches.domain.Match;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LicenseService {

    LicenseRepository licenseRepository;

    public List<Match> getMatchesByUserId(UUID userId) {
        List<License<?>> licenses = licenseRepository.findByUserId(userId);
        return licenses.stream()
                .flatMap(it -> it.getMatches().stream())
                .collect(Collectors.toList());
    }
}
