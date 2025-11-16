package com.example.demo.form.user;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Table(name = "user_mst")
@Data
public class UserRegisterForm {
    // ユーザー名
	
	@Column(name = "user_id")
    @NotBlank(message = "ユーザー名を入力してください。")
//    private String userId;
    private String userName;

    // パスワード
    @NotBlank(message = "パスワードを入力してください。")
    @Size(min = 8, message = "パスワードは少なくとも8文字は必要です。")
    @Column(name = "password")
    private String password;

    // ロールID
    @NotNull(message = "ロールを正しく取得できませんでした。")
    @Column(name = "role_id")
    private Integer roleId;
}
