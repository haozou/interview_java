import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	static class DDLNode{
		int key;
		int val;
		DDLNode next;
		DDLNode prev;
		public DDLNode(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}
    private Map<Integer, DDLNode> dict = new HashMap<Integer, DDLNode>();
    private DDLNode head;
    private DDLNode tail;
    private int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (dict.containsKey(key)) {
        	DDLNode node = dict.get(key);
        	remove(node);
        	setHead(node);
        	return node.val;
        	
        } else {
        	return -1;
        }
    }
    private void remove(DDLNode node) {
    	DDLNode prev = node.prev;
    	DDLNode next = node.next;
    	if (prev != null){
    		prev.next = next; 
    	} else {
    		head = next;
    		if (head != null) {
    			head.prev = null;
    		}
    	}
    	
    	if (next != null) {
    		next.prev = prev;
    	} else {
    		tail = prev;
    		if (tail != null) {
    			tail.next = null;
    		}
    	}
    }
    private void setHead(DDLNode node) {
    	node.next = head;
    	node.prev = null;
    	if (head != null) {
    		head.prev = node;
    	} 
    	head = node;
    	if (tail == null) {
    		tail = node;
    	}
    }
    
    public void set(int key, int value) {
        if (dict.containsKey(key)) {
        	DDLNode node = dict.get(key);
        	node.val = value;
        	remove(node);
        	setHead(node);
        } else {
        	DDLNode node = new DDLNode(key, value);
        	if (dict.size() == this.capacity) {
        		dict.remove(tail.key);
        		remove(tail);
        		setHead(node);
        		dict.put(key, node);
        	} else {
        		setHead(node);
        		dict.put(key, node);
        	}
        }
    }
}