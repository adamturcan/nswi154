import cz.cuni.mff.java.hw.hashtable.HashTable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class HashTableTest {

    @Test
    public void testSetAndGet() {
        cz.cuni.mff.java.hw.hashtable.HashTable table = new cz.cuni.mff.java.hw.hashtable.HashTable();
        table.set("slovo", "ahoj");
        table.set("slovo2", "svet");

        assertEquals("ahoj", table.get("slovo"));
        assertEquals("svet", table.get("slovo2"));
        assertNull(table.get("slovo3"));
    }

    @Test
    public void testUpdate() {
        cz.cuni.mff.java.hw.hashtable.HashTable table = new cz.cuni.mff.java.hw.hashtable.HashTable();
        table.set("slovo", "ahoj");
        table.set("slovo", "cau");

        assertEquals("cau", table.get("slovo"));
    }

    @Test
    public void testResize() {
        cz.cuni.mff.java.hw.hashtable.HashTable table = new cz.cuni.mff.java.hw.hashtable.HashTable();
        for (int i = 0; i < 100; i++) {
            table.set("slovo" + i, "haha" + i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals("haha" + i, table.get("slovo" + i));
        }
    }

    @Test
    public void testForEach() {
        cz.cuni.mff.java.hw.hashtable.HashTable table = new cz.cuni.mff.java.hw.hashtable.HashTable();
        table.set("slovo1", "ahoj");
        table.set("slovo2", "svet");
        table.set("slovo3", "cau");

        List<Object> collectedValues = new ArrayList<>();
        table.forEachValue(collectedValues::add);

        assertTrue(collectedValues.contains("ahoj"));
        assertTrue(collectedValues.contains("svet"));
        assertTrue(collectedValues.contains("cau"));
        assertEquals(3, collectedValues.size());
    }

    @Test
    public void testIteration() {
        cz.cuni.mff.java.hw.hashtable.HashTable table = new HashTable();
        table.set("slovo1", "ahoj");
        table.set("slovo2", "svet");

        List<String> keys = new ArrayList<>();
        for (String key : table) {
            keys.add(key);
        }

        assertTrue(keys.contains("slovo1"));
        assertTrue(keys.contains("slovo2"));
        assertEquals(2, keys.size());
    }
}
