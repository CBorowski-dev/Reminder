package de.borowski.reminder;

import java.util.Date;

public class Deadline {
    Date date;
    boolean done;

    /**
     *
     * @param date
     * @param done
     */
    public Deadline(Date date, boolean done) {
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
