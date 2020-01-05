package ma.meskine.model;

public class Company {

	private long companyId;
	private String companyName;
	private String companyCity;

	public Company() {
	}

	public Company(long companyId, String companyName, String companyCity) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyCity = companyCity;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

}
