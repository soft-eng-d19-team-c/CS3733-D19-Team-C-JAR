package base;

public enum EnumScreenType {
    DASHBOARD("/views/universalDashboard.fxml"),
    APIMain("/views/APIMain.fxml"),
    APIOrder("/views/APIOrder.fxml"),
    APIProvide("/views/APIProvide.fxml");

    protected String path;

    EnumScreenType(String str) {
        this.path = str;
    }

    public String getPath() {
        return path;
    }
}
