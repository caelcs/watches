package uk.co.caeldev.matches.domain;

import lombok.Data;

@Data
public abstract class BaseLicense<T> implements License<T> {
    protected User user;
}
