package datastruct.seq;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Integer> arr=new ArrayList<>();
        arr.add(1);arr.add(2);
        ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream("arr"));
        os.writeObject(arr);
        os.close();

        ObjectInputStream oi=new ObjectInputStream(new FileInputStream("arr"));
        ArrayList<Integer> arr1= (ArrayList<Integer>) oi.readObject();
        for(int i=0;i<arr1.size();i++) {
            System.out.println(arr1.get(i));
        }
    }
}
