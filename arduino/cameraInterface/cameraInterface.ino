#include <SoftwareSerial.h>

#define txPin 3
#define rxPin 2

SoftwareSerial spriteSerial(rxPin, txPin);

void setup(){
  Serial.begin(38400);
  spriteSerial.begin(38400);  
}

void loop(){
  //See if data is available from PC
  if (Serial.available() > 0){
   while (Serial.available() > 0){
    Serial.write(Serial.read());
   }
  }
  //if (spriteSerial.available() > 0){
   //while (spriteSerial.available() > 0){
    //Serial.write(spriteSerial.read());
   //} 
  //}
}
