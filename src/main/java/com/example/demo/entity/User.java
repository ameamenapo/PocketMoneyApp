package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //2025/6/7に追加したアノテーション
@Table(name = "user_mst")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	//user_mstのカラム

	@Id
    @Column(name = "user_id")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    private Integer roleId;


}
