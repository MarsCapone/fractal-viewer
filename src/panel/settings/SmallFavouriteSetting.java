package panel.settings;

import panel.SmallPanel;
import calculation.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class SmallFavouriteSetting extends JPanel {

    private static JButton lastClicked;
    private static JPanel favouriteSpace;
    private static SmallPanel smallPanel;

    /**
     * Create a panel to save specific Julia set generations to come back to later.
     *
     * @param smallPanel The Julia panel to save points from.
     */
    public SmallFavouriteSetting(final SmallPanel smallPanel) {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        favouriteSpace = new JPanel(new FlowLayout());
        this.smallPanel = smallPanel;

        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.PAGE_AXIS));

        // want to be able to scroll through favourites.
        final JScrollPane scroller = new JScrollPane(favouriteSpace, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setPreferredSize(new Dimension(300, 100));

        final JButton addFavourite = new JButton("Add Favourite");
        JButton delete = new JButton("Delete");
        JButton clear = new JButton("Clear");

        // the button that will be pressed to add a new favourite.
        addFavourite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                // get a scaled down version of the current image.
                Image miniCurrentImage = smallPanel.getScaledImage();

                addNewFavourite(miniCurrentImage);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    favouriteSpace.remove(lastClicked);
                } catch (NullPointerException e) {
                    favouriteSpace.remove(favouriteSpace.getComponentCount() - 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Cannot delete anything. The favourites list is empty.");
                }
                favouriteSpace.revalidate();
                favouriteSpace.repaint();
                scroller.revalidate();
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                favouriteSpace.removeAll();
                favouriteSpace.repaint();
                favouriteSpace.revalidate();
            }
        });

        buttonContainer.add(addFavourite);
        buttonContainer.add(delete);
        buttonContainer.add(clear);

        add(scroller);
        add(buttonContainer);
    }

    public static JButton getFavouriteButton(Image icon) {
        // get the current important data regarding the current generation in the Julia panel.
        final Complex currentJuliaConstant = smallPanel.getConstant();
        final HashMap<String, Double> currentJuliaAxes = smallPanel.getAxes();

        // create new button that has the scaled down image as a background.
        final JButton fav = new JButton(new ImageIcon(icon));
        fav.setBorderPainted(false);
        fav.setBorder(null);

        // when clicked this button will show the saved Julia image on the main display.
        fav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                lastClicked = fav;
                smallPanel.setConstant(currentJuliaConstant);
                smallPanel.resetAxes(currentJuliaAxes);
                smallPanel.paintImage();
                smallPanel.repaint();
            }
        });

        return fav;
    }

    public static void addNewFavourite(Image image) {
        JButton newButton = getFavouriteButton(image);
        favouriteSpace.add(newButton);
        favouriteSpace.revalidate();
    }

}
