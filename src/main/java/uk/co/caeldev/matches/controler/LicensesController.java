package uk.co.caeldev.matches.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LicensesController {

    @GetMapping("/users/{userId}/matches")
    public ResponseEntity<MatchesResource> getMatches(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok().build();
    }
}
