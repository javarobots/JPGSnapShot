package ui;

import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import serial.CommandHandler.CAMERA_COMMAND;
import serial.ResponseReader;
import util.rxtx.RxTxUtilities;

/**
 *
 * @author Parham
 */
class JPGCameraController {

    private JPGCameraModel mModel;

    public JPGCameraController(JPGCameraModel model){
        mModel = model;
    }

    public void openComPort(String portName){
//        try {
            mModel.setSerialPort(RxTxUtilities.openPortByName(portName, 38400));
//            mModel.getSerialPort().addEventListener(new ResponseReader());
//        } catch (TooManyListenersException ex) {
//            Logger.getLogger(JPGCameraController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    void resetCamera() {
        mModel.getCommandHandler().sendCommand(CAMERA_COMMAND.RESET);
    }

}
