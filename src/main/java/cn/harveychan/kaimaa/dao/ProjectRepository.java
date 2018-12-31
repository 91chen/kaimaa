package cn.harveychan.kaimaa.dao;

import cn.harveychan.kaimaa.po.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
