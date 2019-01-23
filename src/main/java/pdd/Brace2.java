package pdd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Brace2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input2.txt"));
        HashMap<Character,Integer> pri=new HashMap<>();
        pri.put('{',1);
        pri.put('[',2);
        pri.put('(',3);

        while(scanner.hasNextLine()){
            String str=scanner.nextLine();
            System.out.println(ifillegal(str,pri));
        }
    }

    public static boolean ifillegal(String str,HashMap<Character,Integer> pri){
        LinkedList<Character> stk=new LinkedList<>();
        for(char c:str.toCharArray()) {
            switch (c) {
                case '{':
                    if(stk.isEmpty()||
                            pri.get(c)>=pri.getOrDefault(stk.peek(),0))
                        stk.push(c);
                    else return false;
                    break;
                case '[':
                case '(':
                    if(stk.isEmpty()||pri.get(c)>pri.getOrDefault(stk.peek(),0))
                        stk.push(c);
                    else return false;
                    break;
                case '}':
                case ')':
                case ']':
                    while(!stk.isEmpty()&&(
                            stk.peek()!='{'&&stk.peek()!='['&&stk.peek()!='('))
                        stk.pop();
                    if(stk.isEmpty())   return false;
                    if(c=='}'&&stk.peek()!='{') return false;
                    if(c==')'&&stk.peek()!='(') return false;
                    if(c==']'&&stk.peek()!='[') return false;
                    stk.pop();
                    break;
                default:
                    stk.push(c);
            }
        }
        return true;
    }
}
