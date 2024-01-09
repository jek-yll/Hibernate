package dao;

import java.util.List;

public interface BaseDAO<T>{

    public abstract boolean create(T element);
    public abstract boolean update(T element);
    public abstract boolean delete(Long id);
    public T getById (Long id);

    public List<T> getALL();

}
