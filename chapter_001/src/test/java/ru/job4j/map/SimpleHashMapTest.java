package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    SimpleHashMap<String, String> myHashMap = new SimpleHashMap<>();

    @Test
    public void whenInsertEntry() {
        myHashMap.insert("300", "0");
        myHashMap.insert("301", "1");
        myHashMap.insert("305", "5");
        assertThat(myHashMap.get("305"), is("5"));
    }

    @Test
    public void whenDeleteEntry() {
        myHashMap.insert("300", "0");
        myHashMap.insert("301", "1");
        myHashMap.insert("305", "5");
        myHashMap.displayMap();
        myHashMap.delete("301");
        System.out.println("\nAfter deleting");
        myHashMap.displayMap();
    }

    @Test
    public void whenNextExist() {
        myHashMap.insert("300", "0");
        myHashMap.insert("301", "1");
        myHashMap.insert("305", "5");
        Iterator<SimpleEntry<String, String>> iter = myHashMap.iterator();
        iter.next().getKey();
        iter.next().getKey();
        assertThat(iter.hasNext(), is(true));
    }
    @Test
    public void whenResizeTable() {
        myHashMap.insert("300", "0");
        myHashMap.insert("301", "1");
        myHashMap.insert("305", "5");
        myHashMap.insert("500", "0");
        myHashMap.insert("501", "1");
        myHashMap.insert("503", "three");
        myHashMap.insert("701", "one");
        myHashMap.insert("702", "two");
        myHashMap.insert("703", "three");
        myHashMap.insert("901", "one");
        myHashMap.insert("902", "two");
        myHashMap.insert("903", "three");
        System.out.println("Number of elements: " + myHashMap.getNumElem());
        System.out.println("Size before: " + myHashMap.tableSize());
        System.out.println("Before resizing index of key 501: " + myHashMap.getIndex("500"));
        myHashMap.insert("801", "one");
        myHashMap.insert("802", "two");
        myHashMap.insert("803", "three");
        myHashMap.insert("804", "three");
        myHashMap.insert("805", "three");
        myHashMap.insert("806", "three");
        myHashMap.insert("1", "three");
        myHashMap.insert("2", "three");
        System.out.println("Number of elements: " + myHashMap.getNumElem());
        System.out.println("Size after: " + myHashMap.tableSize());
        System.out.println("After resizing index of key 501: " + myHashMap.getIndex("500"));

    }

}