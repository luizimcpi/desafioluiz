# API CADASTRO DE USUARIOS E LOGIN

EndPoints:
Cadastro
http://localhost:8080/api/users Method: POST -> create a new User
http://localhost:8080/api/users method: GET -> return a list of all users 
http://localhost:8080/api/users/{id} method: GET -> return a specific user pass the token in the header


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

´´´
http://localhost:8080/api/users/login Method: POST -> doLogin with email and password
{
  "email" : "luizhse@gmail.com",
  "password" : "hunter2"
}
´´´

Para Rodar a aplicação em localhost basta executar a classe DesafioluizApplication.java

ou

gradle bootRun
	  	  
Acessar o h2 console http://localhost:8080/h2-console/ colocar no campo  JDBC:URL ->  jdbc:h2:mem:testdb