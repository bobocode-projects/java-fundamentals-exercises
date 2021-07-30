package com.bobocode.basics.util;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class BaseEntity {
    protected UUID uuid;
    protected LocalDateTime createdOn;
}
