package cn.harveychan.kaimaa.service;

import cn.harveychan.kaimaa.po.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    public Page<Project> listProject(Pageable pageable);

    public Project saveProject(Project project);
}
