package com.example.demo.audit;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="audit")
public class Audit {
	
	@Id
	public String root_id;
	
	private String status_message;
	private String application_name;
	private String error_id;
	
	public Audit(String root_id, String status_message, String application_name, String error_id) {
		this.root_id = root_id;
		this.status_message = status_message;
		this.application_name = application_name;
		this.error_id = error_id;
	}
}
