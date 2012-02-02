/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import gnu.io.SerialPort;
import java.util.List;
import java.util.Observable;
import util.rxtx.RxTxUtilities;

/**
 *
 * @author Parham
 */
class JPGCameraModel extends Observable {

    private List<String> mAvailablePorts;
    private SerialPort mSerialPort;

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

    public void setSerialPort(SerialPort mSerialPort) {
        this.mSerialPort = mSerialPort;
        setChanged();
    }

}
