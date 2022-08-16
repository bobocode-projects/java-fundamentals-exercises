package com.bobocode.jackson.dto;

public record User(Long id, String firstName, String lastName, Address address) {
}
