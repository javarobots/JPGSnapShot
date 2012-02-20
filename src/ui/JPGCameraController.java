package ui;

import serial.CameraCommand;
import serial.OneClickSnapShotWorker;
import util.rxtx.RxTxUtilities;

/**
 *
 * @author Parham
 */
public class JPGCameraController {

    private JPGCameraModel mModel;

    public JPGCameraController(JPGCameraModel model){
        mModel = model;
    }

    public void openComPort(String portName){
        mModel.setSerialPort(RxTxUtilities.openPortByName(portName, 38400));
    }

    public void resetCamera() {
        mModel.getCommandHandler().sendCommand(CameraCommand.RESET);
    }

    public void takePicture() {
        mModel.getCommandHandler().sendCommand(CameraCommand.TAKE);
    }

    public void getImageSize() {
        mModel.getCommandHandler().sendCommand(CameraCommand.SIZE);
    }

    public void getVersion() {
        mModel.getCommandHandler().sendCommand(CameraCommand.VERSION);
    }

    public void getDimension() {
        mModel.getCommandHandler().sendCommand(CameraCommand.DIMENSION);
    }

    public void readImageData() {
        mModel.getCommandHandler().sendCommand(CameraCommand.READ);
    }

    public void panCamera(int panValue){
        int highByte = panValue / 256;
        int lowByte = panValue % 256;
        byte[] command = CameraCommand.PAN.getCommand();
        command[4] = (byte) highByte;
        command[5] = (byte) lowByte;
        mModel.getCommandHandler().sendCommand(command);
    }

    public void tiltCamera(int tiltValue) {
        int highByte = tiltValue / 256;
        int lowByte = tiltValue % 256;
        byte[] command = CameraCommand.TILT.getCommand();
        command[4] = (byte) highByte;
        command[5] = (byte) lowByte;
        mModel.getCommandHandler().sendCommand(command);
    }

    public void oneClickShot(){
        OneClickSnapShotWorker worker = new OneClickSnapShotWorker(this);
        worker.execute();
    }

}
