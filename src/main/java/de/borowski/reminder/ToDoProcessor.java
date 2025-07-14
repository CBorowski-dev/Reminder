package de.borowski.reminder;

import org.springframework.batch.item.ItemProcessor;

import java.util.List;

public class ToDoProcessor implements ItemProcessor<ToDo, ToDo> {
    @Override
    public ToDo process(ToDo item) throws Exception {
        List<Deadline> deadlines = item.deadlines;
        for (int i=deadlines.size()-1; i>=0; i--) {
            Deadline dl = deadlines.get(i);
            if (dl.done || dl.date.getTime()-System.currentTimeMillis() > 864000000) deadlines.remove(i);
        }
        return (deadlines.isEmpty()) ? null : item;
    }
}
