package genetic.storage;

import genetic.subjects.Subject;

import java.util.*;

public class SubjectStorage {

    private HashMap<String, List<Subject>> storage = new HashMap<>();

    public HashMap<String, List<Subject>> getStorage() {
        return storage;
    }

    public void setStorage(HashMap<String, List<Subject>> storage) {
        this.storage = storage;
    }

    public void addSubject(Subject subject) {
        if (storage.containsKey(subject.getName())) {
            storage.get(subject.getName()).add(subject);
        } else {
            storage.put(subject.getName(), new ArrayList<>(Arrays.asList(subject)));
        }
    }

    public Set<String> getSubjectNames() {
        return storage.keySet();
    }

    public int getSize() {
        return storage.size();
    }

    public List<Subject> getSubjectPossibleVariants(String subjectName) {
        return storage.get(subjectName);
    }
}
