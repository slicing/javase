package Slow.slicing.dms;


import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import jdk.internal.util.xml.XMLStreamException;
import jdk.internal.util.xml.impl.XMLWriter;

import javax.swing.text.Document;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class NetService {
	private String type;
	private long length;
	public static void send(Document doc, OutputStream out) throws IOException, XMLStreamException {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		OutputFormat fmt = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(buf,fmt);
		writer.write(doc);
		byte[] bytes = buf.toByteArray();
		String str = "XML," + bytes.length;
		byte[] t1 = str.getBytes("utf-8");
		t1 = Arrays.copyOf(t1,50);
		out.write(t1);
		out.write(bytes);
		out.flush();


	}

	public static Document receive(InputStream netIn) {
		return null;
	}
}
