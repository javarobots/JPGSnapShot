package serial;

/**
 *
 * @author Parham
 */
public enum CameraCommand {

    RESET("Reset Camera", new byte[]{0x56,0x00,0x26,0x00}, new byte[]{0x76,0x00,0x26,0x00}),
    TAKE("Take Picture", new byte[]{0x56,0x00,0x36,0x01,0x00}, new byte[]{0x76,0x00,0x36,0x00,0x00}),
    SIZE("Get Image Size", new byte[]{0x56,0x00,0x34,0x01,0x00}, new byte[]{0x76,0x00,0x34,0x00,0x04,0x00,0x00}),
    READ("Read Image", new byte[]{0x56,0x00,0x32,0x0C,0x00,0x0A,0x00,0x00}, new byte[]{0x76,0x00,0x32,0x00});

    private String mDescription;
    private byte[] mCommand;
    private byte[] mExpectedResponse;

    CameraCommand(String name, byte[] command, byte[] response){
        mDescription = name;
        mCommand = command;
        mExpectedResponse = response;
    }

    public String getName(){
        return mDescription;
    }

    public byte[] getCommand(){
        return mCommand;
    }

    public int getExpectedReturnSize(){
        return mExpectedResponse.length;
    }

    public byte[] getExpectedResponse(){
        return mExpectedResponse;
    }

}
