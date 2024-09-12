package com.ad.jobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@RequestBody Company updatedCompany, @PathVariable Long id) {
        boolean updated = companyService.updateCompany(updatedCompany, id);
        if (updated) {
            return new ResponseEntity<>("Company updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompanyById(id);
        if (deleted) {
            return new ResponseEntity<>("Company deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }
}
