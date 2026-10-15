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
public class PasswordResetController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ① リセット開始画面
    @GetMapping("/password/reset")
    public String resetPasswordPage() {
        return "auth/password_reset_username";
    }

    // ② ユーザー確認 → パスワード入力画面へ
    @PostMapping("/password/reset/confirm")
    public String confirmUser(@RequestParam String username, Model model) {
    	
        // 空チェック
        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("error", "ユーザー名を入力してください");
            return "auth/password_reset_username";
        }
    	
        User user = userRepository.findByUserName(username).stream().findFirst().orElse(null);

        if (user == null) {
            model.addAttribute("error", "ユーザーが見つかりません");
            return "auth/password_reset_username";
        }

        model.addAttribute("username", username);
        return "auth/password_reset_form";
    }

    // ③ 新しいパスワード更新
    @PostMapping("/password/reset/update")
    public String updatePassword(@RequestParam String username,
                                 @RequestParam String newPassword) {

        User user = userRepository.findByUserName(username).get(0);

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return "redirect:/login?resetSuccess";
    }
}