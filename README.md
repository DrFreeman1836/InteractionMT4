# InteractionMT4


Приложение для записи и графического анализа тиков из торгового терминала Meta Trader.
Основной стек: Spring Boot, SwingWrapper, MySQL.  
Сборка: mvn package  
Базовый URL: http://localhost:6363/api/v1/mt  
  
Спецификация:  
POST /  
  добавить цены в БД  
    параметры:  
    priceAsk - цена аск  
    priceBid - цена бид  
    flag - флаг прохождения порога тиковой активности  
        
POST /  
  добавить цены в БД  
    параметры:  
    priceAsk - цена аск  
    priceBid - цена бид  
    flag - флаг прохождения порога тиковой активности    
