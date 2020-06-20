package leetcode;

import java.util.*;

/**
 * Created by Kaiser on 6/16/20.
 */
public class L1268_SearchSuggestionsSystem {
    //O(nlogn + n*m)

/*    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        Arrays.sort(products); //O(nlogn)

        //adding to Tries O(n x m), n = number of products and m = avg length of products
        for(int i = 0; i < products.length; i++){
            TrieNode curr = root;
            for(char c : products[i].toCharArray()){
                if(curr.childMap.get(c) == null){
                    curr.childMap.put(c, new TrieNode());
                }
                curr = curr.childMap.get(c);
                if(curr.suggestions.size() < 3){
                    curr.suggestions.add(products[i]);
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        TrieNode curr = root;
        for(char c : searchWord.toCharArray()){
            if(curr != null){
                curr = curr.childMap.get(c);
            }
            result.add(curr == null ? Arrays.asList() : curr.suggestions);
        }
        return result;
    }

    class TrieNode{
        Map<Character, TrieNode> childMap = new HashMap<>();
        List<String> suggestions = new ArrayList<>();
    }
    */


//We can use a special kind of trie for this problem. Instead of keeping words in the bottom (last) node for each word we do the other way around - keep word in every node starting from the first character. This way we can immidiately get all words for the current character without traversing the trie. This way we're making a trade off memory (each trie node keep up to three words) for performance.
//
//For the sorted result we do following optimizations - we sort the ditionary before we start, this way all words will be added to the trie in a sorted order. Then in every node we can fill the word's list in a greedy manner - once we've filled 3 elements of the list skip every next word. This way we can just add all the list from each trie node to result.

    /*public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        //sort words so they will be added in a sorted order to nodes
        Arrays.sort(products);

        Trie root = new Trie();
        for (String prod : products) {
            Trie n = root;
            for (char ch : prod.toCharArray()) {
                int i = ch - 'a';
                if (n.next[i] == null) {
                    n.next[i] = new Trie();
                }
                n = n.next[i];
                if (n.words.size() < 3)
                    n.words.add(prod);
            }
        }

        List<List<String>> res = new ArrayList();
        Trie n = root;
        //start going over the search word char by char
        for (int i = 0; i < searchWord.length(); i++) {
            n = n.next[searchWord.charAt(i) - 'a'];
            //if we met null - means no more matches possible, the result of result can be filled by empty lists
            if (n == null) {
                for (int j = i; j < searchWord.length(); j++)
                    res.add(Collections.EMPTY_LIST);
                break;
            }
            res.add(n.words);
        }
        return res;
    }
    //trie node
    class Trie {
        Trie[] next;
        List<String> words;
        Trie() {
            words = new ArrayList();
            next = new Trie[26];
        }
        }*/

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products); // sort products.
        Trie root = new Trie();
        for (String p : products) { // build Trie.
            insert(p, root); // insert a product into Trie.
        }
        return search(searchWord, root);
    }



    private void insert(String p, Trie root) {
        Trie t = root;
        for (char c : p.toCharArray()) { // insert current product into Trie.
            if (t.sub[c - 'a'] == null)
                t.sub[c - 'a'] = new Trie();
            t = t.sub[c - 'a'];
            if (t.suggestion.size() < 3) // maintain 3 lexicographically minimum strings.
                t.suggestion.offer(p); // put products with same prefix into suggestion list.
        }
    }
    class Trie {
        Trie[] sub = new Trie[26];
        LinkedList<String> suggestion = new LinkedList<>();
    }
    private List<List<String>> search(String searchWord, Trie root) {
        List<List<String>> ans = new ArrayList<>();
        for (char c : searchWord.toCharArray()) { // search product.
            if (root != null) // if there exist products with current prefix.
                root = root.sub[c - 'a'];
            ans.add(root == null ? Arrays.asList() : root.suggestion); // add it if there exist products with current prefix.
        }
        return ans;
    }



    public static void main(String[] args) {
        String [] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";
        L1268_SearchSuggestionsSystem program = new L1268_SearchSuggestionsSystem();
        System.out.println(program.suggestedProducts(products, searchWord));
//        System.out.println('z' - 'a');
    }
}

