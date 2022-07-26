#### get All Meals
`curl -s http://localhost:8080/topjava/rest/user/meals`

#### get Meals 100003
`curl -s http://localhost:8080/topjava/rest/user/meals/100003`

#### filter Meals
`curl -s "http://localhost:8080/topjava/rest/user/meals/filter?startDate=2020-01-30&startTime=07:00:00&endDate=2020-01-31&endTime=11:00:00"`

#### get Meals not found
`curl -s -v http://localhost:8080/topjava/rest/user/meals/100001`

#### delete Meals
`curl -s -X DELETE http://localhost:8080/topjava/rest/user/meals/100004`

#### create Meals
`curl -s -X POST -d '{"dateTime":"2020-02-01T12:00","description":"Created lunch","calories":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/user/meals`

#### update Meals
`curl -s -X PUT -d '{"dateTime":"2020-01-30T07:00", "description":"Updated breakfast", "calories":200}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/user/meals/100003`