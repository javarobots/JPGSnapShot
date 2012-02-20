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
import javax.swing.ImageIcon;
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
    private long mStartTime;



    public CommandHandler(SerialPort port, JPGCameraModel model) throws IOException, TooManyListenersException{
        mPort = port;
        mPort.addEventListener(new SerialDataListener(port.getInputStream()));
        mModel = model;
        mImageData = new ImageData();
    }

    public void sendCommand(CameraCommand command){
        mCurrentCommand = command;
        if (command == CameraCommand.READ){
            mStartTime = System.currentTimeMillis();
        }
        transmitCommand(command.getCommand());
    }

    public void sendCommand(byte[] command){
        if (command[0] == 0x24 && command[1] == 0x54 && command[2] == 0x4C && command[3] == 0x54){
            //Tilt
            mCurrentCommand = CameraCommand.TILT;
        } else if (command[0] == 0x24 && command[1] == 0x50 && command[2] == 0x41 && command[3] == 0x4E){
            //Pan
            mCurrentCommand = CameraCommand.PAN;
        }
        transmitCommand(command);
    }

    private void transmitCommand(byte[] command){
        try {
            //Get output stream
            if (mOutStream == null){
                mOutStream = mPort.getOutputStream();
            }
            mOutStream.write(command);
        } catch (IOException ex) {
            Logger.getLogger(CommandHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Inner class
    private class SerialDataListener implements SerialPortEventListener {

        private InputStream mInStream;
        private int byteIndex = 0;
        private int mFileSize = 0;
        private byte[] mImageDataArray;

        public SerialDataListener(InputStream inStream){
            mInStream = inStream;
        }

        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                //Delay to ensure the response buffer can fill
                System.out.println(mCurrentCommand.getName() + " command");
                Thread.sleep(250);
                int bytesRead = mInStream.read(buffer);
                while(bytesRead > 0){
                    if (mCurrentCommand == CameraCommand.SIZE){
                        String[] splitString = (new String(buffer,0,bytesRead)).split(" ");
                        mFileSize = Integer.parseInt(splitString[0]);
                        mModel.getProgressBar().setMaximum(mFileSize);
                        mModel.getProgressBar().setValue(0);
                        mImageDataArray = new byte[mFileSize];
                        System.out.print(new String(buffer,0,bytesRead));
                    } else if (mCurrentCommand == CameraCommand.READ){
                        for (int i = 0; i < bytesRead; i++){
                            mImageDataArray[mImageDataArray.length - mFileSize] = buffer[i];
                            mModel.getProgressBar().setValue(mModel.getProgressBar().getValue() + 1);
                            mFileSize--;
                        }
                        if (mFileSize == 0){
                            try {
                                System.out.println("Time to get image: " + (System.currentTimeMillis() - mStartTime));
                                ImageIcon image = new ImageIcon(mImageDataArray);
                                mModel.getImageLabel().setIcon(image);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    } else if (mCurrentCommand == CameraCommand.RESET){
                        System.out.print(new String(buffer,0,bytesRead));
                    } else if (mCurrentCommand == CameraCommand.VERSION){
                        System.out.print(new String(buffer,0,bytesRead));
                    } else if (mCurrentCommand == CameraCommand.TAKE){
                        System.out.print(new String(buffer,0,bytesRead));
                    } else if (mCurrentCommand == CameraCommand.DIMENSION){
                        System.out.print(new String(buffer,0,bytesRead));
                    }
                    bytesRead = mInStream.read(buffer);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CommandHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(util.data.SerialDataListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
