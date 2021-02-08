package uk.co.caeldev.matches.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode
public class User {
    UUID id;
    String name;
}
