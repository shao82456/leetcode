package datastruct;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayList<T> implements Iterable<T>{
    private static final int DEFAULT_CAPACITY=10;

    private int theSize;
    private T[] theItems;

    public ArrayList(){
        doClear();
    }
    public void clear(){
        doClear();
    }
    public int size(){
        return theSize;
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public void trimToSize(){
        ensureCapacity(size());
    }
    public T get(int pos){
        if(pos<0||pos>=size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[pos];
    }
    public T set(int pos, T ele){
        if(pos<0||pos>=size())
            throw new ArrayIndexOutOfBoundsException();
        T old= theItems[pos];

        theItems[pos]=ele;
        return old;
    }
    public boolean add(T ele){
        add(size(),ele);
        return true;
    }

    public boolean addAll(List<T> list){
        for(T ele:list)
            add(ele);
        return true;
    }

    public void add(int pos, T ele) {
        if(theItems.length==size()){
            ensureCapacity(size()*2+1);
        }
        for(int i=theSize;i>pos;i--)
            theItems[i]=theItems[i-1];
        theItems[pos]=ele;
        theSize++;
    }

    public T remove(int pos){
        T item=theItems[pos];
        for(int i=pos;i<theSize-1;i++){
            theItems[i]=theItems[i+1];
        }
        theSize--;
        return item;
    }

    private void doClear() {
        theSize=0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    private void ensureCapacity(int newCapacity) {
        if(newCapacity<theSize) return;

        T[] old=theItems;
        theItems=(T[]) new Object[newCapacity];
        for(int i=0;i<size();i++)
            theItems[i]=old[i];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T>{
        //要被查看的下一元素的索引
        private boolean okToRemove=false;
        private int cur=0;
        @Override
        public boolean hasNext() {
            return cur<size();
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException();
            okToRemove=true;
            return theItems[cur++];
        }
        @Override
        public void remove(){
            if(!okToRemove)
                throw  new IllegalStateException();
            ArrayList.this.remove(--cur);
            okToRemove=false;
        }
    }
}
