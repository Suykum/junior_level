package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class Cache {
    ConcurrentHashMap<Integer, Base> cache;


    public Cache() {
        cache = new ConcurrentHashMap<>();
    }

    public void add(Base model) {
        cache.put(model.getId(), model);

    }

    public void delete(Base model) {
        cache.remove(model.getId());
    }

    public void update(Base model) throws OptimisticException {
        AtomicBoolean modifying = new AtomicBoolean(true);
        this.cache.computeIfPresent(model.getId(), (integer, base) -> {
            if (base.getVersion() == model.getVersion()) {
                base.setVersion(base.getVersion() + 1);
            } else {
                modifying.set(false);
            }
            return cache.put(base.getId(), base);
        });
        if (!modifying.get()) {
            throw new OptimisticException("Optimistic Exception found");
        }

    }

    public int getVersion(Base model) {
        return model.getVersion();
    }

}
