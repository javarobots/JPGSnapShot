package serial;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import ui.JPGCamera;
import ui.JPGCameraController;

/**
 * Performs a snap shot and image download with a single click.
 * @author Parham
 */
public class OneClickSnapShotWorker extends SwingWorker<Boolean,Boolean> {

    private JPGCameraController mController;

    public OneClickSnapShotWorker(JPGCameraController controller){
        mController = controller;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        try {
            mController.takePicture();
            Thread.sleep(1000);
            mController.getImageSize();
            Thread.sleep(1000);
            mController.readImageData();
        } catch (InterruptedException ex) {
            Logger.getLogger(JPGCamera.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
