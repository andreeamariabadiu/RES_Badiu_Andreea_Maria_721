package Repository;

import java.util.List;

public interface CrudRepository<T>{
    List<T> findAll();
    T findById(Integer id);
    void save(T entity);
    void deleteById(Integer id);
}
