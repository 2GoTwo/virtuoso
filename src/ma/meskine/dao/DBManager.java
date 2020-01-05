package ma.meskine.dao;

import java.util.ArrayList;

import ma.meskine.model.Company;

public interface DBManager {

	public Company findCompanyById(long id);
	public ArrayList<String> allCompaniesName();
	public ArrayList<Company> findAll();
}
