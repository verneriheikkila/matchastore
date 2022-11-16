package fi.swd4tn020.teekauppa.domain;

//import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Matcha {
	
//	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String grade;
	private String description;
	private double price;
	private double size;

	@ManyToOne
	@JsonIgnoreProperties("matchas")
	@JoinColumn(name = "producerid")
	private Producer producer;

	public Matcha() {
	}

	public Matcha(String name, String grade, String description,
			double price, double size, Producer producer) {
		super();
		this.name = name;
		this.grade = grade;
		this.description = description;
		this.price = price;
		this.size = size;
		this.producer = producer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	@Override
	public String toString() {
		if (this.producer != null)
			return "Matcha [id=" + id + ", name=" + name + ", description=" + description +  ", price=" + price + ", producer=" + this.getProducer() + ", grade="
					+ grade + "]";
		else
			return "Matcha [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", producer=" + ", grade=" + "]";
	}

}
