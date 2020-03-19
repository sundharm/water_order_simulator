# water_order_simulator

Simple Rest API to simulate a water ordering system

1. Go to the project folder in terminal

2. Enter the following command to build the docker image
      docker build -f Dockerfile -t water_simulator .

3. Run the docker image using the below command
      docker run -p 8080:8080 water simulator
      
The end point URLs are as follows :

1.  Placing a new order
    http://localhost:8080/placeOrder 

    Request body: 
    { 
        "orderId": 1, 
        "farmId": 101, 
        "duration": 10000 
    }
    
2. To view the orders present in the system
    
   All orders : http://localhost:8080/viewOrders?search=all
   
   Search by order ID : http://localhost:8080/viewOrders?search=order_id&searchValue=1
   
   Search by Farm ID :  http://localhost:8080/viewOrders?searchBy=farm_id&searchValue=101
   
3. To cancel an order
   http://localhost:8080/cancelOrder?order_id=1
  
