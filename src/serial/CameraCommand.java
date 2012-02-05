package serial;

/**
 *
 * @author Parham
 */
public enum CameraCommand {

    RESET("Reset Camera", new byte[]{0x56,0x00,0x26,0x00}, 71),
    TAKE("Take Picture", new byte[]{0x56,0x00,0x36,0x01,0x00}, 5),
    SIZE("Get Image Size", new byte[]{0x56,0x00,0x34,0x01,0x00}, 9),
    READ("Read Image", new byte[]{0x56,0x00,0x32,0x0C,0x00,0x0A,0x00,0x00}, 9999);

    private String mDescription;
    private byte[] mCommand;
    private int mExepectedResponseSize;

    CameraCommand(String name, byte[] command, int expectedReturnSize){
        mDescription = name;
        mCommand = command;
        mExepectedResponseSize = expectedReturnSize;
    }

    public String getName(){
        return mDescription;
    }

    public byte[] getCommand(){
        return mCommand;
    }

    public int getExpectedReturnSize(){
        return mExepectedResponseSize;
    }

}
