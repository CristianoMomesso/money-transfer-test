# Money transfer api


##Commands to run   

cd money-transfer  
mvn install  
cd money-transfer-api  
mvn wildfly:run  

### Api's url Example And JSON

Put http://localhost:8080/money-transfer-api/rest/account  

{  
  "accountNumber":1,  
  "balance": 12.3,  
  "firstName": "test1",  
  "lastName": "test2",  
  "ownerDocument": "3"  
}  
  
GET http://localhost:8080/money-transfer-api/rest/account/1  
  

POST http://localhost:8080/money-transfer-api/rest/account/transfer  
  
{  
    "accountNumberOrigin": 1,  
    "accountNumberDestination": "2",  
    "valueTransfer": 15  
}  
