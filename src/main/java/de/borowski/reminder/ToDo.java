package de.borowski.reminder;

import java.util.List;

public record ToDo(String topic, String description, String mailAddress, List<Deadline> deadlines) {}