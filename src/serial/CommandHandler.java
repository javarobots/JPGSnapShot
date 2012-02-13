package serial;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.JPGCameraModel;

/**
 * Handles sending commands to the LinkSprite camera
 * @author Parham
 */
public class CommandHandler {

    private SerialPort mPort;
    private OutputStream mOutStream;
    private JPGCameraModel mModel;
    private byte[] buffer = new byte[1024];
    private CameraCommand mCurrentCommand;
    private ImageData mImageData;
    private int mFileSize = 0;


    public CommandHandler(SerialPort port, JPGCameraModel model) throws IOException, TooManyListenersException{
        mPort = port;
        mPort.addEventListener(new SerialDataListener(port.getInputStream()));
        mModel = model;
        mImageData = new ImageData();
    }

    public void sendCommand(CameraCommand command){
        try {
            //Get output stream
            if (mOutStream == null){
                mOutStream = mPort.getOutputStream();
            }
            mCurrentCommand = command;
            mOutStream.write(command.getCommand());
        } catch (IOException ex) {
            Logger.getLogger(CommandHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Inner class
    private class SerialDataListener implements SerialPortEventListener {

        private InputStream mInStream;

        public SerialDataListener(InputStream inStream){
            mInStream = inStream;
        }

        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                //Delay to ensure the response buffer can fill
                System.out.println(mCurrentCommand.getName() + " command");
                Thread.sleep(100);
                int bytesRead = mInStream.read(buffer);
                while(bytesRead > 0){
                    if (mCurrentCommand == CameraCommand.SIZE){
                        System.out.println("Image size");
                    } else if (mCurrentCommand == CameraCommand.READ){
                        System.out.println("Snap image");
                    } else if (mCurrentCommand == CameraCommand.RESET){
                        System.out.println("Camera reset");
                    } else if (mCurrentCommand == CameraCommand.VERSION){
                        System.out.println("Camera version");
                    }
                    System.out.println(new String(buffer,0,bytesRead));
                    bytesRead = mInStream.read(buffer);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CommandHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(serial.SerialDataListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
