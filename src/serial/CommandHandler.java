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

    //Image properties
    private int mFileSize = 0;
    private boolean mEOF = false;
    private int mReadHeaderCount = 0;
    private int mFileReadIndex = 0;

    //Comparison arrays
    private ByteComparisonArray mReadCompareArray = new ByteComparisonArray(CameraCommand.READ.getExpectedReturnSize());
    private ByteComparisonArray mEOFComparator = new ByteComparisonArray(2);


    public CommandHandler(SerialPort port, JPGCameraModel model) throws IOException, TooManyListenersException{
        mPort = port;
        mPort.addEventListener(new SerialDataListener(port.getInputStream()));
        mModel = model;
        mImageData = new ImageData();
    }

    public void sendCommand(CameraCommand command){
        try {
            if (mOutStream == null){
                mOutStream = mPort.getOutputStream();
            }
            mCurrentCommand = command;
            if (command == CameraCommand.READ){
                //Build command if file size has been obtained
                if (mFileSize != 0){
                    byte[] modifiedCommand = new byte[command.getCommand().length + 8];
                    System.arraycopy(command.getCommand(), 0, modifiedCommand, 0, command.getCommand().length);

                    //Determine read address
                    int addressToRead = mImageData.getNextReadAddress();
                    System.out.println("Read address: " + addressToRead);
                    int addressHighByte = addressToRead / 256;
                    int addressLowByte = addressToRead % 256;
                    modifiedCommand[8] = (byte)addressHighByte;
                    modifiedCommand[9] = (byte)addressLowByte;

                    //Two pad bytes
                    modifiedCommand[10] = (byte)0x00;
                    modifiedCommand[11] = (byte)0x00;

                    //File size
                    int lengthHighByte = mFileSize / 256;
                    int lengthLowByte = mFileSize % 256;
                    modifiedCommand[12] = (byte) lengthHighByte;
                    modifiedCommand[13] = (byte) lengthLowByte;

                    //Interval time (two byte value * 0.01ms)
                    modifiedCommand[14] = (byte)0x00;
                    modifiedCommand[15] = (byte)0x0A;

                    mReadHeaderCount = 0;

                    mOutStream.write(modifiedCommand);
                } else {
                    System.out.println("File size has not been read");
                }

            //All other commands
            } else {
                mOutStream.write(command.getCommand());
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
                int bytesRead = mInStream.read(buffer);
                ByteComparisonArray compareArray = new ByteComparisonArray(mCurrentCommand.getExpectedReturnSize());
                while(bytesRead > 0){
                    //Get size command
                    if (mCurrentCommand == CameraCommand.SIZE){
                        for (int i = 0; i < bytesRead; i++){
                            compareArray.addByte(buffer[i]);
                            if (compareArray.compareArray(mCurrentCommand.getExpectedResponse())){
                                System.out.println("Size command sucessful");
                            }
                            if (i == 7){
                                mFileSize = (buffer[i] & 0xFF) * 256;
                            } else if (i == 8){
                                mFileSize += (buffer[i] & 0xFF);
                            }
                        }
                        System.out.println("Image size: " + mFileSize);

                    //Read image command
                    } else if (mCurrentCommand == CameraCommand.READ){
                        //Do comparisons for EOF  and end of message here
                        for (int i = 0; i < bytesRead; i++){
                            mReadCompareArray.addByte(buffer[i]);
                            mEOFComparator.addByte(buffer[i]);
                            mImageData.addToTmpImageData(buffer[i]);
                            if (mReadCompareArray.compareArray(mCurrentCommand.getExpectedResponse())){
                                System.out.println("Read header " + ++mReadHeaderCount + " found");
                            }
                            if (mEOFComparator.compareArray(new byte[]{(byte)0xFF,(byte)0xD9})){
                                System.out.println("EOF found");
                                mEOF = true;
                            }
                            if (mReadHeaderCount == 2){
                                mImageData.processTempImageData();
                                mReadHeaderCount = 0;
                            }
                        }


                    //All other commands
                    } else {
                        for (int i = 0; i < bytesRead; i++){
                            compareArray.addByte(buffer[i]);
                            if (compareArray.compareArray(mCurrentCommand.getExpectedResponse())){
                                if (mCurrentCommand == CameraCommand.RESET){
                                    mFileSize = 0;
                                }
                                System.out.println(mCurrentCommand.getName() + " command sucessful");
                            }
                        }
                    }

                    //Read data stream again
                    bytesRead = mInStream.read(buffer);
                }
            } catch (IOException ex) {
                Logger.getLogger(serial.SerialDataListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
