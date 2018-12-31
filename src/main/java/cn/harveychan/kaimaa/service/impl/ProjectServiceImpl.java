package cn.harveychan.kaimaa.service.impl;

import cn.harveychan.kaimaa.dao.ProjectRepository;
import cn.harveychan.kaimaa.po.Project;
import cn.harveychan.kaimaa.service.ProjectService;
import cn.harveychan.kaimaa.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SnowflakeIdWorker idWorker;

    @Override
    public Page<Project> listProject(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Project saveProject(Project project) {
        if (project.getId() == null) {
            project.setId(idWorker.nextId());
            project.setCreateTime(new Date());
            project.setUpdateTime(project.getCreateTime());
        }
        return projectRepository.save(project);
    }
}
