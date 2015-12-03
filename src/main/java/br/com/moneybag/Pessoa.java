package br.com.moneybag;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@Entity
@Table(name="pessoas")
public class Pessoa extends Persistable {

	private static final long serialVersionUID = 1L;

	@Column(name="name")
	private String name;
	
	@Column(name="age")
	private Integer age;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	private Date birthday;
	
	public Pessoa() {
		this(null, null, null, null);
	}
	
	public Pessoa(Integer id){
		this(id, null, null, null);
	}
	
	public Pessoa(Integer id, String name, Integer age, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
