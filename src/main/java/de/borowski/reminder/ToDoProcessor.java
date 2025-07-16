package de.borowski.reminder;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.util.List;

public class ToDoProcessor implements ItemProcessor<ToDo, ToDo> {

    /**
     *
     * @param item
     * @return
     */
    @Override
    public ToDo process(ToDo item) {
        List<Deadline> deadlines = item.deadlines();
        for (int i=deadlines.size()-1; i>=0; i--) {
            Deadline dl = deadlines.get(i);
            // ToDo already completed or is more than 10 days in the future --> not relevant, so remove
            if (dl.done() || dl.date().minusDays(10).isAfter(LocalDate.now())) deadlines.remove(i);
        }
        return (deadlines.isEmpty()) ? null : item;
    }

}
