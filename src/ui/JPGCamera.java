package ui;

import commonutilities.swing.ComponentPosition;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Parham
 */
public class JPGCamera extends javax.swing.JFrame implements Observer {

    private JPGCameraController mController;

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JPGCamera camera = new JPGCamera();
                ComponentPosition.centerFrame(camera);
                camera.setVisible(true);
            }
        });
    }

    /** Creates new form JPGCamera */
    @SuppressWarnings("LeakingThisInConstructor")
    public JPGCamera() {
        initComponents();

        JPGCameraModel model = new JPGCameraModel();
        model.addObserver(this);
        model.initModel();
        model.notifyObservers();

        mController = new JPGCameraController(model);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mComPortLabel = new javax.swing.JLabel();
        mComPortComboBox = new javax.swing.JComboBox();
        mResetButton = new javax.swing.JButton();
        mTakePictureButton = new javax.swing.JButton();
        mReadFileSizeButton = new javax.swing.JButton();
        mReadFileContentButton = new javax.swing.JButton();
        mOpenPortButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JPG Camera");

        mComPortLabel.setText("COM Port:");

        mResetButton.setText("Reset");
        mResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mResetButtonActionPerformed(evt);
            }
        });

        mTakePictureButton.setText("Take Picture");
        mTakePictureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mTakePictureButtonActionPerformed(evt);
            }
        });

        mReadFileSizeButton.setText("Read File Size");

        mReadFileContentButton.setText("Read File Content");

        mOpenPortButton.setText("Open");
        mOpenPortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mOpenPortButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mComPortLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mComPortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mOpenPortButton))
                    .addComponent(mResetButton)
                    .addComponent(mTakePictureButton)
                    .addComponent(mReadFileSizeButton)
                    .addComponent(mReadFileContentButton))
                .addContainerGap(246, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {mReadFileContentButton, mReadFileSizeButton, mResetButton, mTakePictureButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mComPortLabel)
                    .addComponent(mComPortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mOpenPortButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mResetButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mTakePictureButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mReadFileSizeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mReadFileContentButton)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mOpenPortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mOpenPortButtonActionPerformed
        mController.openComPort(mComPortComboBox.getSelectedItem().toString());
    }//GEN-LAST:event_mOpenPortButtonActionPerformed

    private void mResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mResetButtonActionPerformed
        mController.resetCamera();
    }//GEN-LAST:event_mResetButtonActionPerformed

    private void mTakePictureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mTakePictureButtonActionPerformed
        mController.takePicture();
    }//GEN-LAST:event_mTakePictureButtonActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox mComPortComboBox;
    private javax.swing.JLabel mComPortLabel;
    private javax.swing.JButton mOpenPortButton;
    private javax.swing.JButton mReadFileContentButton;
    private javax.swing.JButton mReadFileSizeButton;
    private javax.swing.JButton mResetButton;
    private javax.swing.JButton mTakePictureButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof JPGCameraModel){
            JPGCameraModel model = (JPGCameraModel) o;
            mComPortComboBox.setModel(new DefaultComboBoxModel(model.getAvailablePorts().toArray(new String[0])));
        }
    }

}
