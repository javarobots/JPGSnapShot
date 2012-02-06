package ui;

import serial.CameraCommand;
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
        mModel.getCommandHandler().sendCommand(CameraCommand.RESET);
    }

    void takePicture() {
        mModel.getCommandHandler().sendCommand(CameraCommand.TAKE);
    }

    void getImageSize() {
        mModel.getCommandHandler().sendCommand(CameraCommand.SIZE);
    }

    void readImage() {
        mModel.getCommandHandler().sendCommand(CameraCommand.READ);
    }

}
