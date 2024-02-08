import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SolutionTest {
    @Test
    public void test1(){
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain", "hklf", "hf"};
        List<String> expected = new ArrayList<>(List.of("oath","eat","hklf","hf"));
        List<String> actual = new Solution().findWords(board, words);
        Assert.assertEquals(expected, actual);

    }
}
