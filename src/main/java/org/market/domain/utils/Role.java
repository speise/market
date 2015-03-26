package org.market.domain.utils;

public enum Role {
	SUPER_ADMIN("SUPER_ADMIN"),
	ADMIN("ADMIN"),
	USER("USER");
	
	private final String label;

	private Role(String label) {
		this.label = label;
	}
	
	public String toString(){
		return this.label;
	}
	
}
