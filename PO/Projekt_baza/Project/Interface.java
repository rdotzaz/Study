import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


/**
 * Klasa posiada jako pola wszystkie okna potrzebne do obslugi programu.
 * Zawiera rowniez baze obiektow, na ktorych bedzie operowac.
 */
public class Interface extends Const implements ActionListener
{
    protected Base b_of_objects;
    protected MainFrame mainFrame;
    protected AddFrame addFrame;
    protected ExitFrame exitFrame;
    protected ErrorFrame errorFrame;
    protected DeleteFrame deleteFrame;

    public Interface()
    {

    }
    /**
     * @param something nieuzwany parametr, ktory zapobiega uzwyania konstuktora domyslnego (wynika to z problemow przy implementacji)
     */
    public Interface(int something)
    {
        this.b_of_objects = new Base(something);
        this.set_parametrs_window();
    }

    /**
     * stworzenie glownego okna w programie
     */
    public void set_parametrs_window()
    {


        String[] c = {"Name","Count/Area","Latitude","Longitude","a.s.l.","City A", "City B"};
        mainFrame = new MainFrame("Base of objects",b_of_objects);

    }
    public void actionPerformed(ActionEvent e)
    {

    }
}
