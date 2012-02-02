#include <SoftwareSerial.h>

SoftwareSerial spriteSerial(2,3);

void setup(){
  Serial.begin(38400);
  spriteSerial.begin(38400);  
}

void loop(){
  //See if data is available from PC
  if (Serial.available() > 0){
   while (Serial.available() > 0){
    spriteSerial.write(Serial.read());
   }
  }
  if (spriteSerial.available() > 0){
   while (spriteSerial.available() > 0){
    Serial.write(spriteSerial.read());
   } 
  }
}
