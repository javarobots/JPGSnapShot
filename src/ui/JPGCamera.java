package ui;

import commonutilities.swing.ComponentPosition;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
                //Set the look and feel to Nimbus
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(JPGCamera.class.getName()).log(Level.SEVERE, null, ex);
                }
                JPGCamera camera = new JPGCamera();
                ComponentPosition.centerFrame(camera);
                camera.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("resources/cameraImage.png")));
                camera.setVisible(true);
            }
        });
    }

    /** Creates new form JPGCamera */
    @SuppressWarnings("LeakingThisInConstructor")
    public JPGCamera() {
        initComponents();

        JPGCameraModel model = new JPGCameraModel();
        model.setImageLabel(mImageLabel);
        model.setProgressBar(jProgressBar1);
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
        mGetImageDimensionButton = new javax.swing.JButton();
        mReadImageDataButton = new javax.swing.JButton();
        mImageLabel = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        mPanLabel = new javax.swing.JLabel();
        mPanSlider = new javax.swing.JSlider();
        mTiltLabel = new javax.swing.JLabel();
        mTiltSlider = new javax.swing.JSlider();

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
        mReadFileSizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mReadFileSizeButtonActionPerformed(evt);
            }
        });

        mReadFileContentButton.setText("Version");
        mReadFileContentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mReadFileContentButtonActionPerformed(evt);
            }
        });

        mOpenPortButton.setText("Open");
        mOpenPortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mOpenPortButtonActionPerformed(evt);
            }
        });

        mGetImageDimensionButton.setText("Image Dimension");
        mGetImageDimensionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mGetImageDimensionButtonActionPerformed(evt);
            }
        });

        mReadImageDataButton.setText("Read Image Data");
        mReadImageDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mReadImageDataButtonActionPerformed(evt);
            }
        });

        mImageLabel.setToolTipText("");
        mImageLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        mPanLabel.setText("Camera Pan:");

        mPanSlider.setMaximum(2000);
        mPanSlider.setMinimum(1000);
        mPanSlider.setValue(1500);
        mPanSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mPanSliderStateChanged(evt);
            }
        });

        mTiltLabel.setText("Camera Tilt:");

        mTiltSlider.setMaximum(1750);
        mTiltSlider.setMinimum(1000);
        mTiltSlider.setValue(1500);
        mTiltSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mTiltSliderStateChanged(evt);
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
                            .addComponent(mReadFileContentButton)
                            .addComponent(mGetImageDimensionButton)
                            .addComponent(mReadImageDataButton))
                        .addGap(18, 18, 18)
                        .addComponent(mImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mTiltLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mPanLabel, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(mTiltSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mPanSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {mGetImageDimensionButton, mReadFileContentButton, mReadFileSizeButton, mReadImageDataButton, mResetButton, mTakePictureButton});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {mPanLabel, mTiltLabel});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(mImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mGetImageDimensionButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mReadImageDataButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mPanSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mPanLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mTiltSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mTiltLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(98, Short.MAX_VALUE))
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

    private void mReadFileSizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mReadFileSizeButtonActionPerformed
        mController.getImageSize();
    }//GEN-LAST:event_mReadFileSizeButtonActionPerformed

    private void mReadFileContentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mReadFileContentButtonActionPerformed
        mController.getVersion();
    }//GEN-LAST:event_mReadFileContentButtonActionPerformed

    private void mGetImageDimensionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mGetImageDimensionButtonActionPerformed
        mController.getDimension();
    }//GEN-LAST:event_mGetImageDimensionButtonActionPerformed

    private void mReadImageDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mReadImageDataButtonActionPerformed
        mController.readImageData();
    }//GEN-LAST:event_mReadImageDataButtonActionPerformed

    private void mPanSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mPanSliderStateChanged
        mController.panCamera(mPanSlider.getValue());
    }//GEN-LAST:event_mPanSliderStateChanged

    private void mTiltSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mTiltSliderStateChanged
        mController.tiltCamera(mTiltSlider.getValue());
    }//GEN-LAST:event_mTiltSliderStateChanged



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JComboBox mComPortComboBox;
    private javax.swing.JLabel mComPortLabel;
    private javax.swing.JButton mGetImageDimensionButton;
    private javax.swing.JLabel mImageLabel;
    private javax.swing.JButton mOpenPortButton;
    private javax.swing.JLabel mPanLabel;
    private javax.swing.JSlider mPanSlider;
    private javax.swing.JButton mReadFileContentButton;
    private javax.swing.JButton mReadFileSizeButton;
    private javax.swing.JButton mReadImageDataButton;
    private javax.swing.JButton mResetButton;
    private javax.swing.JButton mTakePictureButton;
    private javax.swing.JLabel mTiltLabel;
    private javax.swing.JSlider mTiltSlider;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof JPGCameraModel){
            JPGCameraModel model = (JPGCameraModel) o;
            mComPortComboBox.setModel(new DefaultComboBoxModel(model.getAvailablePorts().toArray(new String[0])));
        }
    }

}
