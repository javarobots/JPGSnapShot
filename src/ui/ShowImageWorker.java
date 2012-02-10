/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.SwingWorker;
import serial.ImageData;

/**
 *
 * @author Parham
 */
public class ShowImageWorker extends SwingWorker<Boolean,Boolean> {

    private ImageData mImageData;

    public ShowImageWorker(ImageData data) {
        mImageData = data;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        while (!mImageData.isFinishedReadingData()){
            Thread.sleep(50);
        }
        return true;
    }

    @Override
    public void done(){
        System.out.println("Image data collected");
    }

}
