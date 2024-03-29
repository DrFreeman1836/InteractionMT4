//+------------------------------------------------------------------+
//|                                                         frog.mq4 |
//|                        Copyright 2022, MetaQuotes Software Corp. |
//|                                             https://www.mql5.com |
//+------------------------------------------------------------------+
 
#property copyright "Copyright 2022, MetaQuotes Software Corp."
#property link      "https://www.mql5.com"
#property version   "1.00"
#property strict
 
extern double checkInSecond = 1;
extern int maxTick = 18;
 
input string URL="http://localhost/api/v1/mt";
input bool checkMessage = true;
 
int countTicks;
 
//+------------------------------------------------------------------+
//| Expert initialization function                                   |
//+------------------------------------------------------------------+
int OnInit()
{
//--- create timer
   EventSetTimer(checkInSecond);
//---
   return(INIT_SUCCEEDED);
}
//+------------------------------------------------------------------+
//| Expert deinitialization function                                 |
//+------------------------------------------------------------------+
void OnDeinit(const int reason)
{
//--- destroy timer
   EventKillTimer();
   
}
//+------------------------------------------------------------------+
//| Expert tick function                                             |
//+------------------------------------------------------------------+
void OnTick()
{
 
   countTicks++;
   message(Ask, Bid, false);
 
}
 
//+------------------------------------------------------------------+
void OnTimer(){
 
   if(countTicks > maxTick){
      message(Ask, Bid, true);
   }
   Comment(countTicks);
   countTicks = 0;
 
}
//+------------------------------------------------------------------+
void message(double priceAsk, double priceBid, bool flag)
{
if (!checkMessage) return;
int res=0;
   char post[];
   char result[];
   string headers;
   string url = URL + "?priceAsk=" + NormalizeDouble(priceAsk, 5) + "&priceBid=" + NormalizeDouble(priceBid, 5) + "&flag=" + flag;
   //Comment(url); 
     for(int i = 0; i < 2; i++){
      int res=WebRequest("POST",url,NULL,NULL,5000,post,1000,result,headers);
      if(res == 200){
         return;
      }else{
         Print("Ошибка в WebRequest. Код ошибки  =",GetLastError());
      }
     }
 
}
//+------------------------------------------------------------------+
