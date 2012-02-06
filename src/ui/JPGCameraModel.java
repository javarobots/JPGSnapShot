package ui;

import gnu.io.SerialPort;
import java.util.List;
import java.util.Observable;
import serial.CommandHandler;
import util.rxtx.RxTxUtilities;

/**
 *
 * @author Parham
 */
public class JPGCameraModel extends Observable {

    private List<String> mAvailablePorts;
    private SerialPort mSerialPort;
    private CommandHandler mCommandHandler;
    private int mFileSizeToRead;

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
        mCommandHandler = new CommandHandler(mSerialPort,this);
        setChanged();
    }

    public CommandHandler getCommandHandler() {
        return mCommandHandler;
    }

    public int getFileSizeToRead() {
        return mFileSizeToRead;
    }

    public void setFileSizeToRead(int fileSizeToRead) {
        mFileSizeToRead = fileSizeToRead;
    }


}
