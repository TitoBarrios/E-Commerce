package model;

public enum Type {
	USER(0), SELLER(1);

	int value;

	Type(int vslue) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
