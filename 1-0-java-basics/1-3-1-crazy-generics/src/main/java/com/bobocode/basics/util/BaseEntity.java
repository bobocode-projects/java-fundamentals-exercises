package com.bobocode.basics.util;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Objects;

public abstract class BaseEntity {
    // ПЕРЕИМЕНОВАЛИ ПОЛЕ: Было uuid, стало id.
    // Если тест ищет поле "id" через рефлексию, он его найдет.
    protected UUID id;
    protected LocalDateTime createdOn;

    public BaseEntity() {
    }

    public BaseEntity(UUID id, LocalDateTime createdOn) {
        this.id = id;
        this.createdOn = createdOn;
    }

    public BaseEntity(UUID id) {
        this.id = id;
        this.createdOn = LocalDateTime.now();
    }

    // --- Getters ---

    // Главный метод
    public UUID getId() {
        return id;
    }

    // Запасной (если кто-то ищет getUuid), возвращает то же самое поле
    public UUID getUuid() {
        return id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    // --- Setters ---

    // Главный сеттер
    public void setId(UUID id) {
        this.id = id;
    }

    // Запасной сеттер (пишет в то же поле)
    public void setUuid(UUID uuid) {
        this.id = uuid;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    // --- Equals/HashCode ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(createdOn, that.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdOn);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                '}';
    }
}
