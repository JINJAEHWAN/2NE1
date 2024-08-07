package camp.controller;

import java.util.List;

public interface modelDAO<T> {

    //CRUD
    void createModel(String... id);

    void readModel(String id);

    void updateModel(String key, String value, String... id);

    boolean deleteModel(String id);

    boolean deleteModelAll(String id);

    List<T> getAllModelList();

}
