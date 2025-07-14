package de.borowski.reminder;

import jakarta.json.*;
import org.springframework.batch.item.ItemReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    public ToDo read() throws Exception {
        if (jsonArray != null && index < jsonArray.size()) {
            JsonObject json =  jsonArray.getJsonObject(index++);
            ToDo todo = new ToDo(json.getString("topic"), json.getString("description"), json.getString("mailAddress"));
            JsonArray deadlines = json.getJsonArray("deadlines");
            for (int i=0; i< deadlines.size(); i++) {
                JsonObject dl = deadlines.getJsonObject(i);
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
                Date date = format.parse(dl.getString("date"));
                todo.addDeadline(new Deadline(date, dl.getBoolean("done")));
            }
            return todo;
        } else {
            return null;  // Signals the end of reading
        }
    }
}
