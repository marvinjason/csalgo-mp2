
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class BinaryTree
{
    private final Node root;
    private ArrayList<Node> nodes;
    private ArrayList<Integer> list;
    
    public BinaryTree()
    {
        root = new Node();
        nodes = new ArrayList();
        list = new ArrayList();
    }
    
    public ArrayList<Node> getNodes()
    {
        return nodes;
    }
    
    public ArrayList<Integer> getSortedArray()
    {
        toposort(root);
        return list;
    }
    
    public void insert(int data)
    {
        insert(data, root, 160, 10);
    }
    
    private void insert(int data, Node node, int x, int y)
    {
        if (node.data == null)
        {
            node.data = data;
            
            node.leftChild = new Node();
            node.rightChild = new Node();
            node.parent = null;
            
            node.xAxis = x;
            node.yAxis = y;
            
            nodes.add(node);
        }
        else
        {  
            int z = node.parent == null ? x : Math.abs(node.parent.xAxis - x);
            
            if (node.data > data)
            {
                insert(data, node.leftChild, x - (z / 2), y + 25);
                node.leftChild.parent = node;
            }
            else
            {
                insert(data, node.rightChild, x + (z / 2), y + 25);
                node.rightChild.parent = node;
            }
        }
    }
    
    private void dfs(Node node)
    {
        if (node.data != null)
        {
            list.add(node.data);
            
            dfs(node.leftChild);
            dfs(node.rightChild);
        }
    }
    
    private void toposort(Node node)
    {
        if (node.data != null)
        {
            list.add(node.data);
            
            toposort(node.rightChild);
            toposort(node.leftChild);
        }
    }
    
    public void toposort(Graphics g)
    {
        toposortCtr = 0;
        toposort(root, g);
    }
    
    private int toposortCtr = 0;
    
    private void toposort(Node node, Graphics g)
    {
        if (node.data != null)
        {
            ++toposortCtr;
            
            g.setColor(Color.RED);
            g.setFont(new Font("Segoe UI", Font.PLAIN, 9));
            g.drawString(Integer.toString(toposortCtr), node.xAxis - 3, node.yAxis);
            
            toposort(node.leftChild, g);
            toposort(node.rightChild, g);
            
            ++toposortCtr;
            g.drawString(Integer.toString(toposortCtr), node.xAxis + 12, node.yAxis);
        }
    }
}

class Node
{
    Integer data;
    Node parent;
    Node leftChild;
    Node rightChild;
    int xAxis;
    int yAxis;
}