package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.user.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    
	@Autowired
	private PasswordEncoder encoder;

    // 依存性の注入（DI）を行う（コンストラクタインジェクション）
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 新規ユーザーの登録メソッド
    public void createUser(String userName, String password, int roleId) {
        // ユーザー名の未入力チェック（空欄はNG）
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("ユーザー名を入力してください。");
        }

        // ユーザー名の重複チェック（完全一致はNG）
        if (!userRepository.findByUserName(userName).isEmpty()) {
            throw new IllegalArgumentException("そのユーザー名は既に使用されています。");
        }

        // ユーザー登録用のエンティティを作成
        User user = new User();
        user.setUserName(userName);
        //  ハッシュ化されたパスワードをDBに保存
        user.setPassword(encoder.encode(password));
        user.setRoleId(roleId);

        // ユーザーの登録
        userRepository.save(user);
    }

    // ユーザーの一括取得メソッド
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}