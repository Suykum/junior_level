package ru.job4j.sqlite;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class StoreXML {
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }
    public void save(ArrayList list) {
        Entries entries = new Entries();
        entries.setEntries(list);
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller m = context.createMarshaller();
            m.marshal(entries, new FileOutputStream(target));
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(entries, System.out);
            System.out.println("XML-file created");
        } catch (FileNotFoundException e) {
            System.out.println("File can not created: " + e);
        } catch (JAXBException e) {
            System.out.println("JAXB-context error " + e);
        }
    }

}
