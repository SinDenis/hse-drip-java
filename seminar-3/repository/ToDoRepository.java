package repository;

import entity.ToDoItem;

public interface ToDoRepository {
    void save(ToDoItem item);
    ToDoItem getById(Long id);
    void deleteById(Long id);
    ToDoItem[] getAll();
}
