#include <JPEGCamera.h>
#include <NewSoftSerial.h>

//Create an instance of the camera
JPEGCamera camera;

char response[64];
unsigned int count=0;
int size=0;
int address=0;
int eof=0;

void setup()
{
    //Setup the camera, serial port and memory card
    camera.begin();
    Serial.begin(38400);
    
    //Reset the camera
    count=camera.reset(response);
    delay(3000);
    
    //Take a picture
    count=camera.takePicture(response);
    //Print the response to the 'TAKE_PICTURE' command.
    Serial.write((const uint8_t*)response, count);
    Serial.println();
    
    //Get the size of the picture
    count = camera.getSize(response, &size);
    //Print the size
    Serial.print("Size: ");
    Serial.println(size);
    
    //Starting at address 0, keep reading data until we've read 'size' data.
    while(address < size)
    {
        //Read the data starting at the current address.
        count=camera.readData(response, address);
        //Store all of the data that we read to the SD card
        for(int i=0; i<count; i++){
            //Check the response for the eof indicator (0xFF, 0xD9). If we find it, set the eof flag
            if((response[i] == (char)0xD9) && (response[i-1]==(char)0xFF))eof=1;
            //Save the data to the SD card
            Serial.write(response[i]);
            //If we found the eof character, get out of this loop and stop reading data
            if(eof==1)break;
        }
        //Increment the current address by the number of bytes we read
        address+=count;
        //Make sure we stop reading data if the eof flag is set.
        if(eof==1)break;
    }
     Serial.print("Done.");
}

void loop()
{

}
