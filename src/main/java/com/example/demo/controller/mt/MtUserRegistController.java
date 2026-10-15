package com.example.demo.controller.mt;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.User;
import com.example.demo.form.user.UserRegisterForm;
import com.example.demo.service.UserService;

public class MtUserRegistController {
	
    private final UserService userService;

    public MtUserRegistController(UserService userService) {
        this.userService = userService;
    }
	
 
  @GetMapping("/user-regist")
  public String adminUser(Model model) {
      // 最新のユーザーリストを取得
      List<User> users = userService.getAllUsers();

      // ビューにユーザーリストを渡す
      model.addAttribute("users", users);
      
      // すでにインスタンスが存在する場合は行わない
      if (!model.containsAttribute("userRegisterForm")) {
          // ビューにフォームクラスのインスタンスを渡す
          model.addAttribute("userRegisterForm", new UserRegisterForm());
      }

      return "mt/mt-user-regist";
  }

}
