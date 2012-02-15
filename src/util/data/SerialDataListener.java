/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.data;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parham
 */
public class SerialDataListener implements SerialPortEventListener {

    private InputStream mInStream;
    private byte[] buffer = new byte[1024];

    public SerialDataListener(InputStream inStream){
        mInStream = inStream;
    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        try {
            int bytesRead = mInStream.read(buffer);
            while(bytesRead > 0){
                System.out.print(new String(buffer,0,bytesRead));
                bytesRead = mInStream.read(buffer);
            }
        } catch (IOException ex) {
            Logger.getLogger(SerialDataListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
