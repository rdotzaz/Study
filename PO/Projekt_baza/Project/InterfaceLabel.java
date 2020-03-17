import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class InterfaceLabel extends InterfaceFrame
{
    JLabel label;
}

/**
 * JLabel z czcionka Arial
 */
class TitleLabel extends InterfaceLabel
{

    protected Font f;

    /**
     * Konstruktor
     * @param name nazwa
     * @param pos_x pozycja x w oknie
     * @param pos_y pozycja y w oknie
     * @param w szerokosc w oknie
     * @param h wysokosc w oknie
     * @param text_size rozmiar tekstu
     * @param pos pozycja w JLabel (na przyklad: SwingConstans.CENTER)
     */
    public TitleLabel(String name, int pos_x, int pos_y, int w, int h, int text_size, int pos)
    {
        label = new JLabel(name,pos);

        label.setBounds(pos_x,pos_y,w,h);
        f = new Font("Arial",Font.BOLD,text_size);
        label.setFont(f);

    }
}
