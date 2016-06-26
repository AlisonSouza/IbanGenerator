# IbanGenerator

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
