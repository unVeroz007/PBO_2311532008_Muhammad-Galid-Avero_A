package model;

public class CustomerBuilder {
	private String id;
	private String name;
	private String addres;
	private String handphone;
	

	public CustomerBuilder setId(String id) {
		this.id = id;
		return this;
	}

	public CustomerBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public CustomerBuilder setAddres(String addres) {
		this.addres = addres;
		return this;
	}

	public CustomerBuilder setHandphone(String handphone) {
		this.handphone = handphone;
		return this;
	}
	
	public Customer build() {
		return new Customer(id, name, addres, handphone);
	}
}
