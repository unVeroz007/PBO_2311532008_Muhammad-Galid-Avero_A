package model;

public class Customer {
	
	private String id;
	private String name;
	private String addres;
	private String handphone;
	
	public Customer(String id, String name, String addres, String handphone) {
		this.id = id;
		this.name = name;
		this.addres = addres;
		this.handphone = handphone;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddres() {
		return addres;
	}

	public String getHandphone() {
		return handphone;
	}

	
}
