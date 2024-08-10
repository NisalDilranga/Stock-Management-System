
package View;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JButton;
import javax.swing.BorderFactory;



public class MyButton extends JButton{

    private boolean over;
    private  Color color;
    private Color colorover;
    private  Color colorclick;
    private Color bordercolor;
    private  int radius  = 0;
    private int fontSize = 16; // Default font size

  

    
    public MyButton() {
        
        //init color
        setColor(Color.WHITE);
        colorover = new Color(18, 149, 33);
        colorclick = new Color(214, 223, 27);
        bordercolor = new Color(255, 255, 255 );
         // Set an empty border to hide it
     
        
        
       setContentAreaFilled(false);
       // add event for mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorover);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                over = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
            
        });
        
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public Color getColorover() {
        return colorover;
    }

    public void setColorover(Color colorover) {
        this.colorover = colorover;
    }

    public Color getColorclick() {
        return colorclick;
    }

    public void setColorclick(Color colorclick) {
        this.colorclick = colorclick;
    }

    public Color getBordercolor() {
        return bordercolor;
    }

    public void setBordercolor(Color bordercolor) {
        this.bordercolor = bordercolor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
//    public int getFontSize() {
//        return fontSize;
//    }
//
//    public void setFontSize(int fontSize) {
//        int oldFontSize = this.fontSize;
//        this.fontSize = fontSize;
//        setFont(new Font("Arial", Font.PLAIN, fontSize)); // You can adjust the font name and style here
//        
//    }

  

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // pint border
        g2.setColor(bordercolor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2.setColor(getBackground());
                // border set 2pix
                g2.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, radius, radius);
                
        super.paintComponent(g); 
    }
}
