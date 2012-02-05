package serial;

import gnu.io.SerialPort;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles sending commands from the serial port
 * @author Parham
 */
public class CommandHandler {

    private SerialPort mPort;
    private OutputStream mOutStream;

    public CommandHandler(SerialPort port){
        mPort = port;
    }

    public void sendCommand(CameraCommand command){
        try {
            if (mOutStream == null){
                mOutStream = mPort.getOutputStream();
            }
            //Start response thread
            SerialResponse response = new SerialResponse(mPort, command.getExpectedReturnSize());
            Thread t = new Thread(response);
            t.start();
            mOutStream.write(command.getCommand());
        } catch (IOException ex) {
            Logger.getLogger(CommandHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
