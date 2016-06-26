package com.iban;

import com.iban.domain.Iban;
import com.iban.domain.enums.CountryCode;

public class App 
{
    public static void main( String[] args )
    {
    	// How to create Iban object from String
//    	 Iban iban = Iban.valueOf("DE89370400440532013000");
//
//    	 System.out.println(iban.getBankCode());
//    	 System.out.println(iban.getAccountNumber());
//    	 System.out.println(iban.getBankCode());
    	 
    	// How to generate Iban
//    	 Iban iban = new Iban.Builder()
//    	                .countryCode(CountryCode.DE)
//    	                .bankCode("37040044")
//    	                .accountNumber("0532013000")
//    	                .build();

   	 // How to generate random Iban
//   	 Iban iban = Iban.random(CountryCode.AT);
//   	 Iban iban = Iban.random();
//   	 Iban iban = new Iban.Builder()
//   	                 .countryCode(CountryCode.AT)
//   	                 .bankCode("19043")
//   	                 .buildRandom();

    	Iban iban = new Iban(CountryCode.AT);
//    	iban = new Iban(CountryCode.DE);
    	iban = new Iban(CountryCode.NL);
    	
    	 System.out.println(iban.toString());
    	 System.out.println(iban.toFormattedString());
    	 

    	 // How to create Iban object from formatted String
//    	 Iban iban = Iban.valueOf("DE89 3704 0044 0532 0130 00", IbanFormat.Default);


    	 // How to validate Iban 
    }
}
