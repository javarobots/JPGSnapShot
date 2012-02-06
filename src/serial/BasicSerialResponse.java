package serial;

import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parham
 */
public class BasicSerialResponse implements Runnable {

    protected SerialPort mPort;
    protected boolean mStop = false;
    protected CameraCommand mCommand;
    protected List<Byte> mFullResponse = new ArrayList<>();
    protected byte[] mBuffer = new byte[8];
    protected int mBytesRead;
    protected InputStream mInputStream;

    public BasicSerialResponse(SerialPort port, CameraCommand command){
        mPort = port;
        mCommand = command;
    }

    @Override
    public void run() {
        try {
            int totalBytes = 0;
            mInputStream = mPort.getInputStream();
            while (!mStop){
                mBytesRead = mInputStream.read(mBuffer);
                totalBytes += mBytesRead;
                if (mBytesRead > 0){
                    for (int i = 0; i < mBytesRead; i++){
                        mFullResponse.add(mBuffer[i]);
                    }
                    if (totalBytes == mCommand.getExpectedReturnSize()){
                        System.out.println("Response was valid: " + compareBasicCommands());
                        System.out.println("Stoping basic response reader.");
                        mStop = true;
                    }
                }
            }
            if (mCommand != CameraCommand.READ){
                mInputStream.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(BasicSerialResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean compareBasicCommands(){
        byte[] expected = mCommand.getExpectedResponse();
        boolean validResponse = true;
        for (int i = 0; i < expected.length; i++){
            if (expected[i] != mFullResponse.get(i)){
                validResponse = false;
            }
        }
        return validResponse;
    }

}
