package serial;

/**
 *
 * @author Parham
 */
public enum CameraCommand {

    RESET("Reset Camera", new byte[]{0x24,0x52,0x53,0x54,0x00,0x00}),
    TAKE("Take Picture", new byte[]{0x24,0x53,0x4E,0x50,0x00,0x00}),
    SIZE("Get Image Size", new byte[]{0x24,0x53,0x49,0x5A,0x00,0x00}),
    READ("Read Image", new byte[]{0x24,0x44,0x41,0x54,0x00,0x00}),
    VERSION("Get Version", new byte[]{0x24,0x56,0x45,0x52,0x00,0x00}),
    DIMENSION("Get Picture Dimension", new byte[]{0x24,0x44,0x49,0x4D,0x00,0x00}),
    PAN("Pan Camera", new byte[]{0x24,0x50,0x41,0x4E,0x00,0x00}),
    TILT("Tilt Camera", new byte[]{0x24,0x54,0x4C,0x54,0x00,0x00});

    private String mDescription;
    private byte[] mCommand;

    CameraCommand(String name, byte[] command){
        mDescription = name;
        mCommand = command;
    }

    public String getName(){
        return mDescription;
    }

    public byte[] getCommand(){
        return mCommand;
    }

}
