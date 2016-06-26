# IbanGenerator

This first version is supported only for three countries Germany, Austria and Netherlands.

###Iban example:

```java

//Generator Iban for Germany
IIbanService iIbanService = new IbanServiceImpl();
String iban = iIbanService.generateRandomIban("DE");

//Generator Iban for Austria
IIbanService iIbanService = new IbanServiceImpl();
String iban = iIbanService.generateRandomIban("AT");

//Generator Iban for Netherlands
IIbanService iIbanService = new IbanServiceImpl();
String iban = iIbanService.generateRandomIban("NL");

```

###Architecture Proposal

My idea is to create a Microservice using SpringBoot and Docker to expose the service and be consumed as an online API.

###To Do

Need to create a unit test to all domain, was only created integration test.
