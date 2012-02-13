
int dataIn[4];
int dataIndex = 0;

void setup(){
  Serial.begin(38400);  
}

void loop(){
  if (Serial.available() > 0){
    while(Serial.available() > 0){
      //Read in a byte
      dataIn[dataIndex] = Serial.read();
      //Increment the array index
      dataIndex++;
      //Reset the array index
      if (dataIndex == 4){
        dataIndex = 0;
        //Compare the data array
        evaluateData();
      }      
    } 
  }    
}

void evaluateData(){
  if (dataIn[0] == 0x24 && dataIn[1] == 0x52 && dataIn[2] == 0x53 && dataIn[3] == 0x54){
    Serial.print("Arduino Reset");    
  } else if (dataIn[0] == 0x24 && dataIn[1] == 0x56 && dataIn[2] == 0x45 && dataIn[3] == 0x52){
    Serial.print("Arduino Version");
  } else if (dataIn[0] == 0x24 && dataIn[1] == 0x53 && dataIn[2] == 0x4E && dataIn[3] == 0x50){
    Serial.print("Arduino Snap"); 
  }  else if (dataIn[0] == 0x24 && dataIn[1] == 0x53 && dataIn[2] == 0x49 && dataIn[3] == 0x5A){
    Serial.print("Arduino Size");   
  }  
}


