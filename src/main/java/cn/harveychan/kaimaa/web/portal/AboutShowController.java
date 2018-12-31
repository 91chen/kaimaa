package cn.harveychan.kaimaa.web.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 博主信息控制器
 */
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about() {
        return "portal/about";
    }
}
