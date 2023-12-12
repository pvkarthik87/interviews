package com.karcompany.pragmaticplay;

public class MyHashMap {
    MyList[] itemsArray = new MyList[100];

    void put(String key, String value) {
        int hashCode = key.hashCode();
        int possibleIndex = hashCode % itemsArray.length;

        MyList entryList = itemsArray[possibleIndex];
        if (entryList == null) {
            entryList = new MyList();
            itemsArray[possibleIndex] = entryList;
        }
        entryList.insert(key, value);
    }

    String get(String key) {
        int hashCode = key.hashCode();
        int possibleIndex = hashCode % itemsArray.length;
        MyList entryList = itemsArray[possibleIndex];
        if (entryList == null) {
            return null;
        }
        return entryList.get(key);
    }

    void remove(String key) {
        int hashCode = key.hashCode();
        int possibleIndex = hashCode % itemsArray.length;
        MyList entryList = itemsArray[possibleIndex];
        if (entryList != null) {
            entryList.remove(key);
        }
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        System.out.println(myHashMap.get("key1"));

        myHashMap.put("key1", "value1");
        System.out.println(myHashMap.get("key1"));

        myHashMap.put("key2", "value2");
        System.out.println(myHashMap.get("key2"));

        myHashMap.put("key3", "value3");
        System.out.println(myHashMap.get("key3"));

        myHashMap.put("key2", "value4");
        System.out.println(myHashMap.get("key2"));

        myHashMap.remove("key3");
        System.out.println(myHashMap.get("key3"));

        myHashMap.remove("key1");
        System.out.println(myHashMap.get("key1"));

        System.out.println(myHashMap.get("key2"));
    }
}


