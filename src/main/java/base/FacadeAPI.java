package base;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;


/*
        TODO

        on logout remember to clear the history stack
 */


/**
 * This is the FacadeAPI class that is used for passing data between controllers
 * as well as switching between screens.
 * @author Ryan LaMarche
 */
public final class FacadeAPI {
    private HashMap<EnumScreenTypeAPI, Parent> screens;
    private HashMap<EnumScreenTypeAPI, Controller> controllers;
    private Scene primaryScene;
    private HashMap<String, Object> data;
    private Stack<EnumScreenTypeAPI> history;
    private EnumScreenTypeAPI prevType;

    /**
     * Creates a FacadeAPI.
     * @author Ryan LaMarche
     * @param s
     */
    public FacadeAPI(Scene s) {
        this.screens = new HashMap<>();
        this.controllers= new HashMap<>();
        this.data = new HashMap<>();
        this.primaryScene = s;
        this.history = new Stack<>();
        this.prevType = EnumScreenTypeAPI.DASHBOARD;
    }

    /**
     * Sets screen.
     * @author Ryan LaMarche
     * @param type
     */
    public void setScreen(EnumScreenTypeAPI type) {
        setScreen(type, null, true);
    }

    /**
     * Sets screen with data.
     * @author Ryan LaMarche
     * @param type
     * @param data
     */
    public void setScreen(EnumScreenTypeAPI type, HashMap<String, Object> data) {
        setScreen(type, data, true);
    }

    /**
     * Sets screen with data and the option to not add the screen to the history stack.
     * @author Ryan LaMarche
     * @param type
     * @param data
     * @param addToHistory
     */
    public void setScreen(EnumScreenTypeAPI type, HashMap<String, Object> data, boolean addToHistory) {
        this.primaryScene.setCursor(Cursor.DEFAULT);
        this.data = data;
        if (addToHistory) {
            this.history.push(prevType);
        }
        this.prevType = type;
        if (this.screens.containsKey(type)) {
            loadCachedScreen(type);
        } else {
            loadNewScreen(type);
        }
        if (type == EnumScreenTypeAPI.DASHBOARD) {
            clearHistory();
        }
    }

    /**
     * Loads the Parent of a new FXML page from disk.
     * @param type
     * @author Ryan LaMarche
     */
    private void loadNewScreen(EnumScreenTypeAPI type) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(type.getPath()));
            Parent newRoot = loader.load();
            this.screens.put(type, newRoot);
            this.controllers.put(type, loader.getController());
            this.primaryScene.setRoot(newRoot);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Loads the Parent of a cached FXML page and initializes its TeamC.controller.
     * @param type
     * @author Ryan LaMarche
     */
    private void loadCachedScreen(EnumScreenTypeAPI type) {
        this.primaryScene.setRoot(this.screens.get(type));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(type.getPath()));
        this.controllers.get(type).init(getClass().getResource(type.getPath()), loader.getResources());
    }

    /**
     * Returns to previous screen.
     * @author Ryan LaMarche
     */
    public void goBack() {
        goBack(null);
    }

    /**
     * Returns to previous screen with data.
     * @author Ryan LaMarche
     * @param data
     */
    public void goBack(HashMap<String, Object> data) {
        setScreen(this.history.pop(), data, false);
    }

    /**
     * Helper function to clear the history Stack of the go back button.
     * @author Ryan LaMarche
     */
    private void clearHistory() {
        this.history.clear();
        this.history.push(EnumScreenTypeAPI.DASHBOARD);
    }

    /**
     * Getter for data passed between controllers using a hash map.
     * @author Ryan LaMarche
     * @param key the key in the hash mapped that was passed to switchScreen().
     * @return Object that must be cast to the correct type after you get it from the FacadeAPI.
     */
    public Object getData(String key) {
        if (this.data == null) {
            System.err.println("There is no data set in the FacadeAPI.");
            return null;
        }
        if (this.data.containsKey(key)) {
            return this.data.get(key);
        } else {
            System.err.println("Looking for Key: " + key + ", but no member with that name was found in keyset: " + this.data.keySet());
            return null;
        }
    }

}
