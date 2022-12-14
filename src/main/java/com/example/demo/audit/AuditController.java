package com.example.demo.audit;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditController {
	
	@Autowired
	public AuditService service;
	
	@PostMapping("/createaudit")
	public Audit createAudit(@Validated @RequestBody Audit audit) {
		return service.create(audit);
	}
	
	@GetMapping("/audits")
	public List<Audit> getAllAudits() {
		return service.getAll();
	}
	
	@GetMapping("/audit/{id}")
	public Optional<Audit> getAuditById(@PathVariable String id){
		return service.getById(id);
	}
	
	@PostMapping("/updateaudit/{id}")
	public Audit updateAudit(@PathVariable String id, @Validated @RequestBody Audit audit) {
		return service.update(id, audit);
	}
	
	@DeleteMapping("/deleteaudit/{id}")
	public String deleteAudit(@PathVariable String id) {
		service.delete(id);
		return "Audit Deleted Successfullt";
	}
}
