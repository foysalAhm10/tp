package seedu.address.commons.core;

/**
 * Supported application themes.
 */
public enum Theme {
    LIGHT("Light", "view/AddressTheme.css"),
    DARK("Dark", "view/DarkTheme.css");

    private final String displayName;
    private final String mainStylesheetPath;

    Theme(String displayName, String mainStylesheetPath) {
        this.displayName = displayName;
        this.mainStylesheetPath = mainStylesheetPath;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMainStylesheetPath() {
        return mainStylesheetPath;
    }
}
