package dk.dma.s100.header;

public class HeaderBuilder {

	public static HeaderBuilder create() {
		return new HeaderBuilder();
	}
	
	//for better formatting, for example, create: 10.10.2012
	//public HeaderBuilder add(String itemName, Multiplicity multiplicity, Class<?> type, String printf) {
	
	public HeaderBuilder add(String itemName, Multiplicity multiplicity, Class<?> type) {
		
		return this;
	}
	
	public Header build() {
		return new Header();
	}
}
