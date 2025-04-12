package www.com.Project.trim;



import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Десериализатор для автоматического обрезания пробелов в строковых полях.
 */
public class Trimm extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        // Обрезаем пробелы, если значение не null
        return (value != null) ? value.trim() : null;
    }
}