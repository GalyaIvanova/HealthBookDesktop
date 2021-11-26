package storage;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class PersistentStorage implements IStorage {
    //file storage
    private static final int BUFFER_SIZE = 4096; // 4KB

    @Override
    public String getAccessToken() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            // create a reader
            FileInputStream fis = new FileInputStream(new File("storage.dat"));

            // specify UTF_8 characer encoding
            InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);

            // read one byte at a time
            int ch;
            ch = reader.read();
            ch = reader.read();
            while ((ch = reader.read()) != -1) {
                stringBuffer.append((char) ch);
            }
            // close the reader
            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return String.valueOf(stringBuffer);
    }

    @Override
    public void setAccessToken(String accessToken) {
        try {
            DataOutputStream dout = new DataOutputStream(new FileOutputStream("storage.dat"));
            // System.out.println("Probably something is written somewhere and the accepted token is \n"+accessToken);
            dout.writeUTF(accessToken);
            dout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ClearStorage() {
        try {
            DataOutputStream dout = new DataOutputStream(new FileOutputStream("storage.dat"));
            dout.writeUTF("");
            dout.flush();
            dout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
