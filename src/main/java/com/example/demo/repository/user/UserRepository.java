package com.example.demo.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, String>  {
	// ユーザー名で完全一致検索
    List<User> findByUserName(String userId);

}