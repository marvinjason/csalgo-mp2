
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToposortPanel extends JPanel
{
    private BinaryTree tree;
    private final JLabel label1;
    private final JLabel label2;
    
    public ToposortPanel()
    {
        tree = new BinaryTree();
        label1 = new JLabel("Sorted Values:");
        label1.setBounds(10, 200, 175, 30);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label2 = new JLabel();
        label2.setBounds(10, 230, 250, 30);
        label2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        this.setSize(369, 267);
        this.setLayout(null);
        this.add(label1);
        this.add(label2);
    }
    
    public void setValues(int[][] matrix)
    {
        tree = new BinaryTree();
        
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                tree.insert(matrix[i][j]);
            }
        }
        
        ArrayList<Integer> list = new ArrayList(tree.getSortedArray());
        
        label2.setText(Integer.toString(list.get(0)));

        for (int i = 1; i < list.size(); i++)
        {
            label2.setText(label2.getText() + " ," + Integer.toString(list.get(i)));
        }

        this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        if (!tree.getNodes().isEmpty())
        {
            for (Node node: tree.getNodes())
            {
                g.fillOval(node.xAxis, node.yAxis, 20, 20);
                
                if (node.parent != null)
                {
                    g.drawLine(node.xAxis + 10, node.yAxis + 10, node.parent.xAxis + 10, node.parent.yAxis + 10);
                }
            }
            
            for (Node node: tree.getNodes())
            {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Segoe UI", Font.BOLD, 12));
                g.drawString(Integer.toString(node.data), node.xAxis + 6, node.yAxis + 14);
                g.setColor(Color.BLACK);
            }
            
            tree.toposort(g);
        }
    }
}
