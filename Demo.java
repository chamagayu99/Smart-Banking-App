import java.lang.reflect.Array;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        String[] a={"true"};
        // myMethod(a);
        int b=Integer.valueOf(a[0]);
        System.out.println(b+2);
        // System.out.println(Arrays.toString(a));

    }

    public static void myMethod(int[] a){
       int[] b={5,7};
       a=b;
    }
    
}
