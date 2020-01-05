package ma.meskine.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

import ma.meskine.model.Company;
import virtuoso.jena.driver.VirtModel;

@Component
public class DBManagerImp implements DBManager {

	@Override
	public Company findCompanyById(long id) {
		String url = "jdbc:virtuoso://localhost:1111";

		Model m = VirtModel.openDatabaseModel("http://localhost:8890/mydb#", url, "dba", "admin");
		String prolog = "PREFIX mdb: <http://localhost:8890/schemas/mydb/>\n";
		String queryString = prolog + "select ?compId ?compN ?compCity where { \n" + "?company mdb:company_id '" + id
				+ "'.\n" + "?company mdb:company_id ?compId.\n" + "?company mdb:company_name ?compN.\n"
				+ "?company mdb:company_city ?compCity\n" + "}";
		QueryExecution qexec = QueryExecutionFactory.create(queryString, m);
		ResultSet rs = qexec.execSelect();
		QuerySolution result = rs.nextSolution();
		Company c = new Company(id, result.get("compN").toString(), result.get("compCity").toString());
		return c;
	}

	@Override
	public ArrayList<String> allCompaniesName() {
		ArrayList<String> names = new ArrayList<>();
		String url = "jdbc:virtuoso://localhost:1111";
		Model m = VirtModel.openDatabaseModel("http://localhost:8890/mydb#", url, "dba", "admin");
		String prolog = "PREFIX mdb: <http://localhost:8890/schemas/mydb/>\n";
		String queryString = prolog + "select ?companyName  where {?s mdb:company_name ?companyName . }";
		QueryExecution qexec = QueryExecutionFactory.create(queryString, m);
		ResultSet rs = qexec.execSelect();
		while (rs.hasNext()) {
			QuerySolution result = rs.nextSolution();
			RDFNode companyName = result.get("companyName");
			names.add(companyName.toString());
		}
		m.close();
		return names;
	}

	@Override
	public ArrayList<Company> findAll() {
		ArrayList<Company> companies = new ArrayList<>();
		String url = "jdbc:virtuoso://localhost:1111";
		Model m = VirtModel.openDatabaseModel("http://localhost:8890/mydb#", url, "dba", "admin");
		String prolog = "PREFIX mdb: <http://localhost:8890/schemas/mydb/>\n";
		String queryString = prolog + "select ?compId ?compN ?compCity where {\n" + "?company mdb:company_id ?compId.\n"
				+ "?company mdb:company_name ?compN.\n" + "?company mdb:company_city ?compCity\n" + " }";
		QueryExecution qexec = QueryExecutionFactory.create(queryString, m);
		ResultSet rs = qexec.execSelect();
		while (rs.hasNext()) {
			QuerySolution result = rs.nextSolution();
			RDFNode companyId = result.get("compId");
			RDFNode companyName = result.get("compN");
			RDFNode companyCity = result.get("compCity");
			Company c = new Company(Long.valueOf(companyId.toString()), companyName.toString(), companyCity.toString());
			companies.add(c);
		}
		m.close();
		return companies;
	}

}
