package de.borowski.reminder;

import jakarta.json.*;
import org.springframework.batch.item.ItemReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ToDoReader implements ItemReader<ToDo> {
    private int index = 0;
    private JsonArray jsonArray;

    public ToDoReader() {
        super();
        JsonReader reader = null;
        try {
            reader = Json.createReader(
                    new FileReader("/home/christoph/Projects/IdeaProjects/Reminder/src/main/resources/todos.json"));
            // Parse the JSON string into a JsonObject
            jsonArray = reader.readArray();
        } catch (FileNotFoundException e) {
            // do nothing
        }
    }

    /**
     *
     * @return
     */
    @Override
    public ToDo read() {
        if (jsonArray != null && index < jsonArray.size()) {
            JsonObject json =  jsonArray.getJsonObject(index++);
            List<Deadline> deadlinesList = new ArrayList<>();
            JsonArray deadlines = json.getJsonArray("deadlines");
            for (int i=0; i< deadlines.size(); i++) {
                JsonObject dl = deadlines.getJsonObject(i);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.GERMAN);
                LocalDate date = LocalDate.parse(dl.getString("date"), formatter);
                deadlinesList.add(new Deadline(date, dl.getBoolean("done")));
            }
            return new ToDo(json.getString("topic"), json.getString("description"), json.getString("mailAddress"), deadlinesList);
        } else {
            return null;  // Signals the end of reading
        }
    }

}
