package ui;

import serial.CommandHandler.CAMERA_COMMAND;
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
        mModel.setSerialPort(RxTxUtilities.openPortByName(portName, 38400));
    }

    void resetCamera() {
        mModel.getCommandHandler().sendCommand(CAMERA_COMMAND.RESET);
    }

    void takePicture() {
        mModel.getCommandHandler().sendCommand(CAMERA_COMMAND.TAKE);
    }

}
