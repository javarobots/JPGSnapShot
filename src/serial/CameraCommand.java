package serial;

/**
 *
 * @author Parham
 */
public enum CameraCommand {

    RESET("Reset Camera", new byte[]{0x24,0x52,0x53,0x54}),
    TAKE("Take Picture", new byte[]{0x24,0x53,0x4E,0x50}),
    SIZE("Get Image Size", new byte[]{0x24,0x53,0x49,0x5A}),
    READ("Read Image", new byte[]{0x28}),
    VERSION("Get Version", new byte[]{0x24,0x56,0x45,0x52});

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
