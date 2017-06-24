package model;

import java.io.Serializable;

public class Option implements Serializable {
	private String name = "empty";
	private int price = 0;

	Option() {
	}

	Option(String newName, int newPrice) {
		this.name = newName;
		this.price = newPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
