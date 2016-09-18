#define echoPin 4
#define trigPin 3


void setup()
{
Serial.begin(9600);
pinMode(trigPin, OUTPUT);
pinMode(echoPin, INPUT);
}

void loop()
{
float distance,duration;
digitalWrite(trigPin, HIGH);
//delay(100);
digitalWrite(trigPin, LOW);
duration=pulseIn(echoPin, HIGH);
distance=(((duration/2)/29.1)+8)*0.0328084;
Serial.print(distance);
//Serial.print("cm");
Serial.println("");
delay(1000);
}
