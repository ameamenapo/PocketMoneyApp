package com.example.demo.form.profile;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Table(name = "user_image_mst")
@Data
public class UserImageForm {
	//user_image_mstのカラム
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long image_id;
	private String user_id;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image_data;
	private String image_name;
	private String image_type;
	private MultipartFile file; // ← 追加

}
