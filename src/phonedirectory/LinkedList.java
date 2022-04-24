package phonedirectory;

import java.util.NoSuchElementException;

class Node {

    String name;
    Node next;

    public Node(String contact_name) {
        this.name = contact_name;
    }
}

public class LinkedList {
    Node first;
    Node last;
    private int size;

    public void addFirst(String contact_name) {
        var node = new Node(contact_name);
        if (isEmpty()) {
            first = last = node;
        } else {
            node.next = first;
            first = node;
        }
        size++;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addLast(String contact_name) {
        var node = new Node(contact_name);
        if (isEmpty()) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (first == last) {
            first = last = null;
        } else {
            var second = first.next;
            first.next = null;
            first = second;
        }
        size--;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (first == last) {
            first = last = null;
        } else {
            var node = first;
            while (true) {
                if (node.next == last) {
                    last = node;
                    last.next = null;
                    break;
                }
                node = node.next;
            }
        }
        size--;
    }
    
    public void remove(String str) {
        var temp = first;
        if (temp != null && temp.name.equals(str)) {
            first = temp.next;
        } else {
            while (temp.next != null) {
                if (temp.next.name.equals(str)) {
                    Node next = temp.next.next;
                    temp.next = next;
                    break;
                }
                temp = temp.next;
            }
        }
        setSize(size()-1);
    }
    
    public void update(String oldStr, String newStr) {
        var temp = first;
        while (temp != null) {
            if (temp.name.equals(oldStr)) {
                temp.name = newStr;
                break;
            }
            temp = temp.next;
        }
    }

    public int indexOf(String contact_name) {
        var node = first;
        int index = 0;
        while (node != null) {
            if (node.name == contact_name) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    public boolean contains(String contact_name) {
        return indexOf(contact_name) != -1;
    }

    public int size() {
        return size;
    }

    public String[] toArray() {
        String[] arr = new String[size];
        var index = 0;
        var node = first;
        while (node != null) {
            arr[index++] = node.name;
            node = node.next;
        }
        return arr;
    }

    private boolean isEmpty() {
        return first == null;
    }
    
    public void sortAsc(){
        for(Node i = first; i.next != null; i = i.next)
        for(Node j = i.next; j != null; j = j.next){
            if(i.name.compareTo(j.name) > 0){
                String temp = i.name;
                i.name = j.name;
                j.name = temp;
            }
        }
    }
    
    public void sortDesc(){
        for(Node i = first; i.next != null; i = i.next)
        for(Node j = i.next; j != null; j = j.next){
            if(i.name.compareTo(j.name) < 0){
                String temp = i.name;
                i.name = j.name;
                j.name = temp;
            }
        }
    }
    

}
