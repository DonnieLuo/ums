package com.controller;

import com.entity.User;
import com.google.common.collect.Maps;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by donnie on 17-6-18.
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("add")
    public ModelAndView add(@ModelAttribute("user")User user) {
        return new ModelAndView("addUser").addObject("user", user);
    }
    @RequestMapping("edit")
    public ModelAndView edit(@RequestParam(name = "id") long id) {
        User user = userService.findById(id);
        return new ModelAndView("editUser").addObject("user", user);
    }
    @RequestMapping("delete")
    public @ResponseBody
    Map<String,Object> deleteUser(@RequestParam(name = "id") long userId) {
        userService.delete(userId);
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", "ok");
        return map;
    }
    @RequestMapping("save")
    public String saveUser(@ModelAttribute("user")User user) throws Exception {

        userService.insert(user);
        return "redirect:/";
    }
    @RequestMapping("table")
    public @ResponseBody
    List<User> table() throws Exception {
        List<User> list = userService.list();
        return list;
    }
    @RequestMapping("update")
    public String updateUser(@ModelAttribute("user")User user) throws Exception {

        userService.update(user);
        return "redirect:/";
    }
}
