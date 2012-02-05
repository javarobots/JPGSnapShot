package serial;

import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parham
 */
public class SerialResponse implements Runnable {

    private SerialPort mPort;
    private boolean mStop = false;
    private int mExpectedResponse;

    public SerialResponse(SerialPort port, int expectedResponseSize){
        mPort = port;
        mExpectedResponse = expectedResponseSize;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = null;
            byte[] buffer = new byte[96];
            int bytesRead;
            int totalBytes = 0;
            inputStream = mPort.getInputStream();
            while (!mStop){
                bytesRead = inputStream.read(buffer);
                totalBytes += bytesRead;
                if (bytesRead > 0){
                    System.out.print(new String(buffer,0,bytesRead));
                    if (totalBytes == mExpectedResponse){
                        System.out.println("Stoping response reader.");
                        mStop = true;
                    }
                }
            }
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(SerialResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
