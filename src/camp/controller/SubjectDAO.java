package camp.controller;

import camp.model.Subject;

import java.util.List;

public class SubjectDAO implements modelDAO {
    private static List<Subject> subjectStore;

    @Override
    public void createModel(String... id) {

    }

    @Override
    public void readModel(String subjectId) {

    }


    @Override
    public void updateModel(String key, String value, String... id) {

    }

    @Override
    public boolean deleteModel(String id) {
        return true;
    }

    @Override
    public boolean deleteModelAll(String id) {
        return false;
    }

    @Override
    public List getAllModelList() {
        return List.of();
    }

    //@Override
    public String sequence(String type) {
        return "";
    }
}
