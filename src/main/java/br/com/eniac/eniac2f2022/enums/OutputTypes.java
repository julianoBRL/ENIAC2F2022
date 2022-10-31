package br.com.eniac.eniac2f2022.enums;

public enum OutputTypes {
	CSV("csv"),
	JSON("json"),
	XML("xml"),
	
	csv("csv"),
	json("json"),
	xml("xml");

	String type;
	
	OutputTypes(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
	
}
