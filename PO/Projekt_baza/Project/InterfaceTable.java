import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Tabela w ktorej wyswietlamy obiekty z bazy
 */
public class InterfaceTable extends InterfaceFrame
{
    protected JTable table;
    protected JScrollPane sp;
    protected DefaultTableModel dtm;

    /**
     * Konstruktor
     * @param dtm
     */
    public InterfaceTable(DefaultTableModel dtm)
    {
        table = new JTable(dtm);
        this.dtm=dtm;
        table.setPreferredScrollableViewportSize(new Dimension(Const.width/2+200,Const.heigth/2));
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        table.getColumnModel();
        table.getColumnModel().getColumn(0).setPreferredWidth(150);

        sp = new JScrollPane(table);
        sp.setBounds(Const.width/2-150,Const.heigth/2-60,Const.width/2+220,Const.heigth/2);

    }
}
