package edu.usfca.vas.layout;

import edu.usfca.vas.layout.Views.*;
import edu.usfca.xj.appkit.frame.XJWindow;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Thomas Schweich on 1/24/2017.
 * The class representing the main window of the program, including the left-sidebar and the capability to add Containers
 * to the LeftSideBar. It extends XJWindow so that window.WindowAbstract can extend it, thus maintaining support
 * for the XJ library, but this is something of an idiosyncrasy -- only the model view is actually generated by subclassing
 * this class, while the rest of the views will be instantiated within this class.
 * I'm unaware of any better way to approach this.
 */
public abstract class MainWindow extends XJWindow {
    private LeftSideBar leftSideBar;
    private Container subFrame;

    /**
     * Creates a sub-JPanel within the main JFrame window, and adds the LeftSideBar and its contents to it
     */
    public MainWindow() {
        subFrame = new JPanel(new BorderLayout());
        subFrame.setPreferredSize(getJFrame().getPreferredSize());
        getJFrame().add(subFrame);
        leftSideBar = new LeftSideBar(JTabbedPane.LEFT);
        getJFrame().add(leftSideBar);
        addSideTab(new JPanel(), "Logo", false, 70, 25, false);
        addSideTab(subFrame, "Model", 25, 25);
        // The below lines will be replaced with additions of the actual new views
        addSideTab(new AnalyticsView().getPanel(), "Analytics", 25, 25);

        JPanel mapPanel = new JPanel(new BorderLayout());
        MapsView map = MapsView.makeMap();
        mapPanel.add(map, BorderLayout.CENTER);
        mapPanel.setSize(700, 500);
        //mapPanel.setLocation(null);
        mapPanel.setVisible(true);
        //mapPanel.add(MapsView.makeMap());
        System.out.println("Here!!!");
        addSideTab(mapPanel, "Map", 25, 25);
        MapTest tester = new MapTest(map);
        tester.start();
        //addSideTab(new JPanel(), "Map", 25, 25);
        leftSideBar.setSelectedIndex(1);
        leftSideBar.setVisible(true);
    }

    public void addView() {

    }

    /**
     * Adds a container (such as a JPanel) whose corresponding image icon can be found under the given name in
     * settings.json to the sidebar of main window
     * @param tab The Container to display upon clicking the tab
     * @param name The name to display under the tab and the string under which to look up the image in sidebar.json
     */
    public void addSideTab(Container tab, String name, int width, int height) {
        leftSideBar.addTab(tab, name, width, height);
    }

    /**
     * Adds a Container (such as a JPanel) whose image can be found under the given name in settings.json to the sidebar
     * of main window, displaying its name if displayText is set to true, and displaying the image at the given
     * width and height dimensions
     * @param tab The Container to display upon clicking the tab
     * @param name The name to display under the tab if displayText is true and the string under which to look up the
     *             image in sidebar.json
     */
    public void addSideTab(Container tab, String name, boolean displayText, int width, int height) {
    	addSideTab(tab, name, displayText, width, height, true);
    }

    public void addSideTab(Container tab, String name, boolean displayText, int width, int height, Boolean TabControl) {
        if(displayText) {
            leftSideBar.addTab(tab, name, width, height);
        } else {
        	leftSideBar.addTab(tab, name, false, width, height, TabControl);
        }
        	
        }
    
    /**
     * Instead of returning the base JFrame, the content pane is now set to a JPanel within the LeftSideBar for
     * creation of model view.
     * @return The pane to place model view in
     */
    @Override
    public Container getContentPane() {
        return subFrame;
    }
}