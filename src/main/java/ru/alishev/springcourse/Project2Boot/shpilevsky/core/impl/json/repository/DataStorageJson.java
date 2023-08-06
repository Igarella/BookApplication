package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.models.ABaseEntity;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.SortType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class DataStorageJson<Book extends ABaseEntity, K extends Serializable> implements IDataStorage<Book, K> {
    private IRepositoryJson<Book, K> repositoryJson;
    private ObjectMapper objectMapper;
    private File dataFile;
    private Function<Book, K> keyFunction;


    public DataStorageJson(String filePath) {
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

    @Override
    public K getKey(Book entity) {
        return keyFunction.apply(entity);
    }

    @Override
    public void add(Book entity) {
        saveOrUpdate(entity);
    }

    @Override
    public void add(List<Book> entities) {
        saveOrUpdate((Book[]) entities.toArray());
    }

    @Override
    public void delete(List<Book> entities) {

    }

    @Override
    public void delete(Predicate<Book> condition) {

    }

    @Override
    public Book findFirst(Predicate<Book> condition) {
        return null;
    }

    @Override
    public List<Book> find(Predicate<Book> condition) {
        return null;
    }

    @Override
    public List<Book> find(Predicate<Book> condition, SortType sortType) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return getAllEntities();
    }

    @Override
    public void delete(Book entity) {
        try {
            // Получаем путь к файлу
            Path fileToDeletePath = Paths.get(dataFile.getPath());

            // Проверяем существует ли файл
            if (Files.exists(fileToDeletePath)) {
                // Удаляем файл
                Files.delete(fileToDeletePath);
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("File does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAll() {

    }

    private void saveOrUpdate(Book... entity) {
        try (OutputStream outputStream = new FileOutputStream(dataFile)) {
            ObjectMapper objectMapper = new ObjectMapper();{
                String jsonString = objectMapper.writeValueAsString(entity);
                outputStream.write(jsonString.getBytes());
                outputStream.write(System.lineSeparator().getBytes()); // добавляем перевод строки
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private List<Book> selectByCriteria(Predicate<Book> condition, SortType sortType) throws IOException {
//        List<Book> entities = new ArrayList<>();
//
//        // Чтение JSON из файла
//        JsonNode rootNode = objectMapper.readTree(new File(filePath));
//        ArrayNode entitiesNode = (ArrayNode) rootNode.get("entities");
//
//        for (JsonNode entityNode : entitiesNode) {
//            Book entity = (Book) objectMapper.convertValue(entityNode, ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.models.Book.class);
//
//            if (condition.test(entity)) {
//                entities.add(entity);
//            }
//        }

        // Сортировка сущностей
//        entities.sort((e1, e2) -> {
//            if (sortType == SortType.ASC) {
//                return e1.().compareTo(e2.getField());
//            } else {
//                return e2.getField().compareTo(e1.getField());
//            }
//        });

//        return entities;
//    }

    private List<Book> getAllEntities() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Чтение JSON-объекта из файла
            List<Book> entityList = objectMapper.readValue(dataFile, new TypeReference<List<Book>>() {});

            // Итерация по сущностям
            for (Book entity : entityList) {
                System.out.println(entity);
            }
            return entityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    public static void main(String[] args) {
        File file = new File("/Users/igor/Downloads/BookApplication-main/src/main/file.txt");
        DataStorageJson dataStorageJson = new DataStorageJson("/Users/igor/Downloads/BookApplication-main/src/main/file.txt");
        dataStorageJson.add(new ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.models.Book("TestFile2", "Pupkinsssss", 2001));
        dataStorageJson.getAllEntities();
    }
}
