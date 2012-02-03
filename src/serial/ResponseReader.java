package serial;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 *
 * @author Parham
 */
public class ResponseReader implements SerialPortEventListener {

    @Override
    public void serialEvent(SerialPortEvent spe) {
        System.out.println("Serial port event occured.");
    }
}
