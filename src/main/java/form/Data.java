package form;

public class Data {
    private String password;
    private String userName;
    private String hostName;
    private String portNumber;
    private String pathDirServer;
    private String pathFileLocal;

    public Data() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(final String hostName) {
        this.hostName = hostName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(final String portNumber) {
        this.portNumber = portNumber;
    }

    public String getPathDirServer() {
        return pathDirServer;
    }

    public void setPathDirServer(final String pathDirServer) {
        this.pathDirServer = pathDirServer;
    }

    public String getPathFileLocal() {
        return pathFileLocal;
    }

    public void setPathFileLocal(final String pathFileLocal) {
        this.pathFileLocal = pathFileLocal;
    }
}