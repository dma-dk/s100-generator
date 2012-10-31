package dk.dma.s100;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class S100Header {

	Map<String, String> properties = new HashMap<>();

	public static S100Header fromPropertyFile(InputStream is, Charset charset) {
		return null;
	}

	public ProductSpecificationMetadata getPSM() {
		return new ProductSpecificationMetadata();
	}

	String get(String prefix, String key) {
		String str = properties.get(prefix + "." + key);
		return str;
	}

	public class ProductSpecificationMetadata {
		String g(String key, boolean isOptional) {
			return get("psm", key);
		}

		public String getTitle() {
			return g("title", false);
		}

		public String getVersion() {
			return g("version", false);
		}

		public String getDate() {
			return g("date", false);
		}
		
		public String getLanguage() {
			return g("language", false);
		}
		public String getClassification() {
			return g("language", true);
		}
	}
}
