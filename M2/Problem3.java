package M2;
import java.util.Arrays;
public class Problem3 {
    public static void main(String[] args) {
        //Don't edit anything here
        Integer[] a1 = new Integer[]{-1, -2, -3, -4, -5, -6, -7, -8, -9, -10};
        Integer[] a2 = new Integer[]{-1, 1, -2, 2, 3, -3, -4, 5};
        Double[] a3 = new Double[]{-0.01, -0.0001, -.15};
        String[] a4 = new String[]{"-1", "2", "-3", "4", "-5", "5", "-6", "6", "-7", "7"};
        
        bePositive(a1);
        bePositive(a2);
        bePositive(a3);
        bePositive(a4);
    }
    // <T> turns this into a generic so it can take in any datatype, it'll be passed as an Object so casting is required
    static <T> void bePositive(T[] arr){
        System.out.println("Processing Array:" + Arrays.toString(arr));
        //your code should set the indexes of this array
        Object[] output = new Object[arr.length];
        //iaa47
        //9/25/2023
        //To create the positive conversion I created an if statement that takes the integer in the array and if it is negative 
        //I run it through the math.abs function that takes the number and returns the absolute zero of that integer.  
        //Then I did the same for the two arrays containing doubles.  For the string value, I had to rewrite the string values with an 
        //if statement that if the string has a "-" it is to be replaced with a blank value (" ") to make it positive.
        for (int z = 0; z < arr.length; z++) {
            if (arr[z] instanceof Number) {
                if (((Number) arr[z]).doubleValue() < 0) {
                    if (arr[z] instanceof Integer) {
                        output[z] = Math.abs((Integer) arr[z]);
                    } else if (arr[z] instanceof Double) {
                        output[z] = Math.abs((Double) arr[z]);
                    }
                } else {
                    output[z] = arr[z];
                }
            } else if (arr[z] instanceof String) {
                String str = (String) arr[z];
                if (str.contains("-")) {
                    str = str.replace("-", "");
                }
                output[z] = str;
            }
        }
        //hint: use the arr variable; don't diretly use the a1-a4 variables

        //set the result to the proper index of the output array
        //hint: don't forget to handle the data types properly, the result datatype should be the same as the original datatype
        
        //end edit section

        StringBuilder sb = new StringBuilder();
        for(Object i : output){
            if(sb.length() > 0){
                sb.append(",");
            }
            sb.append(String.format("%s (%s)", i, i.getClass().getSimpleName().substring(0,1)));
        }
        System.out.println("Result: " + sb.toString());
    }
}
