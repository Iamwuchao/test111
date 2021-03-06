package src.globalinfo;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigLoad {
	public static Map<String,String> loadProtocolServerFromXml(String fileName) throws DocumentException{
		SAXReader reader = new SAXReader();
		File file = new File(fileName);
		Document doc = reader.read(file);
		Element root = doc.getRootElement();
		List<Element> childElements = root.elements();
		Map<String,String> map = new HashMap<>();
		childElements.forEach((server)->{
			String serverName = server.attributeValue("name");
			serverName = serverName.replaceAll("\\s*", "");
			String key = server.elementText("value");
			key = key.replaceAll("\\s*", "");
			map.put(key,serverName);
		});
		return map;
	}
	
	public static Map<String,String> loadAppServerFromXml(String fileName) throws DocumentException{
		SAXReader reader = new SAXReader();
		File file = new File(fileName);
		Document doc = reader.read(file);
		Element root = doc.getRootElement();
		List<Element> childElements = root.elements();
		Map<String,String> map = new HashMap<>();
		childElements.forEach((app)->{
			String appName =  app.attributeValue("name");
			if(appName == null || appName.isEmpty()){
				System.err.println("appName is null");
			}
			appName = appName.replaceAll("\\s*", "");
			
			List<Element> serverList = app.elements();
			for(Element  server : serverList){
				System.out.println("######  "+server.asXML());
				String serverName = server.attributeValue("name");;
				serverName = serverName.replaceAll("\\s*", "");
				map.put(serverName, appName);
			}
		});
		return map;
	}
}
