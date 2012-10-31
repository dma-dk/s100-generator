package dk.dma.s100;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;

public class S100Generator {

	public static void main(String[] args) {
		JavaDocBuilder builder = new JavaDocBuilder();
		builder.addSourceTree(new File(
				"C://workspace//eNav-api//enav-model//src//main//java"));

		Element chapter = new Element("chapter");
		// chapter.setNamespaceURI("http://docbook.org/ns/docbook");
		// chapter.addAttribute(new Attribute("xml:id", "tables"));
		// chapter.addAttribute(new Attribute("xmlns",
		// "http://docbook.org/ns/docbook"));
		// chapter.setNamespaceURI("tables");
		chapter.addAttribute(new Attribute("version", "5.0"));

		Element title = new Element("title");
		title.setNamespaceURI("http://docbook.org/ns/docbook");
		title.appendChild("Classes");
		chapter.appendChild(title);

		Element para = new Element("para");
		para.setNamespaceURI("http://docbook.org/ns/docbook");
		chapter.appendChild(para);
		Document doc = new Document(chapter);

		for (JavaClass c : builder.getClasses()) {
			addClass(c, para);
		}
		setNameSpace(chapter, "http://docbook.org/ns/docbook");
		try {
			File f = new File("./target/generated");
			f.mkdirs();
			FileOutputStream fos = new FileOutputStream(new File(f,
					"tables.xml"));

			Serializer serializer = new Serializer(fos, "ISO-8859-1");
			serializer.setIndent(2);
			serializer.setMaxLength(120);
			serializer.write(doc);
		} catch (IOException ex) {
			System.err.println(ex);
		}

	}

	private static void setNameSpace(Element root, String namespace) {
		root.setNamespaceURI(namespace);
		for (int i = 0; i < root.getChildElements().size(); i++) {
			setNameSpace(root.getChildElements().get(i), namespace);
		}
	}

	public static void addClass(JavaClass c, Element e) {
		Element elem = new Element("table");
		elem.addAttribute(new Attribute("frame", "all"));

		Element tgroup = new Element("tgroup");
		tgroup.addAttribute(new Attribute("cols", "2"));
		tgroup.addAttribute(new Attribute("align", "left"));
		tgroup.addAttribute(new Attribute("colsep", "1"));
		tgroup.addAttribute(new Attribute("rowsep", "1"));
		Element title = new Element("title");
		title.appendChild(c.getFullyQualifiedName());
		elem.appendChild(title);

		elem.appendChild(tgroup);

		Element tbody = new Element("tbody");
		tgroup.appendChild(tbody);
		boolean hasFields = false;
		for (JavaField f : c.getFields()) {
			if (!f.isStatic()) {
				addField(f, tbody);
				hasFields = true;
			}
		}
		if (hasFields) {
			e.appendChild(elem);
		}
		// <tgroup cols='5' align='left' colsep='1' rowsep='1'>
	}

	public static void addField(JavaField f, Element e) {
		Element row = new Element("row");
		e.appendChild(row);

		Element name = new Element("entry");
		name.appendChild(f.getName());

		Element type = new Element("entry");
		type.appendChild(f.getType().toString());

		row.appendChild(name);
		row.appendChild(type);

	}
}
