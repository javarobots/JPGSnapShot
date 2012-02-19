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

    void getVersion() {
        mModel.getCommandHandler().sendCommand(CameraCommand.VERSION);
    }

    void getDimension() {
        mModel.getCommandHandler().sendCommand(CameraCommand.DIMENSION);
    }

    void readImageData() {
        mModel.getCommandHandler().sendCommand(CameraCommand.READ);
    }

    void panCamera(int panValue){
        int highByte = panValue / 256;
        int lowByte = panValue % 256;
        byte[] command = CameraCommand.PAN.getCommand();
        command[4] = (byte) highByte;
        command[5] = (byte) lowByte;
        mModel.getCommandHandler().sendCommand(command);
    }

    void tiltCamera(int tiltValue) {
        int highByte = tiltValue / 256;
        int lowByte = tiltValue % 256;
        byte[] command = CameraCommand.TILT.getCommand();
        command[4] = (byte) highByte;
        command[5] = (byte) lowByte;
        mModel.getCommandHandler().sendCommand(command);
    }

}
