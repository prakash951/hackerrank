import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        String result = "";
        String resultstr = "";
        while(n-- > 0)
        {
                String str = br.readLine();
                if(!trie.find(str,trie.root))
                {
                     trie.insert(str);
                }
                else
                {
                    System.out.println("BAD SET");
                    System.out.println(str);
                    return;
                }
        }
        System.out.println("GOOD SET");
    }
}

class Trie
{
    TrieNode root;
    public void insert(String str)
    {
        if(root==null)
            {
            root = new TrieNode(' ');        
        }
        insertUtil(str,root);
    }
    public boolean flag = false;
    
    public boolean find(String str,TrieNode root)
    {
        if(root==null) return false;
        for(int i=0;i<str.length();i++)
        {
            int z = atoi(str.charAt(i));
            if(root.nodes[z]==null)
            {
                return false;
            }
            if(root.nodes[z].word==true)
                {
                return true;
            }
            root = root.nodes[z];
        }
        return true;
    }
    public void insertUtil(String str,TrieNode root)
        {
        for(int i=0;i<str.length();i++)
            {
                    int z = atoi(str.charAt(i));
                    if(root.nodes[z]==null)
                    {
                        root.nodes[z] = new TrieNode(str.charAt(i));
                    }
                    root = root.nodes[z];
        }
        root.word = true;
    }
    
    public int atoi(char ch)
        {
            if(ch>=97)
                {
                return ch - 97;
            }
        return -1;
    }
}

class TrieNode
    {
    char data;
    TrieNode[] nodes;
    boolean word;
    public TrieNode(char data)
        {
        this.data = data;
        nodes = new TrieNode[32];
    }
}
