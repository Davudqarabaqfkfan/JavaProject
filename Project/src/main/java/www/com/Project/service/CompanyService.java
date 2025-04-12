package www.com.Project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import www.com.Project.entity.CompanyEntity;
import www.com.Project.repository.CompanyRepository;

@Service
public class CompanyService {
@Autowired
CompanyRepository companyRepository;
public Page<CompanyEntity> getAllCompaniesPaginated(Pageable pageable){
	return companyRepository.findAll(pageable);
}
public Optional<CompanyEntity> GetOneCompany(Long id){
return companyRepository.findById(id);
}
public void create(CompanyEntity companyEntity) {
	companyRepository.save(companyEntity);
}
public void delete(Long id) {
	companyRepository.deleteById(id);
}
public CompanyEntity update(Long id, CompanyEntity companyEntity) {
	Optional<CompanyEntity> excistingOptional = companyRepository.findById(id);
	if(excistingOptional.isPresent()) {
		CompanyEntity updatedCompanyEntity = excistingOptional.get();
		updatedCompanyEntity.setName(companyEntity.getName());
		updatedCompanyEntity.setDirection(companyEntity.getDirection());
		updatedCompanyEntity.setCreator(companyEntity.getCreator());
		return companyRepository.save(updatedCompanyEntity);
	}
	return null;
}
public List<CompanyEntity> search(String keyword){
	return companyRepository.findByNameContainingIgnoreCase(keyword);
}
}
