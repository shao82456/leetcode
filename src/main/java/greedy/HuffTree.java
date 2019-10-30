package greedy;

import util.BinaryTreeNode;
import util.TreeUtil;

import java.util.PriorityQueue;

public class HuffTree {
    static class Node extends BinaryTreeNode<Character> implements Comparable<Node>{
        int n;
        @Override
        public int compareTo(Node o) {
            return n-o.n;
        }

        public Node(Character c, int n) {
            super(c);
            this.n = n;
        }

        @Override
        public String toString() {
            return ""+value+":"+n;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Node> pq=new PriorityQueue<>();
        pq.offer(new Node('a',10));
        pq.offer(new Node('e',15));
        pq.offer(new Node('i',12));
        pq.offer(new Node('s',3));
        pq.offer(new Node('t',4));
        pq.offer(new Node(' ',13));
        pq.offer(new Node('\n',1));

        int i=1;
        while(pq.size()!=1){
            Node n1=pq.poll();
            Node n2=pq.poll();
            Node t=new Node((char) (i+48),n1.n+n2.n);
            t.left=n1;
            t.right=n2;
            pq.offer(t);
            i+=1;
        }
        Node root=pq.poll();
        System.out.println(TreeUtil.preview(root));
    }

}
