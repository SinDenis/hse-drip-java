package repository;

import entity.ToDoItem;

public class InMemoryToDoRepository implements ToDoRepository {
    @Override
    public void save(ToDoItem item) {

    }

    @Override
    public ToDoItem getById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public ToDoItem[] getAll() {
        return new ToDoItem[0];
    }
}
