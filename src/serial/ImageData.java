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

    public void addToImageData(byte b){
        mImageData.add(b);
    }

    public int bytesRead(){
        return mImageData.size();
    }

}
