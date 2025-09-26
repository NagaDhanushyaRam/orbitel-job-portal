package com.orbitel.jobportal.dto;

public record JobDTO(
    Long   id,
    String title,
    String company,
    String location
) {}
