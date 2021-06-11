package application.entities.concretes;



import java.sql.Date;

import application.entities.abstracts.Entity;

public class Transaction implements Entity{
	private int id;
	private String user;
	private String islemAciklama;
	private Double islemTutar;
	private Date islemTarih;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(int id, String user, String islemAciklama, Double islemTutar, Date islemTarih) {
		super();
		this.id = id;
		this.user = user;
		this.islemAciklama = islemAciklama;
		this.islemTutar = islemTutar;
		this.islemTarih = islemTarih;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIslemAciklama() {
		return islemAciklama;
	}

	public void setIslemAciklama(String islemAciklama) {
		this.islemAciklama = islemAciklama;
	}

	public Double getIslemTutar() {
		return islemTutar;
	}

	public void setIslemTutar(Double islemTutar) {
		this.islemTutar = islemTutar;
	}

	public Date getIslemTarih() {
		return islemTarih;
	}

	public void setIslemTarih(Date islemTarih) {
		this.islemTarih = islemTarih;
	}
}
