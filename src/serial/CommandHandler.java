/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serial;

import gnu.io.SerialPort;

/**
 * Handles sending commands from the serial port
 * @author Parham
 */
public class CommandHandler {

    public enum CAMERA_COMMAND {RESET,TAKE,SIZE,READ}

    private final byte[] mReset = {0x56,0x00,0x26,0x00};
    private final byte[] mTake = {0x56,0x00,0x36,0x01,0x00};
    private final byte[] mSize = {0x56,0x00,0x34,0x01,0x00};
    private final byte[] mRead = {0x56,0x00,0x32,0x0C,0x00,0x0A,0x00,0x00};

    private SerialPort mPort;

    public CommandHandler(SerialPort port){
        mPort = port;
    }

    public void sendCommand(CAMERA_COMMAND command){
        switch(command){
            case RESET:
                break;
            case TAKE:
                break;
            case SIZE:
                break;
            case READ:
                break;
        }
    }

}
