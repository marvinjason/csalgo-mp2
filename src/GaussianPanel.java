
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class GaussianPanel extends JPanel
{
    private final JTable table;
    private final JLabel xLabel;
    private final JLabel yLabel;
    private final JLabel zLabel;
    
    public GaussianPanel()
    {
        table = new JTable(3, 4){
            
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            
            {
                render.setHorizontalAlignment(SwingConstants.CENTER);
            }
            
            @Override
            public TableCellRenderer getCellRenderer(int arg0, int arg1)
            {
                return render;
            }
        };

        table.setBounds(60, 20, 250, 150);
        table.setValueAt(0, 0, 0);
        table.setRowHeight(50);
        table.setIntercellSpacing(new Dimension(15, 15));
        table.setFont(new Font("Segoe UI", Font.BOLD, 24));
        table.setRowSelectionAllowed(false);
        table.setFocusable(false);
        
        xLabel = new JLabel("x:");
        xLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        xLabel.setBounds(150, 180, 100, 25);
        
        yLabel = new JLabel("y:");
        yLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        yLabel.setBounds(150, 207, 100, 25);
        
        zLabel = new JLabel("z:");
        zLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        zLabel.setBounds(150, 234, 100, 25);
        
        this.setSize(369, 267);
        this.setLayout(null);
        this.add(table);
        this.add(xLabel);
        this.add(yLabel);
        this.add(zLabel);
    }
    
    public void setValues(int[][] matrix)
    {
        GaussianElimination elimination = new GaussianElimination(matrix);
        
        xLabel.setText("x:  " + elimination.getX());
        yLabel.setText("y:  " + elimination.getY());
        zLabel.setText("z:  " + elimination.getZ());
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                table.setValueAt(elimination.getMatrix()[i][j], i, j);
            }
        }
    }
}
