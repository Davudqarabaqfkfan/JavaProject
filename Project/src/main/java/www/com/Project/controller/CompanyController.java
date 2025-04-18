package www.com.Project.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import www.com.Project.entity.CompanyEntity;
import www.com.Project.service.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	@Autowired
	CompanyService companyService;
	@GetMapping("/get-all")
	public Page<CompanyEntity> getAllCompaniessPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		return companyService.getAllCompaniesPaginated(PageRequest.of(page, size));
	}
	@GetMapping("/{id}")
	public Optional<CompanyEntity> getOneCompanyy(@PathVariable Long id){
		return companyService.GetOneCompany(id);
	}
	@PostMapping("create")
	public ResponseEntity<String> createe(@Valid @RequestBody CompanyEntity companyEntity, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("validation error"+ result.getAllErrors());
		}
		return ResponseEntity.ok("Company created succesfully");
	}
	@DeleteMapping("/delete/{id}")
	public void deleete(@PathVariable Long id) {
		companyService.delete(id);
	}
	@PutMapping("update/{id}")
	public CompanyEntity updateeCompanyEntity(@PathVariable Long id, @RequestBody CompanyEntity companyEntity) {
		return companyService.update(id, companyEntity);
	}
	@GetMapping("/search")
	public List<CompanyEntity> searchh(@RequestParam String keyword){
		return companyService.search(keyword);
	}
}
