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
    private boolean mFinshedReadingData = false;

    public void addImageData(byte b){
        mImageData.add(b);
    }

    public void clearImageData(){
        mImageData.clear();
    }

    public void finishedReadingData(boolean b){
        mFinshedReadingData = b;
    }

    public boolean isFinishedReadingData(){
        return mFinshedReadingData;
    }

}
