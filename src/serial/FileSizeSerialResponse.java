/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serial;

import gnu.io.SerialPort;
import ui.JPGCameraModel;

/**
 *
 * @author Parham
 */
public class FileSizeSerialResponse extends BasicSerialResponse {

    private JPGCameraModel mModel;

    public FileSizeSerialResponse(SerialPort port, CameraCommand command, JPGCameraModel model){
        super(port,command);
        mModel = model;
    }

    @Override
    public void run(){
        super.run();
        if (mCommand == CameraCommand.SIZE){
            System.out.println("The file size is: " + fileSize());
            System.out.println("Stoping file size reader.");
        }
    }

    /**
     * The last two bytes are the file size
     * @return the files size
     */
    private int fileSize(){
        int size = (mFullResponse.get(mFullResponse.size() - 2) & 0xFF) * 256;
        System.out.println("Size 1: " + size);
        size = size + (mFullResponse.get(mFullResponse.size() - 1) & 0xFF);
        System.out.println("Size 2: " + size);
        return size;
    }

}
