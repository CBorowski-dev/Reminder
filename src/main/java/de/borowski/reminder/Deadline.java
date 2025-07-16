package de.borowski.reminder;

import java.time.LocalDate;

public record Deadline(LocalDate date, boolean done) {}
