/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serial;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Parham
 */
public class ImageData {

    private List<Byte> mImageData = new ArrayList<>();
    private List<Byte> mTempImageData = new ArrayList<>();

    public void addToTmpImageData(byte b){
        mTempImageData.add(b);
    }

    public int bytesRead(){
        return mImageData.size();
    }

    /**
     * Remove header and message end from image
     * data and transfer it to the true image data
     * array
     */
    public void processTempImageData(){
        boolean headerIsLengthFive = false;
        boolean footerIsLengthFour = false;
        if (mTempImageData.get(0) == 0x76 &&
                mTempImageData.get(1) == 0x00 &&
                mTempImageData.get(2) == 0x32 &&
                mTempImageData.get(3) == 0x00 &&
                mTempImageData.get(4) == 0x00){

            System.out.println("Image response header matches");
            headerIsLengthFive = true;

        }

        if (mTempImageData.get(mTempImageData.size() - 4) == 0x76 &&
                mTempImageData.get(mTempImageData.size() - 3) == 0x00 &&
                mTempImageData.get(mTempImageData.size() - 2) == 0x32 &&
                mTempImageData.get(mTempImageData.size() - 1) == 0x00){

            System.out.println("Image response footer matches");
            footerIsLengthFour = true;

        }

        //Copy out data to image data array
        if (headerIsLengthFive && footerIsLengthFour){
            int bytesToCopy = mTempImageData.size() - 9;
            for (int i = 0; i < bytesToCopy; i++){
                mImageData.add(mTempImageData.get(i+5));
            }
        }
    }

    public int getNextReadAddress(){
        //Trim data
        int trimLength = mImageData.size() % 8;
        for (int i = 0; i < trimLength; i++){
            mImageData.remove(mImageData.size() - 1);
        }
        return mImageData.size();
    }

}
