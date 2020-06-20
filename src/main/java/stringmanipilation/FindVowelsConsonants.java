package stringmanipilation;

/**
 * Created by Kaiser on 4/4/20.
 */
public class FindVowelsConsonants {
    public static void main(String[] args) {

        String test = "Kaiser Ahmed";
        System.out.println(test);
        findVowelsConsonants(test);




    }

    public static void findVowelsConsonants(String string){
        StringBuilder vowels = new StringBuilder();
        StringBuilder consonants = new StringBuilder();

        int countVowels = 0;
        int countConsonants = 0;

        String englishVowels = "aeiou";

        char [] normalizedInput = string.trim().toLowerCase().toCharArray();

        if (!string.isEmpty()){
            for (char c : normalizedInput){
                //check if the char is in the vowels string
                if (englishVowels.indexOf(c) != -1){

                    //is it already a found vowel letter?
                    if (vowels.toString().indexOf(c) == -1){
                        vowels.append(c);
                    }

                    //Increase vowel count
                    countVowels++;

                }
                //check it's not a space
                else if (c != ' '){

                    //is it a previously found consonant?
                    if (consonants.toString().indexOf(c) == -1){
                        consonants.append(c);
                    }
                    // increse consonant count
                    countConsonants++;
                }
            }
        }

        System.out.println(countVowels + " Vowels: " + vowels);
        System.out.println(countConsonants+ " Consonants: " + consonants);

    }
}
