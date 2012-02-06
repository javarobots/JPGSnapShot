package serial;

import gnu.io.SerialPort;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.JPGCameraModel;

/**
 * Handles sending commands from the serial port
 * @author Parham
 */
public class CommandHandler {

    private SerialPort mPort;
    private OutputStream mOutStream;
    private JPGCameraModel mModel;

    public CommandHandler(SerialPort port, JPGCameraModel model){
        mPort = port;
        mModel = model;
    }

    public void sendCommand(CameraCommand command){
        try {
            if (mOutStream == null){
                mOutStream = mPort.getOutputStream();
            }
            //Start response thread
            if (command == CameraCommand.SIZE){
                BasicSerialResponse response = new FileSizeSerialResponse(mPort, command, mModel);
                Thread t = new Thread(response);
                t.start();
                mOutStream.write(command.getCommand());
            } else if (command == CameraCommand.READ) {
                BasicSerialResponse response = new FileContentSerialResponse(mPort, command, mModel.getFileSizeToRead());
                Thread t = new Thread(response);
                t.start();

                //Correct the command here

                mOutStream.write(command.getCommand());
            } else {
                BasicSerialResponse response = new BasicSerialResponse(mPort, command);
                Thread t = new Thread(response);
                t.start();
                mOutStream.write(command.getCommand());
            }
        } catch (IOException ex) {
            Logger.getLogger(CommandHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
