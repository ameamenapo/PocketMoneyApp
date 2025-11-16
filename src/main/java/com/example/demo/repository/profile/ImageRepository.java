package com.example.demo.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserImage;

//@Repository
//public interface ImageRepository {
//	void add(UserProfile up);
//}
public interface ImageRepository extends JpaRepository<UserImage, Long> {

}
