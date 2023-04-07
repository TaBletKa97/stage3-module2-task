package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.BaseEntity;
import com.mjc.school.repository.model.data.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<E extends BaseEntity<Long>>
        implements BaseRepository<E , Long> {
    protected List<E> data;
    protected DataSource dataSource;

    @PostConstruct
    abstract void init();

    @Override
    public List<E> readAll() {
        return data;
    }

    @Override
    public Optional<E> readById(Long id) {
        return data.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public E create(E entity) {
        data.sort(Comparator.comparing(E::getId));
        if (!data.isEmpty()) {
            entity.setId(data.get(data.size() - 1).getId() + 1);
        } else {
            entity.setId(1L);
        }
        data.add(entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        return data.remove(readById(id).get());
    }

    @Override
    public boolean existById(Long id) {
        return data.stream().anyMatch(a -> id.equals(a.getId()));
    }
}
