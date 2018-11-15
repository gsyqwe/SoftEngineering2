package enums;

public enum MemberType {
	SUPPLIER("Supplier"), RETAILER("Retailer");

	private String name;

	private MemberType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public static MemberType getEnumByValue(String name) {
		for (MemberType a : MemberType.values()) {
			if (a.name.equals(name)) {
				return a;
			}
		}
		return null;
	}
}
