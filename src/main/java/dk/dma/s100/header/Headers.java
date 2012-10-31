package dk.dma.s100.header;

import java.net.URL;
import java.util.Date;

import dk.dma.s100.md.MdClassificationCode;

public class Headers {

	public static void main(String[] args) {
		HeaderBuilder psm = HeaderBuilder.create(/* info about property file prefix */);
		psm.add("title", Multiplicity.ONE, String.class);
		psm.add("version", Multiplicity.ONE, String.class);
		psm.add("date", Multiplicity.ONE, Date.class);
		psm.add("version", Multiplicity.ONE, String.class);
		psm.add("classification", Multiplicity.ZERO_OR_ONE, MdClassificationCode.class);
		psm.add("contact", Multiplicity.ONE, null);
		psm.add("url", Multiplicity.ZERO_OR_ONE, URL.class);
		psm.add("identifier", Multiplicity.ONE, String.class);
		psm.add("maintenance", Multiplicity.ONE, String.class);
		//create header.
		//
	}
}
