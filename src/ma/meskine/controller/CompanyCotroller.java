package ma.meskine.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ma.meskine.dao.DBManager;
import ma.meskine.model.Company;

@RestController
public class CompanyCotroller {
	
	@Autowired
	private DBManager dbm;
	
	@GetMapping("/companies/{id}")
	public Company findCompany(@PathVariable long id) {
		Company c = dbm.findCompanyById(id);
		return c;
	}
	
	@GetMapping("/companies/")
	public ArrayList<Company> findCompany() {
		return dbm.findAll();
	}
}
