package win.bojack.ssm_test.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegController {

    @RequestMapping("/toReg")
    public String toRegView() {
        return "reg";
    }

    @RequestMapping("/doReg")
    public String doReg(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("插入数据");
        return "index";
    }

    @RequestMapping("selectOne")
    public String selectOne(Model model , @RequestParam("uid") int uid) {
        System.out.println("paramter : " + uid);
        String username = "tom";
        // 将数据存放到model中，向jsp传递
        model.addAttribute("myusername", username);
        return "selectOne";
    }

    @RequestMapping("selectAll")
    public String selectAll(Model model, @RequestParam("uid") int uid) {
        System.out.println("parameter: " + uid);
        List<User> list = new ArrayList<User>();
        for (int i =0;i<40; i++) {
            User u = new User();
            u.setAge(i+12);
            u.setId(i+1);
            u.setName("tom" + i);
            list.add(u);
        }
        model.addAttribute("users",list);
        return "selectAll";
    }
}
