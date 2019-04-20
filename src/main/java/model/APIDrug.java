package model;

public class APIDrug {

    private String title;
    private String description;

    public APIDrug(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return title;
    }
}
