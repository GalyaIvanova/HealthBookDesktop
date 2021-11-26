package storage;

public interface IStorage {
    String getAccessToken();

    void setAccessToken(String accessToken);

    void ClearStorage();
}
