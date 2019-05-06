# payconiq-stock-app
Design Assumption: 
1. Stock class and List 
  1.1 have id (Long), name (String), current value (double), currency and last updated time (timestamp) attributes.
  1.2 Compant Name is considered to be unique. This is a general assumption as in practical scenario no two company has same name. 
  1.3 The currency attribute belongs to a list of values (enum); for the time being I have considered only four currencies viz. USD, GBP, EUR & INR.
  1.4 The list of stocks are stored in a ConcurrentHashMap as frequent update on existing stocks is assumed to be a primary use case  for Stock application.
  1.5 Two concurrent hashMap is used to store stock list; 
    StockByName<String,Stock> -> Here name is the key and value consists of actual stock object
    StockById<Long,String> -> here stock id is the key and value consists of the stock name 
   ---- in practical scenario, stock details will be stored in a database, where id will be primary key and name will be secondary index. As our use cases (get stock, edit stock) primarily will be done by name, So I have considered the name as primary key.  

2. Stock list preparation - I have selected 10 random stocks to filled up while starting up the server.  

3. Custom exception is thrown in case of error. 
  3.1 Trying to udpate a stock which doesn't exist
  3.2 trying to add a stock which is already updated
  3.3 Search a stock which doesn't exist etc.

4. Unit test cases are there for few scenarios - 
  4.1 Update stock details (both positive and negative scenario are tested)
  4.2 Add stock details (both positive and negative scenario are tested)
  
5. Due to time constarint, not able to build the UI component. Instead of that added a swagger UI for visualization. Swagger is accessible by http://<hostname>:8080/swagger-ui.html by this url. If locally deployed hostname should be "localhost".
  
 Please let me know for further queries/explanations.
