package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.models.Book;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

@Data
public class JsonParser {
    private String filePath = "/Users/igor/Downloads/BookApplication-main/src/main/file.txt";
    private ObjectMapper objectMapper;
    private File dataFile;

    public JsonParser(String filePath) {
        this.objectMapper = new ObjectMapper();
        this.dataFile = new File(filePath);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
                objectMapper.writeValue(dataFile, new HashMap<Integer, Book>());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Cat> getAllEntities() {
        try {
            // Чтение JSON-объекта из файла
            List<Cat> entityList = objectMapper.readValue(filePath, new TypeReference<>() {});

            // Итерация по сущностям
            for (Cat entity : entityList) {
                System.out.println(entity);
            }
            return entityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveOrUpdate(Cat... entity) {
        try (OutputStream outputStream = new FileOutputStream(dataFile)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS);
            String jsonString = objectMapper.writeValueAsString(entity);
                outputStream.write(jsonString.getBytes());
                outputStream.write(System.lineSeparator().getBytes()); // добавляем перевод строки

            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File file = new File("/Users/igor/Downloads/BookApplication-main/src/main/file.txt");
        JsonParser reader = new JsonParser(file.getPath());
//        Book book = new Book("TestFromJsonReader", "Vasya", 2020);
        Book book1 = new Book();
        Cat cat = new Cat();
        reader.saveOrUpdate(cat);
        List<Cat> cats = reader.getAllEntities();
    }
}
@Data
class Cat {
    int weight;
    String name;
}