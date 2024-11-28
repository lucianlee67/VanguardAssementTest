1. execute game_sales_sql.txt to create table to mysql database
2. update application.yml, change datasource.url to your database, change datasource.username and  datasource.password
3. swagger is provided, http://localhost:8088/swagger-ui/index.html
4. POST localhost:8088/import
   curl --location 'localhost:8088/import' \
   --form 'file=@"/game_sales.csv"'

5. GET localhost:8088/getGameSales
   curl --location --request GET 'localhost:8088/getGameSales' \
   --header 'Content-Type: application/json' \
   --data '{
       "params": {
       "pageSize": 100,
       "pageNum": 1,
       "beginTime": "2024-04-01",
       "endTime": "2024-04-30",
       "moreSalePrice": 0,
       "lessSalePrice": 100
       }
   }'

6. GET localhost:8088/getTotalSales
   curl --location --request GET 'localhost:8088/getTotalSales' \
   --header 'Content-Type: application/json' \
   --data '{
       "gameNo": 77,
       "params": {
           "beginTime": "2024-04-01",
           "endTime": "2024-04-30"
       }
   }'
