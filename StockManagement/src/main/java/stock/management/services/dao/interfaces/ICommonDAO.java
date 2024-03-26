package stock.management.services.dao.interfaces;

import java.util.List;

public interface ICommonDAO<T> {
    List<T> getAll();

    T getById(int id);

    void add(T obj);

    void update(T obj);
}
