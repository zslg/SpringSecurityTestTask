
Base user admin,to get token use admin info:
POST /login HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
{
	"username":"admin",
	"password":"1234"
}