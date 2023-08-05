package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.models.ABaseEntity;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.models.Book;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.SortType;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class DataStorageJson<E extends ABaseEntity, K extends Serializable> implements IDataStorage<E, K> {
    private IRepositoryJson<E, K> repositoryJson;
    private String filePath;
    private ObjectMapper objectMapper;
    private File dataFile;
    private Function<E, K> keyFunction;


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
    public K getKey(E entity) {
        return keyFunction.apply(entity);
    }

    @Override
    public void add(E entity) {
        save(entity);
    }

    @Override
    public void add(List<E> entities) {
        save((E[]) entities.toArray());
    }

    @Override
    public void delete(E entity) {
        try {
            // Получаем путь к файлу
            Path fileToDeletePath = Paths.get(filePath);

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
    public void delete(List<E> entities) {

    }

    @Override
    public void delete(Predicate<E> condition) {

    }

    @Override
    public E findFirst(Predicate<E> condition) {
        return null;
    }

    @Override
    public List<E> find(Predicate<E> condition) {
        return null;
    }

    @Override
    public List<E> find(Predicate<E> condition, SortType sortType) {
        return null;
    }

    @Override
    public List<E> getAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    public <T> void save(E... entity) {
        try {
            objectMapper.writeValue(new File(filePath), entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
