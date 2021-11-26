package storage;

public class LocalStorage implements IStorage {
    private String accessToken;

    //in Memory storage
    @Override
    public String getAccessToken() {
        return this.accessToken;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void ClearStorage() {

    }
}
