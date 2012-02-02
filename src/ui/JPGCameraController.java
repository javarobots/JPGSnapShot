/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

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

}
