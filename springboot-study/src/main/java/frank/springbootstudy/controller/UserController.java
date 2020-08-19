package frank.springbootstudy.controller;

import frank.springbootstudy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
//当前类型注册实例到容器中，并指定为web请求的处理
@Controller
//可以定义请求相关的配置，请求路径，请求方法等等
@RequestMapping("/user")
public class UserController {

    @Autowired//属于spring框架
    //@Resource jdk提供的注解，表示资源。只提供了规范，没有提供实现
    private Map<Integer,Integer> test1;

    @Autowired
    @Qualifier("user1")
    private User u;

    @Resource(name = "user2")
    private User p;

    @RequestMapping("/login")
    //返回application/json的数据类型，返回值序列化json字符串
    @ResponseBody
    public Object login(User u,HttpServletRequest req) {
        if(!u.getUsername().equals("abc")) {
            throw new RuntimeException("登陆失败");
        }
        HttpSession session = req.getSession();
        session.setAttribute("user",u);
        return u;
    }

    @RequestMapping("/m")
    public String m() {
        //返回静态资源/服务器资源 字符串 路径
        return "/main.html";
    }

    @RequestMapping("/l1")
    public String l1() {
        //返回路径 不带/ 以当前路径为相对位置 查找同一级资源 /user/l1 -> /user/login
        //带/ 以项目部署路径为相对位置  /user/l1 -> /login
        //转发
        return "forward:login";
    }

    @RequestMapping("/l2")
    public String l2() {
        //返回静态资源/服务器资源 字符串 路径
        //重定向
        return "redirect:/user/login";
    }

    @RequestMapping("/test/{key}")//变量占位符
    @ResponseBody
    public Object test1(@PathVariable("key") Integer k) {//类型不匹配，抛出异常
        return test1.get(k);
    }

    //请求Get/user/test2?k1=v1&k2=v2
    @RequestMapping(value = "/test2",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object test2(@RequestParam("k1") String kkk, String k2) {
        System.out.println("==================="+kkk+" "+k2);
        return test1;
    }

    @RequestMapping("/test3")
    @ResponseBody
    public Object test3(User u) {
        System.out.println(u);
        return test1;
    }

    @RequestMapping("/test4")
    @ResponseBody
    public Object test4(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println(req.getParameter("username")+req.getParameter("password"));
        return null;
    }

    @RequestMapping("/test5")
    @ResponseBody
    public Object test5(@RequestBody User u) {//解析请求数据类型application/json 请求体为json 反序列化为java对象
        System.out.println(u);
        return null;
    }

    @RequestMapping("/test6")
    @ResponseBody
    public Object test6() {
        throw new RuntimeException("数据库报错了");
    }

}
