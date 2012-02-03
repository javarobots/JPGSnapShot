/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.rxtx;

import commonutilities.swing.ComponentPosition;
import gnu.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import ui.dialog.ErrorDialog;

/**
 * A utility class for the RxTx library
 * @author jparham
 */
public class RxTxUtilities {

    public static List<String> getAvailablePorts(){
        List<String> portNames = new ArrayList<>();
        Enumeration identifiers = CommPortIdentifier.getPortIdentifiers();
        while (identifiers.hasMoreElements()){
            CommPortIdentifier identifier = (CommPortIdentifier) identifiers.nextElement();
            if (identifier.getPortType() == CommPortIdentifier.PORT_SERIAL){
                portNames.add(identifier.getName());
            }
        }
        return portNames;
    }

    public static SerialPort openPortByName(String name, int baudRate){
        SerialPort port = null;
        try {
            CommPortIdentifier identifier = CommPortIdentifier.getPortIdentifier(name);
            port = (SerialPort)identifier.open("JPGCamera", 2000);
            port.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (PortInUseException | NoSuchPortException | UnsupportedCommOperationException ex) {
            ErrorDialog dialog = new ErrorDialog(null,true,ex.getMessage());
            ComponentPosition.centerFrame(dialog);
            dialog.setVisible(true);
        }
        return port;
    }

}
