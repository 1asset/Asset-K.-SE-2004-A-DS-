import java.util.*;

public class hashTable{
    public static void main(String[] args){
		HashTable<Integer, Integer> map = new HashTable<>();
	}
}
class HashTable<A, B>{
    class Node<C, D>{ 
    	C key;D value;
    	final int HASH;
    	Node<C, D> next;
    	public Node(C nk, D nv, int nhc){
    		key = nk;HASH = nhc;value = nv;
    	}
    }
	public int num;
	public int numBucket;
	public ArrayList<Node<A, B>> nodeArray;
	public HashTable(){
		num = 0;
		numBucket = 20;
		nodeArray = new ArrayList<>();
		for(int i=0; i<numBucket;++i)nodeArray.add(null);
	}
	public int size(){return num;}
	public boolean isEmpty(){return(num==0);}
	public void add(A key, B value){
		int pos = getPos(key); int HASH = Objects.hashCode(key);
		Node<A, B> cur = nodeArray.get(pos);
		for(; cur != null; cur = cur.next)if(cur.key.equals(key) && cur.HASH == HASH){cur.value = value;return;}
		num++; cur = nodeArray.get(pos);
		Node<A, B> novyi = new Node<A, B>(key, value, HASH); novyi.next = cur;
		nodeArray.set(pos, novyi);
		if(1.0*num < 0.68*numBucket)return;
		ArrayList<Node<A, B>> old = nodeArray;
		nodeArray = new ArrayList<>();
		num = 0;
		numBucket *= 2;
		for(int i=0; i<numBucket;++i)nodeArray.add(null);
		for(Node<A, B> it:old){
			for(; it != null; it = it.next){
				add(it.key, it.value);
			}
		}
	}
	public B get(A key){
		int HASH = Objects.hashCode(key);
		for(Node<A, B> cur = nodeArray.get(getPos(key)); cur != null; cur=cur.next)if(cur.key.equals(key) && cur.HASH == HASH)return cur.value;
		return null; 
	}
	public int getPos(A key){
		int val = Objects.hashCode(key)%numBucket;
		return(val < 0 ? -val:val);
	}
	public B remove(A key){
		int pos = getPos(key);
		int HASH = Objects.hashCode(key);
		Node<A, B> cur = nodeArray.get(pos);
		Node<A, B> prev = null;
		for(; cur != null; cur = cur.next){
			if(cur.key.equals(key) && HASH == cur.HASH)break;
			prev = cur;
		}
		if(cur==null)return null;
		num--;
		if(prev != null)prev.next=cur.next;
		else nodeArray.set(pos,cur.next);
		return cur.value;
	}
}