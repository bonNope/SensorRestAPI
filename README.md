# SensorRestAPI
## **About**    
**English**  
This is my first Rest API application, this application accepts requests in JSON format from sensors for measuring temperature and determining rainy weather.   
There are 4 addresses to which requests can be made:  
- `/measuremets/add` adding a new measurement from the sensor  
  accepted format:
    ```
    {
      "value": 24,
      "raining": true,
      "sensor":{
        "name": "test sensor"
      }
    }
- `/measurements`  getting a list of all dimensions
- `/measurements/rainyDaysCount` getting the number of registered rainy days
- `/sonsors/registration` to register a new sensor in the system  
  accepted format:
  ```
  {
    name:"test sensor"
  }
  ```
  **Russian**  
это мое первое Rest API приложение, это приложение принимает запросы в формате JSON от условных датчиков измерения температуры и определения дождливой погоды.  
Есть 4 адреса, на которые можно сделать запросы:  
- `/measuremets/add` для добавляения нового измерения от датчика  
  принимаемый формат:
    ```
    {
      "value": 24,
      "raining": true,
      "sensor":{
        "name": "test sensor"
      }
    }
- `/measurements`  получаем список всех измерений
- `/measurements/rainyDaysCount` получаем количество зарегестрированных дождливых дней
- `/sonsors/registration` для регистрации нового сенсора в системе  
  принимаемый формат:
  ```
  {
    name:"test sensor"
  }
  
