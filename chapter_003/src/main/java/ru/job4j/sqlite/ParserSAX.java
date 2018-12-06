package ru.job4j.sqlite;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ParserSAX  {
    private String sourceXML;
   private ArrayList<Integer> valueList = new ArrayList<>();

    public ParserSAX(String path) {
        sourceXML = path;
    }

    private void parser() {
        try {
            File inputFile = new File(sourceXML);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            EntryHandler handler = new EntryHandler();
            saxParser.parse(inputFile, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSum() {
        parser();
        int sum = 0;
        for (int i : valueList) {
            sum += i;
        }
        return sum;
    }

    private class EntryHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("entry")) {
                valueList.add(Integer.parseInt(attributes.getValue("href")));
            }
        }
    }


}
