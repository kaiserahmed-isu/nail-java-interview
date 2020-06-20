package stringmanipilation;

import javax.sound.midi.Soundbank;

/**
 * Created by Kaiser on 4/4/20.
 */
public class StringManipulation {
    public static void main(String[] args) {
        System.out.println("-----------String----------");

        /***
         * String: a data type that represent text rather than number
         *
         *  First, there are two ways we can create strings. We can create
         *  1. string literals or
         *  2. string objects.
         *  The difference between these is how they're stored and referenced.
         */

        //String literals
        String literalStrings = "test";
        String anotherLiteralString = "test";

        //both references to same object and value, and lives in String constant pool
        System.out.println(literalStrings == anotherLiteralString); //true

        //String Object
        String A = new String("test");
        String B = new String("test");

        //Lives in heap and different object
        System.out.println(A==B); //false

        //Compare
        System.out.println(A.equals(B)); //true

        //String is immutable, once created no change
        //StringBuilder and StringBuffer(tread safe) are mutable

        System.out.println("-----------String Concatenation----------");

        String firstName = "Kaiser";
        String lastName = "Ahmed";

        String name = firstName + " " + lastName;

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(name);

        System.out.println(firstName.concat(" ").concat(lastName));


        StringBuilder sb = new StringBuilder("abc");
        sb.append("DFE");
        sb.append("ghi");
        sb.insert(1, "IJK");
        sb.delete(4, 5);
        String s = sb.toString();
        System.out.println(sb);
        System.out.println(s);

        System.out.println("-----------Basic String Functions----------");
        String f = " Fish ";
        String h = "Horse     ";
        String c = "Cat";
        String nothing = " ";

        String [] items = {f,h,c, nothing};

        for (String item : items){
            item = item.trim();
            System.out.println(item);
            if (!item.isEmpty()){
                System.out.println("Not Empty");
            }

            if (item.length() > 3){
                System.out.println("length more than 3");
            }

            System.out.println(item.toLowerCase());
            System.out.println(item.toUpperCase());
            System.out.println("");

        }

        int index =2 ;
        System.out.println(f.charAt(index));

        System.out.println(f.indexOf('i'));
        System.out.println("Contains sh: "+ f.contains("sh"));

        System.out.println(reverseString("Kaiser"));

        System.out.println("");


        //some Advanced methods
        String paragraph = "This is a big sentence. To find this out, we can use Split, which is a String Instance Method. Split takes a regular expression and breaks down a given string around matches of the given regular expression, outputting a string array. On the most basic level, split will break a string based on a single character.";

        String[] sentences = paragraph.split("\\.");
        System.out.println("Number of sentences: "+ sentences.length);

        String [] words = paragraph.split(" ");
        System.out.println("Number of words: "+ words.length);

        System.out.println(paragraph.replace("this", "THIS"));

    }

    private static String reverseString(String s){
        StringBuilder reverse = new StringBuilder();

        for(int i = s.length() -1; i >= 0; i -- ){
            reverse.append(s.charAt(i));

        }
        return reverse.toString();
    }
}
