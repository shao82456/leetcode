package baidu;

import java.util.LinkedList;

public class Solution {

    static class Employee{
        int val;
        LinkedList<Employee> follow=new LinkedList<>();

        public Employee(int val) {
            this.val = val;
        }
    }
    static int f(Employee e){
        return Integer.max(f1(e),f2(e));
    }

    //e参加
    static int f1(Employee e){
        if(e.follow.size()==0)
            return e.val;
        int res=e.val;
        for(Employee fol:e.follow){
            res+=f2(fol);
        }
        return res;
    }

    //e不参加
    static int f2(Employee e){
        if(e.follow.size()==0)
            return 0;
        int res=0;
        for(Employee fol:e.follow){
            res+=Integer.max(f1(fol),f2(fol));
        }
        return res;
    }

    /***
     *      3
     *     5 6
     *    9
     */

    public static void main(String[] args) {
        Employee e1=new Employee(3);
        Employee e2=new Employee(5);
        Employee e3=new Employee(6);
        Employee e4=new Employee(9);

        e2.follow.add(e4);
        e1.follow.add(e2);
        e1.follow.add(e3);

        System.out.println(f(e1));
    }
}
