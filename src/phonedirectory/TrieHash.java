package phonedirectory;

import java.util.HashMap;

public class TrieHash {

    private class Node {

        private HashMap<Character, Node> map;
        private boolean isEnd;
        private int size;

        public Node() {
            this.map = new HashMap<>();
            this.isEnd = false;
            this.size = 0;
        }

        private static void delete(Node node) {
            node.map = new HashMap<Character, Node>();
            node.isEnd = false;
            node.size = 0;
        }
    }

    private Node Contact_Map;
    public static LinkedList results;

    public TrieHash() {
        this.Contact_Map = new Node();
    }

    public void addName(String name) {
        name = name.toLowerCase();
        Node node = Contact_Map;
        int index = 0;
        while (index < name.length()) {
            node.size++;
            if (node.map.containsKey(name.charAt(index))) {
                node = node.map.get(name.charAt(index));
            } else {
                node.map.put(name.charAt(index), new Node());
                node = node.map.get(name.charAt(index));
            }
            index++;
        }
        node.size++;
        node.isEnd = true;

    }

    public void addNumber(long number) {
        Node node = Contact_Map;
        String number_str = number + "";
        int index = 0;
        while (index < number_str.length()) {
            node.size++;
            if (node.map.containsKey(number_str.charAt(index))) {
                node = node.map.get(number_str.charAt(index));
            } else {
                node.map.put(number_str.charAt(index), new Node());
                node = node.map.get(number_str.charAt(index));
            }
            index++;
        }
        node.isEnd = true;
    }

    public void deleteName(String name) {
        name = name.toLowerCase();
        Node node = Contact_Map;
        int index = 0;
        while (index < name.length()) {
            node.size--;
            if (node.size == 0) {
                Node.delete(node);
                return;
            } else {
                node = node.map.get(name.charAt(index));
            }
            index++;
        }
        node.isEnd = false;
        node.size--;
    }

    public void deleteNumber(long number) {
        Node node = Contact_Map;
        String number_str = number + "";
        int index = 0;
        while (index < number_str.length()) {
            node.size--;
            if (node.size == 0) {
                Node.delete(node);
                return;
            } else {
                node = node.map.get(number_str.charAt(index));
            }
            index++;
        }
        node.isEnd = false;
        node.size--;
    }

    public void editName(String from_name, String to_name) {
        deleteName(from_name);
        addName(to_name);
    }

    public void editNumber(long from_number, long to_number) {
        deleteNumber(from_number);
        addNumber(to_number);
    }

    public boolean search(String str) {
        results = new LinkedList();
        str = str.toLowerCase();
        Node currentNode = Contact_Map;
        int index = 0;
        while (index < str.length()) {
            if (currentNode.map.containsKey(str.charAt(index))) {
                currentNode = currentNode.map.get(str.charAt(index));
            } else {
                return false;
            }
            index++;
        }
        traverse(currentNode, str);
        return true;
    }

    private static void traverse(Node currentNode, String result) {
        if (currentNode.isEnd) {
            results.addLast(Contact.get(result).getName());
        }
        for (HashMap.Entry<Character, Node> node : currentNode.map.entrySet()) {
            traverse(node.getValue(), result + node.getKey());
        }
    }
}
