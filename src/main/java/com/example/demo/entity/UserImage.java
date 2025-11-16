package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_image_mst")
public class UserImage {
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

}
