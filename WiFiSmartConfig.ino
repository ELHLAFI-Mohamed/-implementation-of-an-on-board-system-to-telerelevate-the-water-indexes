#include <Wire.h>
#include <WiFi.h>
const char* ssid     = "Wifi";
const char* password = "12345678";
String num_secteur="1",num_cpt="2";
int index_cpt;
int sensor=19;
// Le capteur de débit à effet hall émet environ 4,5 impulsions par seconde par seconde.
// litre / minute de débit.
float calibrationFactor = 4.5;
int pulseCount;
float flowRate;
int litre ;
unsigned long oldTime;
WiFiServer server(80);
void IRAM_ATTR isr() {
pulseCount++;
}

void setup() {
 
  Serial.begin(115200);
   pinMode(sensor, INPUT_PULLUP);
   pulseCount=0;
   flowRate=0;
   litre=0;
   index_cpt=0;
   oldTime=0;
 attachInterrupt(sensor,isr, FALLING);
  Wire.begin();
  Serial.print("Connecting to ");
    Serial.println(ssid);

    WiFi.begin(ssid, password);;

    while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(".");
    }

    Serial.println("");
    Serial.println("WiFi connected.");
    Serial.println("IP address: ");
    Serial.println(WiFi.localIP());
    
    server.begin();
    Serial.println("le serveur a commence");

}
void loop() {

  
  WiFiClient client = server.available();   // listen for incoming clients
 if (client) {                             // if you get a client,
    Serial.println("New Client.");           // print a message out the serial port
    delay(10);
      while (client.connected()) {  
        client.print(num_secteur+";"+num_cpt+";"+String(index_cpt));
       client.print("\n");  
       if((millis() - oldTime) > 1000){
 detachInterrupt(sensor);

 flowRate = ((1000.0 / (millis() - oldTime)) * pulseCount) / calibrationFactor;
 oldTime = millis();
   // Divide the flow rate in litres/minute by 60 to determine how many litres have
    // passed through the sensor in this 1 second interval,
 litre=(flowRate/60);
 index_cpt+=litre;
 client.print(num_secteur+";"+num_cpt+";"+String(index_cpt));
       client.print("\n");  
       
 // Reset the pulse counter so we can start incrementing again
    pulseCount = 0;
    
 // Enable the interrupt again now that we've finished sending output
    attachInterrupt(sensor, isr, FALLING);
    
       }
       
    
   }
   
      
      }
       client.stop();
    Serial.println("Client Disconnected.");

  }
