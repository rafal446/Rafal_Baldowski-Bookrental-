package driver;

public enum BrowserType {

    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge");

    private final String browser;

    BrowserType(String browser) {
        this.browser = browser;
    }
}
