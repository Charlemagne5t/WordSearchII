import java.util.*;

public class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        Trie trie = new Trie();
        for(String word : words){
            trie.insert(word);
        }
        Set<String> result = new HashSet<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                char start = board[i][j];
                if(trie.root.children.containsKey(start)){
                    boolean[][] visited = new boolean[m][n];
                    visited[i][j] = true;
                    dfs(board, i, j, visited, trie.root.children.get(start), result, "" + start);
                }

            }
        }


        return new ArrayList<>(result);

    }

    public void dfs(char[][] board, int i, int j, boolean[][] visited, TrieNode root, Set<String> result, String current){
        int m = board.length;
        int n = board[0].length;
        if(root.isTerminal){
            result.add(current);
        }
        if(current.length() > 10){
            return;
        }

        int[][] destinations = {{-1, 0},{0, 1},{1, 0},{0 , -1}};
        for(int[] d : destinations){
            int nextI = i + d[0];
            int nextJ = j + d[1];

            if(nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && !visited[nextI][nextJ] && root.children.containsKey(board[nextI][nextJ]) ){
                visited[nextI][nextJ] = true;
                dfs(board, nextI, nextJ, visited, root.children.get(board[nextI][nextJ]), result,  current + board[nextI][nextJ] );
                visited[nextI][nextJ] = false;
            }
        }

    }

}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        int wordLength = word.length();
        TrieNode temp = root;
        for (int i = 0; i < wordLength; i++) {
            if(!temp.children.containsKey(word.charAt(i))){
                TrieNode node = new TrieNode();
                temp.children.put(word.charAt(i), node);
            }
            temp = temp.children.get(word.charAt(i));
            if(i == wordLength - 1){
                temp.isTerminal = true;
            }
        }

    }

    public boolean search(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            if(temp.children.containsKey(word.charAt(i))){
                temp = temp.children.get(word.charAt(i));
            }else return false;
        }
        return temp.isTerminal;
    }

    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            if(temp.children.containsKey(prefix.charAt(i))){
                temp = temp.children.get(prefix.charAt(i));
            }else return false;
        }
        return true;
    }
}

class TrieNode{
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isTerminal;

    public TrieNode() {
        this.isTerminal = false;
    }
}
