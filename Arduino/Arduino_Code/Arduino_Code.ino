#include <SoftwareSerial.h>
SoftwareSerial ArduinoSerial(3, 2); // RX, TX
#include "Wire.h"
#include "SPI.h"  // not used here, but needed to prevent a RTClib compile error
#include "RTClib.h"

RTC_DS3231 RTC;


String Get_Data;

int First_Check_Smoke = 0, First_Check_PIR = 0 , First_Check_Temperature = 0;
int Second_Check_Smoke = 0, Second_Check_PIR = 0, Second_Check_Temperature = 0;

String Transmission_of_Smoke;
String Transmission_of_PIR;
String Transmission_of_Temp;

int Time = 0;

void setup() {
  Serial.begin(115200);
  ArduinoSerial.begin(4800);
  Wire.begin();
  RTC.begin();
  
  /*Init pinMode*/
  pinMode(A0, INPUT); //Smoke
  pinMode(A1, INPUT); //PIR
}
void loop() {

  Transmission_of_Smoke = "A ";
  Transmission_of_PIR = "B ";
  Transmission_of_Temp = "C ";
    
  Transmission_of_Smoke += analogRead(A0);
  Transmission_of_PIR += analogRead(A1);
  Transmission_of_Temp += RTC.getTemperature();
  
  Transmission_of_Smoke += "/";
  Transmission_of_PIR += "/";
  Transmission_of_Temp += "/";
  
  /*This is the part of showing the quantity of all in Serial Monitor.*/
  Serial.println(Transmission_of_Smoke); //Smoke
  Serial.println(Transmission_of_PIR); //PIR
  Serial.println(Transmission_of_Temp); //Temperature
  
  /*This is the part of printing the quantity of all in Arduino.*/
  ArduinoSerial.print(Transmission_of_Smoke);
  delay(1000);
  ArduinoSerial.print(Transmission_of_PIR);
  delay(1000);
  ArduinoSerial.print(Transmission_of_Temp);
  delay(1000);
}  
