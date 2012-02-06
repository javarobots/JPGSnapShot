/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serial;

import gnu.io.SerialPort;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parham
 */
public class FileContentSerialResponse extends BasicSerialResponse {

    private int mFileSizeToRead;
    private boolean mEOF = false;

    public FileContentSerialResponse(SerialPort port, CameraCommand command, int fileSize){
        super(port,command);
        mFileSizeToRead = fileSize;
    }

    @Override
    public void run(){
        try {
            super.run();
            //Do the reading here
            while(!mEOF){
                mBytesRead = mInputStream.read(mBuffer);
                if (mBytesRead > 0){
                    for (int i = 0; i < mBytesRead; i++){
                        mFullResponse.add(mBuffer[i]);
                    }
                    //Check for EOF
                    int b1 = mFullResponse.get(mFullResponse.size() - 2) & 0xFF;
                    int b2 = mFullResponse.get(mFullResponse.size() - 1) & 0xFF;
                    if (b1 == 255 && b2 == 217){
                        mEOF = true;
                    }
                }
            }
            mInputStream.close();
            System.out.println("Stop file content reader");
        } catch (IOException ex) {
            Logger.getLogger(FileContentSerialResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
