# DESAFIO CONCRETE LUIZ HENRIQUE

EndPoints:
Cadastro
https://peaceful-tundra-88559.herokuapp.com/api/users Method: POST -> create a new User
https://peaceful-tundra-88559.herokuapp.com/api/users method: GET -> return a list of all users 

JSON Example 
{
  "name" : "Luiz Henrique",
  "email" : "luizhse@gmail.com",
  "password" : "hunter2",
  "phones" : [
    {
      "number" : "99177-8808",
      "ddd" : "13"
    }
  ]
}   

Login
https://peaceful-tundra-88559.herokuapp.com/api/login Method: POST -> doLogin with email and password
{
  "email" : "luizhse@gmail.com",
  "password" : "hunter2"
}
	  	  
	  	  
