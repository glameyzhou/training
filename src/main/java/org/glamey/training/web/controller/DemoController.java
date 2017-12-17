package org.glamey.training.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.13.21.
 */

@Controller
@RequestMapping("/demo")
public class DemoController {

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index() {
    return "this is the first demo page.";
  }
}
