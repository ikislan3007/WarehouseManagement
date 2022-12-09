package com.example.warehousemanagement.base;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E, I> extends JpaRepository<E, I> {

    @Query("select t.id from #{#entityName} t where t.id in :ids")
    Set<Long> findExistingIds(List<I> ids);

}
