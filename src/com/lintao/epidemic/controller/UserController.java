package com.lintao.epidemic.controller;

import com.lintao.epidemic.bean.UserInfo;
import com.lintao.epidemic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * controller层
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user：封装前台请求的数据，表单name中的值跟user中的属性一致
     * @param model：将后台数据传递给前台
     * @param session：传统的session
     * @return
     */
    @RequestMapping("/login")
    public String login(UserInfo user, Model model, HttpSession session){
        //获取用户信息
        UserInfo u = userService.findByAccount(user);
        if(u==null){
            //账号不正确
            model.addAttribute("msg","账号不正确！");
            return "login";
        }
        if(u.getPassword().equals(user.getPassword())){
            //登录成功
            //将当前用户的信息保存到session中
            session.setAttribute("loginUser",u);
            return "redirect:/data_input.jsp";
        }
        //账号不正确
        model.addAttribute("msg","账号不正确！");
        return "login";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //清理session
        session.invalidate();
        return "redirect:/epidemic.jsp";
    }

}
