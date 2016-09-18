 #include <SoftwareSerial.h>
#include <TinyGPS.h>
#include <dht.h>

dht DHT;
TinyGPS gps;
SoftwareSerial ss(2, 3);

#define DHT11_PIN 4

int measurePin = A0;
int samplingTime = 280;
int deltaTime = 40;
int sleepTime = 9680;
float voMeasured = 0;
float calcVoltage = 0;
float dustDensity = 0;
float gasCount=0; 
float percCarbon=0;
void setup(){
    ss.begin(9600);
  Serial.begin(9600);
  Serial.print(DHT_LIB_VERSION);
 
  pinMode(A1, INPUT);
  pinMode(measurePin,INPUT);
}
 
void loop(){
 
  bool newData = false;
  unsigned long chars;
  unsigned short sentences, failed;

  for (unsigned long start = millis(); millis() - start < 1000;)
  {
    while (ss.available())
    {
      char c = ss.read();
      if (gps.encode(c)) // Did a new valid sentence come in?
        newData = true;
    }
  }

  if (newData)
  {
    float flat, flon;
    unsigned long age;
    gps.f_get_position(&flat, &flon, &age);
    Serial.print("LAT=");
    Serial.print(flat == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : flat, 6);
    Serial.print(" LON=");
    Serial.print(flon == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : flon, 6);
    
  }
            delayMicroseconds(samplingTime);
            voMeasured = analogRead(A0); // read the dust value 
            gasCount = analogRead(A1);
             
            delayMicroseconds(deltaTime);
            delayMicroseconds(sleepTime);
            calcVoltage = voMeasured * (5.0/ 1024);
            dustDensity = 0.17 * calcVoltage - 0.1;
             int chk = DHT.read11(DHT11_PIN);
            percCarbon = (gasCount*100/1024);
            
            Serial.println("AT+CMGF=1"); 
            delay(1500);
            Serial.print("AT+CMGS=\"");     // send the SMS number
            Serial.print("09060827710");//me       //s Serial.print("0906");//royce
            Serial.println("\"");
            while(Serial.read()!='>');
            
            float lat= 123.11;
            float lon= 43.4232;
              
            Serial.print(0001);
            Serial.print("|");        
            Serial.print(lat);
            Serial.print("|");
            Serial.print(lon);
            Serial.print("|");
            Serial.print(dustDensity);
            Serial.print("|");
            Serial.print(percCarbon); 
            Serial.print("|");
            Serial.print(DHT.humidity,1);
            Serial.print("|");
            Serial.println(DHT.temperature,1);
                    
            Serial.write(0x1A);       //sends ++
            Serial.write(0x0D);
            Serial.write(0x0A); 
            delay(10000);


 
}

