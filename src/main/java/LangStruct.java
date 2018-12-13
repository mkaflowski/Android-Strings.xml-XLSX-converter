import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LangStruct {

    private String lang;
    private File stringFile;
    private Map<String, String> stringMap = new HashMap<>();

    private void extractStrings() throws Exception {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(stringFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("string");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String name = eElement.getAttribute("name");
                String value = eElement.getChildNodes().item(0).getTextContent();
                stringMap.put(name, value);
            }
        }

    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public File getStringFile() {
        return stringFile;
    }

    public void setStringFile(File stringFile) {
        this.stringFile = stringFile;
        try {
            extractStrings();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return getLang() + "\t" + getStringFile().getPath();
    }
}
