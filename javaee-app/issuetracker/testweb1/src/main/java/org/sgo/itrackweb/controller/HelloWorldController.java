package org.sgo.itrackweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Simon
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/hello")
    public ModelAndView helloWorld() {
        String message = "Ey upff";
        return new ModelAndView("hello", "message", message);
    }

}
