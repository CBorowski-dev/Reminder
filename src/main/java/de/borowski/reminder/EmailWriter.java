package de.borowski.reminder;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmailWriter implements ItemWriter<ToDo> {

    @Autowired
    EmailServiceImpl emailService;

    /**
     *
     * @param items
     * @throws Exception
     */
    @Override
    public void write(Chunk<? extends ToDo> items) throws Exception {
        for (ToDo item : items) {
            List<Deadline> deadlines = item.deadlines;
            if (!deadlines.isEmpty()) {
                for (Deadline dl: deadlines)
                    emailService.sendSimpleMessage(item.mailAddress, item.topic, item.description + "\n\n" + dl.date);
            }
        }
    }

}
