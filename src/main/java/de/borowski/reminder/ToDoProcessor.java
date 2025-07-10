package de.borowski.reminder;

import org.springframework.batch.item.ItemProcessor;

public class ToDoProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) throws Exception {
        return item.toUpperCase();  // Converts each string to uppercase
    }
}
