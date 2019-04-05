package datastruct;

import java.util.NoSuchElementException;

public class BinarySearchTree<E extends Comparable<E>> {
    private static class Node<E extends Comparable<E>>{
        E val;
        Node<E> left;
        Node<E> right;

        public Node(){}
        public Node(E val){ this.val=val;}
    }

    private Node<E> root;
    private int size;

    public void add(E val){
        root=add(root,val);
        size++;
    }
    private Node<E> add(Node<E> root,E val){
        if(root==null)
            return new Node<>(val);
        if(val.compareTo(root.val)<0)
            root.left=add(root.left,val);
        else if(val.compareTo(root.val)>0)
            root.right=add(root.right,val);
        return root;
    }

    public int size(){
        return size;
    }

    public boolean contains(E val){
        return contains(root,val);
    }
    private boolean contains(Node<E> root,E val){
        if(root==null)
            return false;
        int res=root.val.compareTo(val);
        if(res==0)
            return true;
        else if(res<0)
            return contains(root.left,val);
        else
            return contains(root.right,val);
    }

    public E minVal(){
        Node<E> cur=root;
        if(cur==null) throw  new NoSuchElementException();
        while(cur.left!=null)
            cur=cur.left;
        return cur.val;
    }

    public E maxVal(){
        Node<E> cur=root;
        if(cur==null) throw  new NoSuchElementException();
        while(cur.right!=null){
            cur=cur.right;
        }
        return cur.val;
    }

    private Node<E> removeMin() {
        Node<E> cur=root;
        if(cur==null) return root;
        Node<E> parent=cur;
        while (cur.left!=null) {
            parent=cur;
            cur = cur.left;
        }
        Node res=cur;
        if(cur==parent){
            root=cur.right;
            cur.right=null;
        }else
            parent.left=null;
        return res;
    }

    public Node<E> remove(Node<E> root,E val){
        if(root==null)
            return root;
        int comp=root.val.compareTo(val);
        if(comp==0){
            Node newroot;
            if(root.left==null){
                newroot=root.right;
                root.right=null;
            }else if(root.right==null){
                newroot=root.left;
                root.left=null;
                size--;
            }else{
                newroot=removeMin();
                newroot.left=root.left;
                newroot.right=root.right;
                root.left=root.right=null;
            }
            size--;
            return newroot;
        }
        else if(comp<0)
            return remove(root.left,val);
        else
            return remove(root.right,val);
    }
}
