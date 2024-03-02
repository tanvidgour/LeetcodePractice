/* MEDIUM
 * 
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

 

Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

 */

 class LRUCache {

    class DLinkNode{
        int key;
        int value;
        DLinkNode next;
        DLinkNode prev;
    }

    public void addNode(DLinkNode node){
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    public void removeNode(DLinkNode node){
        DLinkNode prev = node.prev;
        DLinkNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    public void moveToHead(DLinkNode node){
        removeNode(node);
        addNode(node);
    }

    public DLinkNode popTail(){
        DLinkNode node = tail.prev;
        removeNode(node);
        return node;
    }

    Map<Integer,DLinkNode> cache = new HashMap<>();
    DLinkNode head,tail;
    int size,capacity;

    LRUCache(int capacity){
        head = new DLinkNode();
        tail = new DLinkNode();
        size = 0;
        this.capacity = capacity;

        head.next = tail;
        tail.prev = head;
    }

    public void put(int key, int value){
        DLinkNode node = cache.get(key);
       if(node == null){
           DLinkNode newNode = new DLinkNode();
           newNode.key = key;
           newNode.value = value;
           cache.put(key,newNode);

           addNode(newNode);
           size++;

           if(cache.size() > capacity){
               DLinkNode tail = popTail();
               cache.remove(tail.key);
               size--;
           }
       }else{
           node.value = value;
           moveToHead(node);
       }
    }

    public int get(int key){
        DLinkNode node = cache.get(key);
        if(node == null)
            return -1;
        moveToHead(node);
        return node.value;
    }

}
