package de.borowski.reminder;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class EmailWriter implements ItemWriter<String> {
    @Override
    public void write(Chunk<? extends String> items) throws Exception {
        for (String item : items) {
            System.out.println(item);  // Writes each processed item to the console
        }
    }
}
