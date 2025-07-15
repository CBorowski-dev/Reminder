package de.borowski.reminder;

import java.time.LocalDate;

public class Deadline {
    LocalDate date;
    boolean done;

    /**
     *
     * @param date
     * @param done
     */
    public Deadline(LocalDate date, boolean done) {
        this.date = date;
        this.done = done;
    }

    @Override
    public String toString() {
        return "Deadline{" +
                "date=" + date +
                ", done=" + done +
                '}';
    }

}
