/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import gnu.io.SerialPort;
import java.util.List;
import java.util.Observable;
import serial.CommandHandler;
import serial.SerialResponse;
import util.rxtx.RxTxUtilities;

/**
 *
 * @author Parham
 */
class JPGCameraModel extends Observable {

    private List<String> mAvailablePorts;
    private SerialPort mSerialPort;
    private CommandHandler mCommandHandler;
    private SerialResponse mReader;

    public void initModel(){
        mAvailablePorts = RxTxUtilities.getAvailablePorts();
        setChanged();
    }

    public List<String> getAvailablePorts() {
        return mAvailablePorts;
    }

    public SerialPort getSerialPort() {
        return mSerialPort;
    }

    public void setSerialPort(SerialPort serialPort) {
        mSerialPort = serialPort;
        mCommandHandler = new CommandHandler(mSerialPort);
        mReader = new SerialResponse(serialPort);
        Thread t = new Thread(mReader);
        t.start();
        setChanged();
    }

    public CommandHandler getCommandHandler() {
        return mCommandHandler;
    }
}
