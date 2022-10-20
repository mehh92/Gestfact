package controleur;

public class TableauBord {
	private String nomc, prenomc, nomt, prenomt, matricule, description, dateinter ;

	public TableauBord(String nomc, String prenomc, String nomt, String prenomt, String matricule, String description,
			String dateinter) {
		 
		this.nomc = nomc;
		this.prenomc = prenomc;
		this.nomt = nomt;
		this.prenomt = prenomt;
		this.matricule = matricule;
		this.description = description;
		this.dateinter = dateinter;
	}

	public String getNomc() {
		return nomc;
	}

	public void setNomc(String nomc) {
		this.nomc = nomc;
	}

	public String getPrenomc() {
		return prenomc;
	}

	public void setPrenomc(String prenomc) {
		this.prenomc = prenomc;
	}

	public String getNomt() {
		return nomt;
	}

	public void setNomt(String nomt) {
		this.nomt = nomt;
	}

	public String getPrenomt() {
		return prenomt;
	}

	public void setPrenomt(String prenomt) {
		this.prenomt = prenomt;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateinter() {
		return dateinter;
	}

	public void setDateinter(String dateinter) {
		this.dateinter = dateinter;
	} 
	
	

}
