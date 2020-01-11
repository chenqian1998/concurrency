package conllectionSafety;


import java.util.ArrayList;

public class ArrayListDemo {
    public static  void test(){
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("123");
        arrayList.add("456");
        arrayList.add("789");

        for( String s : arrayList){
            System.out.println(s);
        }
    }


    public static void main(String[] args) {
        test();
    }
}
