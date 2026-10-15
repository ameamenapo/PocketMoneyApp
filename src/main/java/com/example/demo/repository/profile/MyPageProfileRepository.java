package com.example.demo.repository.profile;

import com.example.demo.entity.Profile;

public interface MyPageProfileRepository {

	void add(String username);
	
	void update(Profile p);

}
