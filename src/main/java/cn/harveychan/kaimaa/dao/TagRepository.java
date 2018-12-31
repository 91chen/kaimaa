package cn.harveychan.kaimaa.dao;

import cn.harveychan.kaimaa.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    public Tag findByName(String name);

    @Query("select t from Tag t")
    public List<Tag> findTop(Pageable pageable);
}
