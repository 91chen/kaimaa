package cn.harveychan.kaimaa.web.portal;

import cn.harveychan.kaimaa.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 项目控制器
 */
@Controller
public class ProjectShowController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public String projects(@PageableDefault(size = 15) Pageable pageable, Model model) {
        model.addAttribute("page", projectService.listProject(pageable));
        return "portal/projects";
    }
}
