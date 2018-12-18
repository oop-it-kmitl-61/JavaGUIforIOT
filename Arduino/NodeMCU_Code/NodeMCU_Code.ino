#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include <SoftwareSerial.h>
#include <String>
SoftwareSerial NodeSerial(D2, D3);


String Get_Data;
String Get_Data_2 = "";
int pos = 0;

String Transmission;

/*Definiting the WIFI and Firebase Info*/
#define WIFI_SSID "KYR_iPhone"
#define WIFI_PASSWORD "kimyora48"

#define FIREBASE_HOST "java-iot-bed31.firebaseio.com"
#define FIREBASE_KEY "XtGbBcI2KSQsZPGBd68pDi6mpBjyiXDozOLgZGvk"


int Time = 0;
int Status = 0;

void setup() {
  /*Init pinMode*/
  pinMode(D2, INPUT);
  pinMode(D3, OUTPUT);

  Serial.begin(115200);
  NodeSerial.begin(4800);
  //Serial.println(WiFi.localIP());

  /*Connecting the WIFI*/
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("Connected: ");
  Serial.println(WiFi.localIP());

  /*Connecting the Firebase*/
  Firebase.begin(FIREBASE_HOST, FIREBASE_KEY);
  Serial.println("Okay");
}

void loop() {
  int Time = 0;
  if (Status == 0) {
    Status = 1;
  }
  else if (Status != 0) {
    while (NodeSerial.available()) {
      char c = NodeSerial.read();
      if (c == '/') {
        if (Get_Data[0] == 'A') {
          Serial.println(Get_Data.substring(Get_Data.lastIndexOf(' ')+1));
          Firebase.setString("Sensor/Data/Smoke", Get_Data.substring(Get_Data.lastIndexOf(' ')+1));
          Get_Data = "";
          Time = 1;
        }
        else if (Get_Data[0] == 'B') {
          Serial.println(Get_Data.substring(Get_Data.lastIndexOf(' ')+1));
          Firebase.setString("Sensor/Data/PIR", Get_Data.substring(Get_Data.lastIndexOf(' ')+1));
          Get_Data = "";
          Time = 2;
        }
        else if (Get_Data[0] == 'C') {
          Serial.println(Get_Data.substring(Get_Data.lastIndexOf(' ')+1));
          Firebase.setString("Sensor/Data/Temperature", Get_Data.substring(Get_Data.lastIndexOf(' ')+1));
          Get_Data = "";
          Time = 0;
        }
      }
      else {
        Get_Data += c;
      }
      delay(5);
    }



    if (Transmission.length() > 0) {
      Serial.println(Transmission);
      NodeSerial.print(Transmission);
      Transmission = "";
    }
  }
}
