package ru.job4j.sqlite;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class ConvertXSQT {
    public void convert(File source, File dest, File scheme) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(scheme));
        transformer.transform(new StreamSource(source), new StreamResult(dest));
        transformer.transform(new StreamSource(source), new StreamResult(System.out));
    }
}
