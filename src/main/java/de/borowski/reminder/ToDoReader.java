package de.borowski.reminder;

import jakarta.json.*;
import org.springframework.batch.item.ItemReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ToDoReader implements ItemReader<String> {
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
    public String read() throws Exception {
        if (jsonArray != null && index < jsonArray.size()) {
            JsonObject todo =  jsonArray.getJsonObject(index++);
            StringBuffer sb = new StringBuffer();
            sb.append(String.format("%s : %s --> %s\n", todo.getString("todo"), todo.getString("description"), todo.getString("mailAddress")));
            JsonArray dates = todo.getJsonArray("dates");
            for (JsonValue jv : dates) {
                sb.append(jv.toString());
                sb.append('\n');
            }
            return sb.toString();
        } else {
            return null;  // Signals the end of reading
        }
    }
}
