import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class InterfaceButton extends InterfaceFrame
{
    JButton button;
    public InterfaceButton()
    {

    }
}

/**
 * JButton odpowiedzialny za wyswietlanie wynikow w tabeli
 */
class ShowButton extends InterfaceButton
{
    public ShowButton()
    {
        button = new JButton("Show results");
        button.setBounds(50,230,150,50);//50,230,150,50
    }
}


/**
 * JButton dodajacy obiekt do bazy
 */
class AddButton extends InterfaceButton
{
    public AddButton()
    {
        button = new JButton("Add Object");
        button.setBounds(50,110,150,50);
        //50,110,150,50
    }
    public AddButton(String name,int pos_x , int pos_y, int w, int h)
    {
        button = new JButton(name);
        button.setBounds(pos_x,pos_y,w,h);
    }
}

/**
 * JButtton uruchamiajacy okno z ExitFrame lub zamykajacy program
 */
class ExitButton extends InterfaceButton
{
    public ExitButton(String name,int pos_x , int pos_y, int w, int h)
    {
        button = new JButton(name);
        button.setBounds(pos_x,pos_y,w,h);
    }
}

/**
 * JButton odpowiedzialny za uruchomienie sortowanie w liscie obiektow
 */
class SortButton extends InterfaceButton
{
    public SortButton()
    {
        button = new JButton("Sort");
        int pos_x=Const.width/2-150;
        int pos_y=Const.heigth/2-95;
        int w=150;
        int h=35;
        button.setBounds(pos_x,pos_y,w,h);
                //Const.width/2-150,Const.heigth/2-90,Const.width/2+125,Const.heigth/2)
    }
}

/**
 * JButton w oknie ErrorFrame ktory zamyka to okno
 */
class ErrorButton extends InterfaceButton
{
    public ErrorButton(String name,int pos_x , int pos_y, int w, int h)
    {
        button = new JButton(name);
        button.setBounds(pos_x,pos_y,w,h);
    }
}

/**
 * JButton uruchamiajacy okno DeleteFrame lub usuwajacy obiekt z bazy
 */
class DeleteButton extends InterfaceButton
{
    public DeleteButton(String name)
    {
        button = new JButton(name);
        button.setBounds(50,50,150,50);
    }

    public DeleteButton()
    {
        button = new JButton("Delete");
        button.setBounds(0,50,150,50);
    }
}

