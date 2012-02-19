#include <Servo.h>
#include <VC0706.h>
#include <SoftwareSerial.h>



SoftwareSerial cameraConnection = SoftwareSerial(3,2);
VC0706 camera = VC0706(&cameraConnection);
Servo panServo;
Servo tiltServo;

const int DATA_BYTES = 6;
int dataIn[DATA_BYTES];
int dataIndex = 0;

void setup(){
  Serial.begin(38400);
  camera.begin();
  panServo.attach(13);
  panServo.writeMicroseconds(1500);
  tiltServo.attach(12);
  tiltServo.writeMicroseconds(1500);
}

void loop(){
  if (Serial.available() > 0){
    while(Serial.available() > 0){
      //Read in a byte
      dataIn[dataIndex] = Serial.read();
      //Increment the array index
      dataIndex++;
      //Reset the array index
      if (dataIndex == DATA_BYTES){
        dataIndex = 0;
        //Compare the data array
        evaluateData();
      }      
    } 
  }    
}

void evaluateData(){
  if (dataIn[0] == 0x24 && dataIn[1] == 0x52 && dataIn[2] == 0x53 && dataIn[3] == 0x54){
    //Reset camera
    if (camera.reset()){
      Serial.println("Reset succeed");
    } else {
      Serial.println("Reset fail");
    }     
  } else if (dataIn[0] == 0x24 && dataIn[1] == 0x56 && dataIn[2] == 0x45 && dataIn[3] == 0x52){
    //Get version info
    char *reply = camera.getVersion();
    if (reply == 0) {
      Serial.print("Failed to get version");
    } else {
      Serial.println("-----------------");
      Serial.print(reply);
      Serial.println("\n-----------------");
    } 
  } else if (dataIn[0] == 0x24 && dataIn[1] == 0x53 && dataIn[2] == 0x4E && dataIn[3] == 0x50){
    //Snaps shot
    if (!camera.takePicture()){ 
      Serial.println("Failed to snap!");
    } else { 
      Serial.println("Picture taken!");
    }
  } else if (dataIn[0] == 0x24 && dataIn[1] == 0x53 && dataIn[2] == 0x49 && dataIn[3] == 0x5A){
    //Image size
    Serial.print(getFrameLength(), DEC);
    Serial.println(" byte image");   
  } else if (dataIn[0] == 0x24 && dataIn[1] == 0x44 && dataIn[2] == 0x49 && dataIn[3] == 0x4D){
    uint8_t imgsize = camera.getImageSize();
    if (imgsize == VC0706_640x480) Serial.println("640x480");
    if (imgsize == VC0706_320x240) Serial.println("320x240");
    if (imgsize == VC0706_160x120) Serial.println("160x120");
  } else if (dataIn[0] == 0x24 && dataIn[1] == 0x44 && dataIn[2] == 0x41 && dataIn[3] == 0x54){
    //Read image: Detach servos until done
    panServo.detach();
    tiltServo.detach();   
    
    // Get the size of the image (frame) taken  
    uint16_t jpglen = getFrameLength();
  
    // Read all the data up to # bytes!
    while (jpglen != 0) {
      // read 64 bytes at a time;
      uint8_t *buffer;
      
      //Changing the chunk size to 16 bytes prevents corrupt images.
      
      uint8_t bytesToRead = min(16, jpglen);   // change 32 to 64 for a speedup but may not work with all setups!
      buffer = camera.readPicture(bytesToRead);
      Serial.write(buffer, bytesToRead);
      //Serial.print("Read ");  Serial.print(bytesToRead, DEC); Serial.println(" bytes");
      jpglen -= bytesToRead;
    }
    
    camera.resumeVideo();
    panServo.attach(13);
    tiltServo.attach(12);
    
  } else if (dataIn[0] == 0x24 && dataIn[1] == 0x50 && dataIn[2] == 0x41 && dataIn[3] == 0x4E) {
    //Pan command
    int microseconds = (dataIn[4] * 256) + dataIn[5];
    panServo.writeMicroseconds(microseconds);
    
  } else if (dataIn[0] == 0x24 && dataIn[1] == 0x54 && dataIn[2] == 0x4C && dataIn[3] == 0x54) {
    //Tilt command
    int microseconds = (dataIn[4] * 256) + dataIn[5];
    tiltServo.writeMicroseconds(microseconds);
    
  }
}

uint16_t getFrameLength(){
  return camera.frameLength();
}


