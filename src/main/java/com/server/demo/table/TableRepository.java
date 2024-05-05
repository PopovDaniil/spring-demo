package com.server.demo.table;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

public interface TableRepository extends CrudRepository<Table, Long> {
    @Nullable
    Table findByName(String name);
}
