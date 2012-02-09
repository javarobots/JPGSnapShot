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
    private int mFileSize = 0;

    public CommandHandler(SerialPort port, JPGCameraModel model) throws IOException, TooManyListenersException{
        mPort = port;
        mPort.addEventListener(new SerialDataListener(port.getInputStream()));
        mModel = model;
    }

    public void sendCommand(CameraCommand command){
        try {
            if (mOutStream == null){
                mOutStream = mPort.getOutputStream();
            }
            mCurrentCommand = command;
            mOutStream.write(command.getCommand());
            if (command == CameraCommand.READ){
                //Continue sending until EOF
            }
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
                System.out.println("Processing command: " + mCurrentCommand.getName());
                int bytesRead = mInStream.read(buffer);
                ByteComparisonArray compareArray;
                while(bytesRead > 0){
                    if (mCurrentCommand == CameraCommand.SIZE){
                        compareArray = new ByteComparisonArray(mCurrentCommand.getExpectedReturnSize());
                        for (int i = 0; i < bytesRead; i++){
                            compareArray.addByte(buffer[i]);
                            if (compareArray.compareArray(mCurrentCommand.getExpectedResponse())){
                                System.out.println("Command Sucessful");
                            }
                            if (i == 7){
                                mFileSize = (buffer[i] & 0xFF) * 256;
                            } else if (i == 8){
                                mFileSize += (buffer[i] & 0xFF);
                            }
                        }
                        System.out.println("Image size: " + mFileSize);
                    } else if (mCurrentCommand == CameraCommand.READ){

                    } else {
                        compareArray = new ByteComparisonArray(mCurrentCommand.getExpectedReturnSize());
                        for (int i = 0; i < bytesRead; i++){
                            compareArray.addByte(buffer[i]);
                            if (compareArray.compareArray(mCurrentCommand.getExpectedResponse())){
                                System.out.println("Command Sucessful");
                            }
                        }
                    }

                    //Ouput the result to the console
                    System.out.print(new String(buffer,0,bytesRead));
                    bytesRead = mInStream.read(buffer);
                }
            } catch (IOException ex) {
                Logger.getLogger(serial.SerialDataListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
