import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import Airline.*;

public class ImpAuthService extends AuthServicePOA {

    private Map<String, String> adminCredentials;
    private Map<String, String> clientCredentials;

    protected ImpAuthService() {
        super();
        adminCredentials = new HashMap<>();
        clientCredentials = new HashMap<>();

        adminCredentials.put("admin", "admin");
        clientCredentials.put("client", "client");
    }

    @Override
    public boolean Authothification(String username, String password) {
        if (adminCredentials.containsKey(username)) {
            return adminCredentials.get(username).equals(password);
        } else if (clientCredentials.containsKey(username)) {
            return clientCredentials.get(username).equals(password);
        } else {
            return false;
        }
    }
}
