package de.perdian.yavin.backend.application.modules.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    String doIndex() {
        return "/index";
    }

}
