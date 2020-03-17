import javax.swing.*;

public abstract class InterfaceTextField extends InterfaceFrame
{
    JTextField textField;
}

/**
 * Pole tekstowe
 */
class GeoTextField extends InterfaceTextField
{
    /**
     * Konstruktor
     * @param i indeks pola tekstowego w oknie AddFrame
     */
    public GeoTextField(int i)
    {
        textField = new JTextField(empty,40);
        textField.setBounds(150,50*(i+2),150,50);
    }
}
