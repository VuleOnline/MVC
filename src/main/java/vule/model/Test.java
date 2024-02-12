package vule.model;

import java.util.List;

public class Test {
	private int testid;
    private String tehnologija;
    private String pitanje;
    private List<String> odgovori;
    private String tacan;
    
    
	
	
	public Test(String tehnologija) {
		super();
		this.tehnologija = tehnologija;
	}
	
	public Test(String tehnologija, String pitanje, List<String> odgovori, String tacan) {/*KORISTI INSERT*/
		super();
		this.tehnologija = tehnologija;
		this.pitanje = pitanje;
		this.odgovori = odgovori;
		this.tacan = tacan;
	}
	public Test(int testid, String tehnologija, String pitanje, List<String> odgovori, String tacan) {
		super();
		this.testid = testid;
		this.tehnologija = tehnologija;
		this.pitanje = pitanje;
		this.odgovori = odgovori;
		this.tacan = tacan;
	}

	
	
	
	public int getTestid() {
		return testid;
	}
	public void setTestid(int testid) {
		this.testid = testid;
	}
	public String getTehnologija() {
		return tehnologija;
	}
	public void setTehnologija(String tehnologija) {
		this.tehnologija = tehnologija;
	}

	public String getPitanje() {
		return pitanje;
	}
	public void setPitanje(String pitanje) {
		this.pitanje = pitanje;
	}
	public List<String> getOdgovori() {
		return odgovori;
	}
	public void setOdgovori(List<String> odgovori) {
		this.odgovori = odgovori;
	}
	public String getTacan() {
		return tacan;
	}
	public void setTacan(String tacan) {
		this.tacan = tacan;
	}
	
	
	
	

}
