package application.api;

public class Version {
    private final String currentVersion;

    public Version(String version) {
        this.currentVersion = version;
    }

    public String getVersion() {
        return currentVersion;
    }
}
