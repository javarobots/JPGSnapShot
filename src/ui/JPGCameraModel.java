package ui;

import gnu.io.SerialPort;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
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
    private JLabel mImageLabel;
    private JProgressBar mProgressBar;
    private byte[] mImageData;

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
        try {
            mSerialPort = serialPort;
            mCommandHandler = new CommandHandler(mSerialPort,this);
            mSerialPort.notifyOnDataAvailable(true);
            setChanged();
        } catch (TooManyListenersException | IOException ex) {
            Logger.getLogger(JPGCameraModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CommandHandler getCommandHandler() {
        return mCommandHandler;
    }

    public JLabel getImageLabel() {
        return mImageLabel;
    }

    public void setImageLabel(JLabel imageLabel) {
        mImageLabel = imageLabel;
    }

    public JProgressBar getProgressBar() {
        return mProgressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        mProgressBar = progressBar;
    }

    public byte[] getImageData() {
        return mImageData;
    }

    public void setImageData(byte[] imageData) {
        mImageData = imageData;
    }




}
