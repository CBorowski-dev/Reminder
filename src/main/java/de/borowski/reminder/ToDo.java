package de.borowski.reminder;

import java.util.ArrayList;
import java.util.List;

public class ToDo {
    String topic;
    String description;
    String mailAddress;
    List<Deadline> deadlines = new ArrayList<>();

    /**
     *
     * @param topic
     * @param description
     * @param mailAddress
     */
    public ToDo(String topic, String description, String mailAddress) {
        this.topic = topic;
        this.description = description;
        this.mailAddress = mailAddress;
    }

    /**
     *
     * @param deadline
     */
    public void addDeadline(Deadline deadline) {
        deadlines.add(deadline);
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                ", deadlines=" + deadlines +
                '}';
    }

}