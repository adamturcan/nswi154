package cz.cuni.mff.java.hw.hashtable;



import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;


/**
 * Jednoducha hash tabulka implementovana s klucami typu String a hodnotami typu Object
 */
public class HashTable implements  Iterable<String>{



    private static final int DEFAULT_CAPACITY = 16;
    private static final double UPDATE_FACTOR = 0.75;






    private LinkedList<Entry>[] table;

    private int size;




    /**
     * Vytvor prazdnu hash tabulku
     */
    @SuppressWarnings("unchecked")
    public HashTable() {

        table = new LinkedList[DEFAULT_CAPACITY];

        for (int i = 0; i < table.length; i++) {

            table[i] = new LinkedList<>();

        }
        System.out.println("aaaaaa");
        size = 0;

    }

    /**
     * Vypocita index pre kluc
     *
     * @param key kluc pre ktory pocitame index
     * @return index kluca v tabulke
     */

    private int index(String key) {

        return Math.abs(key.hashCode()) % table.length;

    }



    /**
     * Vrati hodnotu priradenu klucu v tabulke
     *
     * @param key kluc, ktoreho hodnotu hladame
     * @return hodnota priradena ku klucu key v pripade ze existuje, inak null
     */

    public Object get(String key) {

        int idx = index(key);

        for (Entry entry : table[idx]) {

            if (entry.key.equals(key)) {

                return entry.value;

            }

        }

        return null;

    }



    /**
     * Priradenie hodnoty ku klucu do hashovacej tabulky
     *
     * @param key  kluc ktory vkladame do tabulky
     * @param value hodnota ktora bude priradena klucu v tabulke
     */

    public void set(String key, Object value) {

        int idx = index(key);

        for (Entry entry : table[idx]) {

            if (entry.key.equals(key)) {

                entry.value = value;

                return;

            }

        }



        table[idx].add(new Entry(key, value));

        size++;



        // Resize if necessary

        if (size/(double)table.length > UPDATE_FACTOR) {

            resize();

        }

    }

     /**
     * Rozsirenie tabulky v pripade potreby
     */

    @SuppressWarnings("unchecked")

    private void resize() {

        LinkedList<Entry>[] oldTable = table;

        table = new LinkedList[oldTable.length * 2];

        for (int i = 0; i < table.length; i++) {

            table[i] = new LinkedList<>();

        }



        for (LinkedList<Entry> bucket : oldTable) {

            for (Entry entry : bucket) {

                int idx = index(entry.key);

                table[idx].add(entry);

            }

        }

    }


    /**
     *
     * reprezentuje key-value par ulozeny v tabulke
     *
     */
    private static class Entry {

        String key;

        Object value;



        Entry(String key, Object value) {

            this.key = key;

            this.value = value;

        }

    }


    /**
     * Vrati iterator cez kluce tabulky
     *
     * @return iterator cez kluce
     */
    @Override
    public Iterator<String> iterator() {

        return new Iterator<>() {

            private int bucketIndex = 0;

            private Iterator<Entry> bucketIterator = table[0].iterator();

            /**
             * Metoda posuva iterator na dalsi "bucket", ktorý obsahuje položky
             */
            private void advanceBucket() {
                while (bucketIndex < table.length && !bucketIterator.hasNext()) {
                    bucketIndex++;
                    if (bucketIndex < table.length) {
                        bucketIterator = table[bucketIndex].iterator();
                    }

                }

            }


            @Override

            public boolean hasNext() {

                advanceBucket();

                return bucketIndex < table.length && bucketIterator.hasNext();

            }



            @Override

            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Ziadny dalsi prvok nieje v tabulke");
                }
                return bucketIterator.next().key;

            }

        };



    }

    /**
     * Aplikuje operaciu pre kazdu hodnotu v tabulke
     *
     * @param operation functional interface, ktore sa aplikuje na kazdu polozku v tabulke
     */


    public void forEachValue(Operation operation) {
        for (LinkedList<Entry> bucket : table) {
            for (Entry entry : bucket) {
                operation.apply(entry.value);
            }
        }
    }

    /**
     * Functional interface na aplikovanie operacii na hodnoty v tabulke
     */
    @FunctionalInterface
    public interface Operation {
        void apply(Object value);
    }



}
