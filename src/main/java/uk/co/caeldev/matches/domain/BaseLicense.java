package uk.co.caeldev.matches.domain;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class BaseLicense<T> implements License<T> {

    UUID userId;

}
