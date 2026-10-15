package com.example.demo.controller.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ① 登録画面の表示
    @GetMapping("/user/register")
    public String showRegisterForm() {
        return "auth/register";
    }

    // ② 登録処理
    @PostMapping("/user/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String passwordConfirm,
                               Model model) {

        // 入力チェック
        if (username.trim().isEmpty()) {
            model.addAttribute("error", "ユーザー名を入力してください");
            return "auth/register";
        }
        if (password.isEmpty() || passwordConfirm.isEmpty()) {
            model.addAttribute("error", "パスワードを入力してください");
            return "auth/register";
        }
        if (!password.equals(passwordConfirm)) {
            model.addAttribute("error", "パスワードが一致しません");
            return "auth/register";
        }

        // 既存ユーザー確認
        if (!userRepository.findByUserName(username).isEmpty()) {
            model.addAttribute("error", "このユーザー名はすでに使われています");
            return "auth/register";
        }

        // 新規ユーザー作成
        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoleId(1); // 一般ユーザー

        userRepository.save(user);

        // 成功メッセージ → ログイン画面へ
        return "redirect:/login?registerSuccess";
    }
}