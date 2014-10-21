package com.estalkme.tools;

public class ValidateFields {
	
	// ===================================================================== //
	// FIRST & LAST NAME
	// ===================================================================== //
	public static boolean isValidName(String firstName, String lastName) {
		if (validateFirstName(firstName) && validateLastName(lastName)) {
			return true;
		} else {
			return false;
		}
	}

	// Validate First Name
	protected static boolean validateFirstName(String firstName)
	{
		return firstName.matches( "[A-Z][a-zA-Z]*" );
	}
	
	// Validate Last Name
	protected static boolean validateLastName(String lastName)
	{
		return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
	}

}
