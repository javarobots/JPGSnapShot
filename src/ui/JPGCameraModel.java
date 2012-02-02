/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.List;
import java.util.Observable;
import util.rxtx.RxTxUtilities;

/**
 *
 * @author Parham
 */
class JPGCameraModel extends Observable {

    List<String> mAvailablePorts;

    public void initModel(){
        mAvailablePorts = RxTxUtilities.getAvailablePorts();
        setChanged();
    }

    public List<String> getAvailablePorts() {
        return mAvailablePorts;
    }



}
