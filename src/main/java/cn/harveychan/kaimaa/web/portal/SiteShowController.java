package cn.harveychan.kaimaa.web.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 酷站控制器
 */
@Controller
public class SiteShowController {

    @GetMapping("/sites")
    public String sites() {
        return "portal/sites";
    }
}
