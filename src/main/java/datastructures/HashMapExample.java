package datastructures;

import java.util.HashMap;

/**
 * Created by Kaiser on 4/4/20.
 */
public class HashMapExample {
    public static void main(String[] args) {

        //Hashmap: A key-value pairs and Hashing function to store and organize data

        // Hashing function maps a key or object to a specific hash

        // Fast retrieve value using key
        // can save multiple values with one key (hash collusion)


        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);
        hashMap.put("four", 4);

        System.out.println(hashMap.get("two"));
        System.out.println(hashMap.keySet());

        System.out.println(hashMap.remove("one"));

        System.out.println(hashMap);


        System.out.println("--------Most repeated character-----------");

        String st = "Apple";
        System.out.println("For "+ st );
        mostRepeatedChar(st);

    }

    public static void mostRepeatedChar(String string){

        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();

        //Loop over string with starting index 0 to < length
        for (int i=0; i<string.length(); i++) {
            //get each char
            Character ch = string.charAt(i);

            //check if char (as key) is in the hashmap then
            if (hashMap.containsKey(ch)) {
                //Increase the value
                hashMap.put(ch, hashMap.get(ch)+1);
            }
            //Else
            else {
                //Add to hashmap with value 1 with char as key
                hashMap.put(ch, 1);
            }
        }

        System.out.println(hashMap);

        Character maxChar = ' ';
        int maxValue = 0;
        //loop over hash map
        for (Character key : hashMap.keySet()){
            if (maxValue < hashMap.get(key)){
                maxValue = hashMap.get(key);
                maxChar = key;
            }
        }

        System.out.println("Most repeated Char: "+ maxChar + " | Times: " + maxValue);


        //set a maxValue = the value of first key and maxChar = key
        // if maxvalue is less then current key's value then maxValue = value and maxChar= key

        //print the maxValue and maxChar
    }

}
