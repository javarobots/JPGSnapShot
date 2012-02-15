/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.data;

/**
 *
 * @author Parham
 */
public class ByteComparisonArray {

    private byte[] mCompareArray;

    public ByteComparisonArray(int size){
        mCompareArray = new byte[size];
    }

    public void addByte(byte b){
        //Shift all bytes
        for (int i = 0; i < mCompareArray.length - 1; i++){
            mCompareArray[i] = mCompareArray[i+1];
        }
        //Add new byte
        mCompareArray[mCompareArray.length-1] = b;
    }

    public byte[] getCompareArray(){
        return mCompareArray;
    }

    public boolean compareArray(byte[] array){
        if (array.length != mCompareArray.length){
            return false;
        }
        for (int i = 0; i < mCompareArray.length; i++){
            if (mCompareArray[i] != array[i]){
                return false;
            }
        }
        return true;
    }

}
