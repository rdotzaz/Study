import javax.swing.*;

public abstract class InterfaceComboBox extends InterfaceFrame
{
    JComboBox box;
}

/**
 * JComboBox z lista rodzajow obiektow (czyli "Cities", "Villages" ...)
 */
class GeoComboBox extends InterfaceComboBox
{
    protected String[][] strings;

    /**
     * Konstruktor
     * @param x pozycja x w oknie
     * @param y pozycja y w oknie
     * @param w szerokosc w oknie
     * @param h wysokosc w oknie
     */
    public GeoComboBox(int x, int y, int w, int h)
    {
        box = new JComboBox();
        box.setBounds(x,y,w,h);//(50,230,150,50);
        this.set_strings();
    }

    /**
     * Metoda dodajaca napisy do JComboBox
     */
    protected void set_strings()
    {
        String[][] s = {
                {"c", "Cities"},
                {"a", "Airports"},
                {"l", "Lakes"},
                {"m", "Mountains"},
                {"p", "Parks"},
                {"r", "Rivers"},
                {"rd", "Roads"},
                {"s", "Seas"},
                {"v", "Villages"},

        };
        this.strings=s;
        box.addItem("Select");
        for(int i=0;i<9;i++)
        {
            box.addItem(strings[i][1]);
        }
        box.setSelectedItem("Select");
    }

    /**
     * Metoda zwracajaca aktualny napis w JComboBox
     * @return rodzaj obiektu
     */
    public String get_currnet_item()
    {
        return  (String)box.getSelectedItem();
    }

    /**
     * Metoda zwracajaca id obiektu, potrzebne do metody filer() w bazie
     * @param s rodzaj obiektu
     * @return identyfikator
     */
    public String get_id(String s)
    {
        for(int i=0;i<9;i++)
        {
            if(strings[i][1]==s) return strings[i][0];
        }
        return "error";
    }
}

class OptionComboBox extends InterfaceComboBox
{
    protected String[] strings;

    /**
     * Konstruktor
     * @param x pozycja x w oknie
     * @param y pozycja y w oknie
     * @param w szerokosc w oknie
     * @param h wysokosc w oknie
     */
    public OptionComboBox(int x, int y, int w, int h)
    {
        box = new JComboBox();
        box.setBounds(x,y,w,h);
        this.set_strings();
    }
    /**
     * Metoda dodajaca napisy do JComboBox
     */
    protected void set_strings()
    {
        String[] s = {"Name","Count/Area", "Latitude", "Longitude", "Above sea level"};
        this.strings=s;
        box.addItem("Select");
        for(int i=0;i<5;i++)
        {
            box.addItem(strings[i]);
        }
        box.setSelectedItem("Select");
    }
    /**
     * Metoda zwracajaca aktualny napis w JComboBox
     * @return rodzaj obiektu
     */
    public String get_currnet_item()
    {
        return  (String)box.getSelectedItem();
    }

}
