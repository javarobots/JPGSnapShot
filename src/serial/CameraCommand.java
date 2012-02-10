package serial;

/**
 *
 * @author Parham
 */
public enum CameraCommand {

    RESET("Reset Camera", new byte[]{0x0A}),
    TAKE("Take Picture", new byte[]{0x14}),
    SIZE("Get Image Size", new byte[]{0x1E}),
    READ("Read Image", new byte[]{0x28});

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
