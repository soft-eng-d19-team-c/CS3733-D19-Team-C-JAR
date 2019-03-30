package base;

public enum EnumScreenType {
    NODETABLE("/views/prototypeNodeTable.fxml"),
    MAP("/views/prototypeMap.fxml"),
    NODEEDIT("/views/prototypeNodeEdit.fxml"),
    DOWNLOADCSV("/views/downloadscreen.fxml"),
    DASHBOARD("/views/dashboard.fxml"),
    SEARCH("/views/autocompleteSearchBar.fxml"),
    LOGIN("/views/login screen example.fxml"),
    REQUESTSERVICE("/views/requestServiceView.fxml");

    protected String path;

    EnumScreenType(String str) {
        this.path = str;
    }

    public String getPath() {
        return path;
    }
}
