# DESAFIO CONCRETE LUIZ HENRIQUE

A aplicação está hospedada no Heroku utilizando a seguinte URL:
https://peaceful-tundra-88559.herokuapp.com/

EndPoints:
Cadastro
https://peaceful-tundra-88559.herokuapp.com/api/users Method: POST -> create a new User
https://peaceful-tundra-88559.herokuapp.com/api/users method: GET -> return a list of all users 
https://peaceful-tundra-88559.herokuapp.com/api/users/{id} method: GET -> return a specific user pass the token in the header


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

Para Rodar a aplicação em localhost basta executar a classe DesafioluizApplication.java
	  	  
Acessar o h2 console http://localhost:8080/h2-console/ colocar no campo  JDBC:URL ->  jdbc:h2:mem:testdb