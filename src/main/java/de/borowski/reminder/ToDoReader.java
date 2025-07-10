package de.borowski.reminder;

import org.springframework.batch.item.ItemReader;

public class ToDoReader implements ItemReader<String> {
    private String[] data = {"Spring", "Batch", "Example"};
    private int index = 0;

    @Override
    public String read() throws Exception {
        if (index < data.length) {
            return data[index++]; // Returns the next string in the array
        } else {
            return null;  // Signals the end of reading
        }
    }
}
