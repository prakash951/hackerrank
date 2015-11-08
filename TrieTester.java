/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;


class Ideone {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        String result = "";
        String resultstr = "";
        while(n-- > 0)
            {
            String str = br.readLine();
            trie.insert(str);
            if(trie.flag)
            {
                if(result=="")
                result = "BAD SET";
                if(resultstr=="")
                resultstr = str;
                break;
            }
        }
        if(result!="")
            {
            System.out.println(result);
            System.out.println(resultstr);
        }
        else
            {
            System.out.println("GOOD SET");
        }
        trie.traverse(trie.root);
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
    public void insertUtil(String str,TrieNode root)
        {
        for(int i=0;i<str.length();i++)
            {
        		int z = atoi(str.charAt(i));
                if(z!=-1)
                {
                    if(root.nodes[z]==null)
                    {
                        root.nodes[z] = new TrieNode(str.charAt(i));
                    }
                    root = root.nodes[z];
                    if(root.word==true)
                    {
                        flag = true;
                    }
                }
                /**else
                    {
                    flag = true;
                }*/
        }
        root.word = true;
    }
    
    public void traverse(TrieNode root)
    {
    	if(root==null)
    		return;
    	char[] ch = new char[1024];
    	traverseUtil(root,ch,0);
    	
    }
    
    
    public void traverseUtil(TrieNode root,char[] ch,int index)
    {
    	if(root==null) return;
    	if(root.word)
    	{
    		System.out.println(new String(ch,0,index));
    		return;
    	}
    	for(int i=0;i<32;i++)
    	{
    		if(root.nodes[i]!=null)
    		{
    			ch[index] = root.nodes[i].data;
    			traverseUtil(root.nodes[i],ch,index+1);
    		}
    	}
    	
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
