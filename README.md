#Api Two
This project is created to fulfill the following requirements:
`2 spring boot apps, one calling another (testable via curl). This would use direct http communication`

You will need to check out both Spring Boot Aplication projects [apione](https://github.com/kundanscorpio/apione "apione") and 
[apitwo](https://github.com/kundanscorpio/apitwo)

The server ports and the URL of the pair application are configured on `application.properties` file:
```
server.port=8082

anotherapiURL=http://localhost:8081/callByAnotherApi
```
You can make a call which results in a call to the other application using the following curl command:
```
$ curl http://localhost:8082/callAnotherApi
```

The output of the curl command will be :
```
{
	"messages": [
		"From apione: I got your call.",
		"Initial call received by apitwo, other api is called and returning now"
	]
}
```