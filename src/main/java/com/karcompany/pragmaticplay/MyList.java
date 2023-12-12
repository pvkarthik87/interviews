package com.karcompany.pragmaticplay;

public class MyList {
    ListNode head;

    void insert(String key, String value) {
        if (head == null) {
            head = new ListNode(key, value, null);
        } else {
            ListNode tmp = head;
            while (tmp != null && !tmp.key.equals(key)) {
                tmp = tmp.next;
            }
            if (tmp != null) {
                tmp.value = value;
            } else {
                ListNode node = new ListNode(key, value, null);
                node.next = head;
                head = node;
            }
        }
    }


    public String get(String key) {
        ListNode tmp = head;
        while (tmp != null && !tmp.key.equals(key)) {
            tmp = tmp.next;
        }
        if (tmp != null)
            return tmp.value;
        else
            return null;
    }

    public void remove(String key) {
        ListNode tmp = head;
        ListNode prev = null;
        while (tmp != null && !tmp.key.equals(key)) {
            prev = tmp;
            tmp = tmp.next;
        }
        if (tmp != null) {
            if (prev == null) {
                head = head.next;
            } else {
                prev.next = tmp.next;
            }
        }
    }
}

class ListNode {
    String key;
    String value;
    ListNode next;

    public ListNode(String key, String value, ListNode next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}


