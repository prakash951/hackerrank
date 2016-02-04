import java.util.*;
import java.lang.*;
import java.io.*;

class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc= new Scanner(System.in);
		int n = sc.nextInt();
		HashMap<Node,ArrayList<Node>> map = new HashMap<Node,ArrayList<Node>>();
		ArrayList<Node> set = new ArrayList<Node>();
		while(n-->0)
		{
			int a = sc.nextInt();
			int b = sc.nextInt();
			Node a1 = new Node(a);
			if(!set.contains(a1))
			{
				set.add(a1);
				a1.pred.add(a1);
			}
			else
			{
				a1 = set.get(set.indexOf(a1));
			}
			
			Node b1 = new Node(b);
			if(!set.contains(b1))
			{
				set.add(b1);
				b1.pred.add(b1);
			}
			else
			{
				b1 = set.get(set.indexOf(b1));
			}

			if(map.get(a1)==null)
			{
				ArrayList<Node> al = new ArrayList<Node>();
				al.add(b1);
				map.put(a1,al);
			}
			else
			{
				map.get(a1).add(b1);
			}
		}
		//System.out.println(map);
		bfs(map);
		System.out.println(set);
	}
	
	public static void bfs(HashMap<Node,ArrayList<Node>> map)
	{
		
		Queue<Node> queue = new LinkedList<Node>();
		Node xyz = map.keySet().iterator().next();
		queue.add(xyz);
		int level = 1;
		xyz.level = 1;
		xyz.flag = true;
		while(queue.size() > 0)
		{
			xyz = queue.poll();
			ArrayList<Node> z = map.get(xyz);
			xyz.flag = true;
			if(z!=null)
			{
				for(int i=0;i<z.size();i++)
				{
					if(!z.get(i).flag)
					{
						if(z.get(i).predecessor==null)
						{
							z.get(i).predecessor = xyz;
							z.get(i).pred.addAll(xyz.pred);
						}							
						else
						{
							if(z.get(i).predecessor.vertex != xyz.vertex)
							{
								z.get(i).predecessor = xyz.predecessor;
								z.get(i).pred.clear();
								z.get(i).pred.add(z.get(i));
								z.get(i).pred.addAll(xyz.predecessor!=null ? xyz.predecessor.pred : null);
							}
						}
						z.get(i).level = z.get(i).level==0?xyz.level + 1:z.get(i).level;
						queue.add(z.get(i));
					}
				}
			}
		}
	}
}
class Node
{
	int vertex;
	HashSet<Node> pred = null;
	boolean flag;
	int level;
	Node predecessor;
	
	public Node(int vertex)
	{
		this.vertex = vertex;
		pred = new HashSet<Node>();
	}
	public boolean equals(Object obj)
	{
		return this.vertex == ((Node)obj).vertex;
	}
	public int hashCode()
	{
		return vertex;
	}
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("(");
		Iterator<Node> it = pred.iterator();
		while(it.hasNext())
		{
			buffer.append(it.next().vertex+",");
		}
		buffer.append(")");
		String cobra = predecessor!=null ? ""+predecessor.vertex : null;
		return "[\t Vertex:"+ vertex + ",\t Dominators:{\t"+ buffer.toString() +"},\t Predecessor:"+ cobra +",\t Level:"+level+"]";
	}
}
