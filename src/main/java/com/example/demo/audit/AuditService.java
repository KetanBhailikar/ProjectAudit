package com.example.demo.audit;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
	
	@Autowired
	public AuditRepository auditRepo;
	
	public Audit create(Audit audit) {
		return auditRepo.save(audit);
	}
	
	public List<Audit> getAll() {
		return auditRepo.findAll();
	}
	
	public Optional<Audit> getById(String id) {
		return auditRepo.findById(id);
	}
	
	public Audit update(String id, Audit audit) {
		audit.setRoot_id(id);
		return auditRepo.save(audit);
	}
	
	public String delete(String id) {
		auditRepo.deleteById(id);
		return "Deleted Successfully";
	}
}
