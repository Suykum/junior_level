package ru.job4j.generic;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private int size;
    private SimpleArray<T> storeData;

    public AbstractStore(int size) {
        storeData = new SimpleArray<>(size);
        this.size = size;
    }
    @Override
    public void add(T model) {
        storeData.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (storeData.get(i).getId().equals(id)) {
                storeData.set(i, model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (storeData.get(i).getId().equals(id)) {
                storeData.delete(i);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T elem = null;
        for (int i = 0; i < size; i++) {
            if (storeData.get(i).getId().equals(id)) {
                 elem = storeData.get(i);
                break;
            }
        }
        return  elem;
    }
}
