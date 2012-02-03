/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public SerialResponse(SerialPort port){
        mPort = port;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        byte[] buffer = new byte[1024];
        int bytesRead;
        try {
            inputStream = mPort.getInputStream();
            while (!mStop){
                bytesRead = inputStream.read(buffer);
                while (bytesRead > 0){
                    System.out.println("Something read");
                    bytesRead = inputStream.read(buffer);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SerialResponse.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(SerialResponse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
