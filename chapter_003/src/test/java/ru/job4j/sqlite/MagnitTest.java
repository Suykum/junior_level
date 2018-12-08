package ru.job4j.sqlite;

import org.junit.Test;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.util.ArrayList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MagnitTest {

    //1. Генерация данных в SQLLite
    Config config = new Config();
    ArrayList<Entry> list;
    StoreSQL storeSQL = new StoreSQL(config);

    @Test
    public void whenSpeedOfExecutingTested() {
        storeSQL.dataInsertingTime(1000000);
    }

    @Test
    public void whenStoreSQLTested() {
        storeSQL.createNewDatabase();
        storeSQL.generate(4);
        list = storeSQL.selectAll();
        storeSQL.close();
        assertThat(list.size(), is(4));
    }

    //3. Генерация XML из данных базы. Описывается класс StoreXML
    @Test
    public void whenStoreXMLTested() {
        StoreXML storeXML = new StoreXML(new File("C:/projects/entries"));
        list = storeSQL.selectAll();
        storeXML.save(list);
    }

    //4. Преобразовать полученный файл из пункта 3 в файл другого XML формата через XSTL.
    ConvertXSQT convertXSQT = new ConvertXSQT();
    @Test
    public void whenXMLtransformedViaXSL() throws TransformerException {
        convertXSQT.convert(new File("C:/projects/entries"), new File("C:/projects/entriesXSL"), new File("C:/projects/scheme.xsl"));
    }

    //5. Приложение парсит выходной файл из пункта 4 и выводит арифметическую сумму значений всех атрибутов
    //field в консоль.
    ParserSAX parserSum = new ParserSAX("C:/projects/entriesXSL");
    @Test
    public void whenParserTested() throws SAXException {
       int sum = parserSum.getSum();
       assertThat(sum, is(10));
    }
}