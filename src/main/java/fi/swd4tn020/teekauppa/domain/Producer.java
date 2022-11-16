package fi.swd4tn020.teekauppa.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Producer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long producerid;
	private String name;
	private String area;
	private String about;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
	private List<Matcha> matchas;
	
	public Producer() {}
	


	public Producer(String name, String area, String about) {
		super();
		this.name = name;
		this.area = area;
		this.about = about;
	}



	public long getProducerid() {
		return producerid;
	}

	public void setProducerid(long producerid) {
		this.producerid = producerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	@Override
	public String toString() {
		return "Producer [producerid=" + producerid + ", name=" + name + ", area=" + area + ", about=" + about + "]";
	}


}
