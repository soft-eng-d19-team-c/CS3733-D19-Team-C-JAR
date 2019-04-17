package base;

public enum EnumScreenTypeAPI {
    DASHBOARD("/views/universalDashboard.fxml"),
    APIMain("/views/APIMain.fxml"),
    APIOrder("/views/APIOrder.fxml"),
    APIProvide("/views/APIProvide.fxml");

    protected String path;

    EnumScreenTypeAPI(String str) {
        this.path = str;
    }

    public String getPath() {
        return path;
    }
}
