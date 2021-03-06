package de.dfki.vsm;

import de.dfki.vsm.util.ios.ResourceLoader;
import de.dfki.vsm.util.log.LOGDefaultLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

//~--- JDK imports ------------------------------------------------------------

/**
 * @author Gregor Mehlmann
 * @author Patrick Gebhard
 * @author Lenny Händler
 * <p>
 * Desktop VSM configurations
 */
public final class PreferencesDesktop extends Preferences {

    //////////////////////////////////////////////////////////////////////////////
    // SYSTEM PROPERTIES
    //////////////////////////////////////////////////////////////////////////////

    public static final String sSAMPLE_PROJECTS = "res" + System.getProperty("file.separator") + "prj";
    public static final String sTUTORIALS_PROJECTS = "res" + System.getProperty("file.separator") + "tutorials";
    //////////////////////////////////////////////////////////////////////////////
    // NODE COLORS
    //////////////////////////////////////////////////////////////////////////////
    public static final Color sBASIC_NODE_COLOR = new Color(125, 125, 125);
    public static final Color sHISTORY_NODE_COLOR = new Color(255, 255, 255);
    public static final Color sSUPER_NODE_COLOR = new Color(125, 125, 125);
    public static final Color sSELECTED_NODE_COLOR = new Color(211, 211, 211);

    //////////////////////////////////////////////////////////////////////////////
    // EDGE COLORS
    //////////////////////////////////////////////////////////////////////////////
    public static final Color sFEDGE_COLOR = new Color(35, 77, 103);  //BLUE
    public static final Color sEEDGE_COLOR = new Color(130, 125, 120);  //GRAY
    public static final Color sTEDGE_COLOR = new Color(84, 63, 29);  //BROWN
    public static final Color sCEDGE_COLOR = new Color(152, 142, 52);   //YELLOW
    public static final Color sPEDGE_COLOR = new Color(42, 103, 35);   //GREEN
    public static final Color sIEDGE_COLOR = new Color(152, 52, 52);    //RED

    //////////////////////////////////////////////////////////////////////////////
    // VISUALIZATION COLORS
    //////////////////////////////////////////////////////////////////////////////
    public static final Color sHIGHLIGHT_COLOR = new Color(211, 211, 211);
    public static final Color sTRANSLUCENT_HIGHLIGHT_COLOR = new Color(111, 251, 211, 100);    // Do not change opacity!
    public static final Color sTRANSLUCENT_RED_COLOR = new Color(246, 0, 0, 100);        // Do not change opacity!
    public static final Color sTRANSLUCENT_BLUE_COLOR = new Color(0, 0, 246, 100);        // Do not change opacity!
    public static final Color sTRANSLUCENT_GREEN_COLOR = new Color(0, 246, 0, 100);        // Do not change opacity!
    public static final Color sTRANSLUCENT_YELLOW_COLOR = new Color(0, 246, 246, 100);      // Do not change opacity!
    public static final Color sCOMMENT_BADGE_COLOR = new Color(246, 231, 191, 128);
    public static final Color sSTART_SIGN_COLOR = new Color(181, 45, 13);
    public static final Color sMESSAGE_COLOR = new Color(181, 45, 13);
    public static final Color sHIGHLIGHT_SCENE_COLOR = Color.YELLOW;
    //////////////////////////////////////////////////////////////////////////////
    // RECENT FILES
    //////////////////////////////////////////////////////////////////////////////
    public static final int sMAX_RECENT_FILE_COUNT = 8;
    public static final int sMAX_RECENT_PROJECTS = 8;
    public static final ArrayList<Integer> sDYNAMIC_KEYS = new ArrayList<>(Arrays.asList(KeyEvent.VK_1,
            KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4,
            KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7,
            KeyEvent.VK_8, KeyEvent.VK_9));
    //////////////////////////////////////////////////////////////////////////////
    // FILE RESSOURCES
    //////////////////////////////////////////////////////////////////////////////
    public static final URL sABOUT_FILE = SceneMaker3.class.getResource("/res/doc/about.html");
    public static final URL sHELP_FILE = SceneMaker3.class.getResource("/res/doc/index.html");
    //////////////////////////////////////////////////////////////////////////////
    // FONT DATA
    //////////////////////////////////////////////////////////////////////////////
    public static final String[] sFONT_FAMILY_LIST = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    public static final Integer[] sFONT_SIZE_LIST = {6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};
    // public static final int sFONT_SIZE_LIST[] = {6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};
    //////////////////////////////////////////////////////////////////////////////
    // IMAGE RESSOURCES
    //////////////////////////////////////////////////////////////////////////////
    public static final ImageIcon ICON_SCENEMAKER_LOGO = ResourceLoader.loadImageIcon("/res/img/smlogo.png");
    public static final ImageIcon ICON_SCENEMAKER_DOC = ResourceLoader.loadImageIcon("/res/img/docicon.png");
    public static final ImageIcon ICON_SHOW_GRID = ResourceLoader.loadImageIcon("/res/img/grid.png");
    public static final ImageIcon ICON_VISUALISATION = ResourceLoader.loadImageIcon("/res/img/visualisation.png");
    //SUPERNODE
    public static final ImageIcon ICON_SUPERNODE_STANDARD = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/SUPERNODE_ENTRY.png");
    public static final ImageIcon ICON_SUPERNODE_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/SUPERNODE_ENTRY_BLUE.png");
    public static final ImageIcon ICON_SUPERNODE_DRAGGING = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/SUPERNODE_ENTRY_SMALL.png");
    //BASIC NODE
    public static final ImageIcon ICON_BASICNODE_STANDARD = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/BASICNODE_ENTRY.png");
    public static final ImageIcon ICON_BASICNODE_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/BASICNODE_ENTRY_BLUE.png");
    public static final ImageIcon ICON_BASICNODE_DRAGGING = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/BASICNODE_ENTRY_SMALL.png");
    //COMMENT
    public static final ImageIcon ICON_COMMENT_ENTRY_STANDARD = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/COMMENT_ENTRY.png");
    public static final ImageIcon ICON_COMMENT_ENTRY_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/COMMENT_ENTRY_BLUE.png");
    public static final ImageIcon ICON_COMMENT_ENTRY_DRAGGING = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/COMMENT_ENTRY_SMALL.png");
    //EPSILON EDGE
    public static final ImageIcon ICON_EEDGE_ENTRY_STANDARD = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/EEDGE_ENTRY.png");
    public static final ImageIcon ICON_EEDGE_ENTRY_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/EEDGE_ENTRY_BLUE.png");
    public static final ImageIcon ICON_EEDGE_ENTRY_DRAGGING = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/EEDGE_ENTRY_SMALL.png");
    //TIMEOUT EDGE
    public static final ImageIcon ICON_TEDGE_ENTRY_STANDARD = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/TEDGE_ENTRY.png");
    public static final ImageIcon ICON_TEDGE_ENTRY_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/TEDGE_ENTRY_BLUE.png");
    public static final ImageIcon ICON_TEDGE_ENTRY_DRAGGING = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/TEDGE_ENTRY_SMALL.png");
    //PROBABILISTIC EDGE
    public static final ImageIcon ICON_PEDGE_ENTRY_STANDARD = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/PEDGE_ENTRY.png");
    public static final ImageIcon ICON_PEDGE_ENTRY_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/PEDGE_ENTRY_BLUE.png");
    public static final ImageIcon ICON_PEDGE_ENTRY_DRAGGING = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/PEDGE_ENTRY_SMALL.png");
    //CONDITIONAL EDGE
    public static final ImageIcon ICON_CEDGE_ENTRY_STANDARD = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/CEDGE_ENTRY.png");
    public static final ImageIcon ICON_CEDGE_ENTRY_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/CEDGE_ENTRY_BLUE.png");
    public static final ImageIcon ICON_CEDGE_ENTRY_DRAGGING = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/CEDGE_ENTRY_SMALL.png");
    //INTERRUPTIVE EDGE
    public static final ImageIcon ICON_IEDGE_ENTRY_STANDARD = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/IEDGE_ENTRY.png");
    public static final ImageIcon ICON_IEDGE_ENTRY_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/IEDGE_ENTRY_BLUE.png");
    public static final ImageIcon ICON_IEDGE_ENTRY_DRAGGING = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/IEDGE_ENTRY_SMALL.png");
    //FORK EDGE
    public static final ImageIcon ICON_FEDGE_ENTRY_STANDARD = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/FEDGE_ENTRY.png");
    public static final ImageIcon ICON_FEDGE_ENTRY_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/FEDGE_ENTRY_BLUE.png");
    public static final ImageIcon ICON_FEDGE_ENTRY_DRAGGING = ResourceLoader.loadImageIcon("/res/img/workspace_toolbar/FEDGE_ENTRY_SMALL.png");
    //
    public static final ImageIcon ICON_ROOT_FOLDER = ResourceLoader.loadImageIcon("/res/img/elementtree/ROOT_FOLDER.png");
    public static final ImageIcon ICON_SCENE_FOLDER = ResourceLoader.loadImageIcon("/res/img/elementtree/SCENE_FOLDER.png");
    public static final ImageIcon ICON_BASIC_FOLDER = ResourceLoader.loadImageIcon("/res/img/elementtree/BASIC_FOLDER.png");
    public static final ImageIcon ICON_RADIOBUTTON_UNSELECTED = ResourceLoader.loadImageIcon("/res/img/elementtree/RADIOBUTTON_UNSELECTED.png");
    public static final ImageIcon ICON_RADIOBUTTON_SELECTED = ResourceLoader.loadImageIcon("/res/img/elementtree/RADIOBUTTON_SELECTED.png");
    public static final ImageIcon ICON_FUNCTION_ENTRY = ResourceLoader.loadImageIcon("/res/img/elementtree/FUNCTION_ENTRY.png");
    public static final ImageIcon ICON_FUNCTION_ERROR_ENTRY = ResourceLoader.loadImageIcon("/res/img/elementtree/FUNCTION_ERROR_ENTRY.png");
    //MORE ICON
    public static final ImageIcon ICON_MORE_STANDARD = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/more.png");
    public static final ImageIcon ICON_MORE_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/more_blue.png");
    //LESS ICON
    public static final ImageIcon ICON_LESS_STANDARD = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/less.png");
    public static final ImageIcon ICON_LESS_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/less_blue.png");
    //PLUS ICON
    public static final ImageIcon ICON_PLUS_STANDARD = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/add.png");
    public static final ImageIcon ICON_PLUS_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/add_blue.png");
    public static final ImageIcon ICON_PLUS_DISABLED = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/add_disabled.png");
    //MINUS ICON
    public static final ImageIcon ICON_MINUS_STANDARD = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/remove.png");
    public static final ImageIcon ICON_MINUS_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/remove_blue.png");
    public static final ImageIcon ICON_MINUS_DISABLED = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/remove_disabled.png");
    //EDIT ICON
    public static final ImageIcon ICON_EDIT_STANDARD = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/edit.png");
    public static final ImageIcon ICON_EDIT_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/edit_blue.png");
    //UP ICON
    public static final ImageIcon ICON_UP_STANDARD = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/up_20.png");
    public static final ImageIcon ICON_UP_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/up_20_blue.png");
    public static final ImageIcon ICON_UP_DISABLED = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/up_20_disabled.png");
    //DOWN ICON
    public static final ImageIcon ICON_DOWN_STANDARD = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/down.png");
    public static final ImageIcon ICON_DOWN_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/down_blue.png");
    public static final ImageIcon ICON_DOWN_DISABLED = ResourceLoader.loadImageIcon("/res/img/toolbar_icons/down_disabled.png");
    //CANCEL ICONS
    public static final ImageIcon ICON_CANCEL_STANDARD = ResourceLoader.loadImageIcon("/res/img/cancel_icon_gray.png");
    public static final ImageIcon ICON_CANCEL_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/cancel_icon_blue.png");
    public static final ImageIcon ICON_CANCEL_STANDARD_TINY = ResourceLoader.loadImageIcon("/res/img/cancel_icon_gray_tiny.png");
    public static final ImageIcon ICON_CANCEL_ROLLOVER_TINY = ResourceLoader.loadImageIcon("/res/img/cancel_icon_blue_tiny.png");
    //OK ICONS
    public static final ImageIcon ICON_OK_STANDARD = ResourceLoader.loadImageIcon("/res/img/ok_icon_gray.png");
    public static final ImageIcon ICON_OK_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/ok_icon_blue.png");
    //BACK ICONS
    public static final ImageIcon ICON_PREVIOUS_STANDARD = ResourceLoader.loadImageIcon("/res/img/back_icon_gray.png");
    public static final ImageIcon ICON_PREVIOUS_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/back_icon_blue.png");
    //NEXT ICONS
    public static final ImageIcon ICON_NEXT_STANDARD = ResourceLoader.loadImageIcon("/res/img/next_icon_gray.png");
    public static final ImageIcon ICON_NEXT_ROLLOVER = ResourceLoader.loadImageIcon("/res/img/next_icon_blue.png");
    //BACKGROUND WELCOME
    public static final Image BACKGROUND_IMAGE = ResourceLoader.loadImageIcon("/res/img/icon_big.png").getImage();   // Background for the welcome screen
    //////////////////////////////////////////////////////////////////////////////
    // APPEARANCE CONFIGURATION
    //////////////////////////////////////////////////////////////////////////////
    public static boolean sSHOW_ELEMENTS = true;
    public static boolean sSHOW_ELEMENT_PROPERTIES = true;
    public static boolean sSHOW_SCENEEDITOR = true;
    public static boolean sSHOW_SCENEFLOWEDITOR = true;
    public static float sSCENEFLOW_SCENE_EDITOR_RATIO = 0.75f;
    public static boolean sSHOW_GESTURES = true;
    // The Screen Size
    public static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static int SCREEN_HORIZONTAL = SCREEN_SIZE.width;
    public static int SCREEN_VERTICAL = SCREEN_SIZE.height;
    //Components dimensions
    public static Dimension SF_PALETTEITEM_SIZE = new Dimension(61, 65);

    public static synchronized void configure() {
        try {

            // Mac/Apple Settings
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Visual SceneMaker");

            // Use system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
            UIManager.put("TabbedPane.background", new Color(100, 100, 100));
            UIManager.put("TabbedPane.contentAreaColor", new Color(100, 100, 100));
            UIManager.put("TabbedPane.tabAreaBackground", new Color(100, 100, 100));

            // paint a nice doc icon when os is mac
            if (isMac()) {
                final Class appClass = Class.forName("com.apple.eawt.Application");
                // Get the application and the method to set the dock icon
                final Object app = appClass.getMethod("getApplication", new Class[]{}).invoke(null);
                final Method setDockIconImage = appClass.getMethod("setDockIconImage", Image.class);
                // Set the dock icon to the logo of Visual Scene Maker 3
                setDockIconImage.invoke(app, ICON_SCENEMAKER_DOC.getImage());
            }
        } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException exc) {
            LOGDefaultLogger.getInstance().failure("Error: " + exc.getMessage());
        }
    }

    private static synchronized void init() {

        // load visual appearance settings
        PreferencesDesktop.sSHOW_ELEMENTS = Boolean.valueOf(sPROPERTIES.getProperty("showelements"));
        PreferencesDesktop.sSHOW_ELEMENT_PROPERTIES = Boolean.valueOf(sPROPERTIES.getProperty("showelementproperties"));
        PreferencesDesktop.sSHOW_SCENEEDITOR = Boolean.valueOf(sPROPERTIES.getProperty("showsceneeditor"));
        PreferencesDesktop.sSHOW_SCENEFLOWEDITOR = Boolean.valueOf(sPROPERTIES.getProperty("showscenefloweditor"));
        // sSCENEFLOW_SCENE_EDITOR_RATIO = Float.valueOf(sPROPERTIES.getProperty("sceneflow_sceneeditor_ratio"));
        PreferencesDesktop.sSHOW_GESTURES = Boolean.valueOf(sPROPERTIES.getProperty("showgestures"));
    }

    public static synchronized void save() {
        try {
            try (FileOutputStream fileOutputStream = new FileOutputStream(sCONFIG_FILE)) {
                sPROPERTIES.storeToXML(fileOutputStream, "Properties for the Sceneflow Editor", "ISO8859_1");
            }
        } catch (IOException e) {
            LOGDefaultLogger.getInstance().failure("Error: " + e.getMessage());
        }
        init();
    }

    public static synchronized void load() {
        parseConfigFile();
        init();
    }

    // Check if we are on a WINDOWS system
    private static synchronized boolean isWindows() {
        final String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("win"));
    }

    // Check if we are on a MAC system
    private static synchronized boolean isMac() {
        final String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("mac"));
    }

    // Check if we are on a UNIX system
    private static synchronized boolean isUnix() {
        final String os = System.getProperty("os.name").toLowerCase();
        return ((os.contains("nix")) || (os.contains("nux")));
    }

}
