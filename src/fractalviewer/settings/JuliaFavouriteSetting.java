package fractalviewer.settings;

import fractalviewer.extras.Complex;
import fractalviewer.panels.JuliaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;


public class JuliaFavouriteSetting extends JPanel {

    private JButton lastClicked;

    /**
     * Create a panel to save specific Julia set generations to come back to later.
     * @param juliaPanel The Julia panel to save points from.
     */
    public JuliaFavouriteSetting(final JuliaPanel juliaPanel) {
        setLayout(new FlowLayout());

        
        final JPanel favouriteSpace = new JPanel(new FlowLayout());

        JButton addFavourite = new JButton("Add Favourite");
        JButton delete = new JButton("Delete");
        JButton clear = new JButton("Clear");

        // the button that will be pressed to add a new favourite.
        addFavourite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BufferedImage currentJuliaImage = juliaPanel.getImage();

                // get a scaled down version of the current image.
                Image miniCurrentImage = currentJuliaImage.getScaledInstance(70, 70, 10);

                // get the current important data regarding the current generation in the Julia panel.
                final Complex currentJuliaConstant = juliaPanel.getConstant();
                final HashMap currentJuliaAxes = juliaPanel.getAxes();

                // create new button that has the scaled down image as a background.
                final JButton fav = new JButton(new ImageIcon(miniCurrentImage));
                fav.setBorderPainted(false);

                // when clicked this button will show the saved Julia image on the main display.
                fav.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        lastClicked = fav;
                        juliaPanel.setConstant(currentJuliaConstant);
                        juliaPanel.resetAxes(currentJuliaAxes);
                        juliaPanel.paintImage();
                        juliaPanel.repaint();
                    }
                });

                favouriteSpace.add(fav);
                revalidate();
            }
        });
        
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    favouriteSpace.remove(lastClicked);
                } catch (NullPointerException e) {
                    favouriteSpace.remove(favouriteSpace.getComponentCount()-1);
                }
                favouriteSpace.revalidate();
            }
        });
        
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                favouriteSpace.removeAll();
                favouriteSpace.revalidate();
            }
        });

        // want to be able to scroll through favourites.
        JScrollPane scroller = new JScrollPane(favouriteSpace);
        scroller.setPreferredSize(new Dimension(200, 80));
        
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.PAGE_AXIS));
        buttonContainer.add(addFavourite);
        buttonContainer.add(delete);
        buttonContainer.add(clear);

        add(scroller);
        add(buttonContainer);
    }
}