/**
  *In case, if you are directly reading this file, please go through UserInterfaceFinanceManager.java.
  *@package	com.financeManager	->	All the java files are stored in the package com.financeManager (src/com/financeManager/Validation.java)
  *									Class files are compiled in (class/com/financeManager/Validation.class)
  *@class Validation			->	This class contains all the validation methods for Riche Rich Calculator Application.
  *@access public
**/
package com.financeManager;
public class Validation
{
	/**
	  *@method validateMonthlyIncome	-> A method to validate the monthly contribution entered by the user.
	  *@return integer					-> Returns an integer indicating error code for the input value. Error Codes are as follows.
	  *										101 -> Input value is below minimum range of 1000.
	  *										102	-> Input value is not a valid Integer (floats are not considered as valid input).
	  *										100	-> Input value is a valid monthly contribution value.
	  *@access public
	  *@param  param_monthly_income		-> A string paramter to get input of monthly contribution.
	**/
	public int validateMonthlyIncome(String param_monthly_income)
	{
		//A local variable to store parsed value of param_monthly_income which passed as parameter.
		int temp_monthly_income = 0;

		try{

			//Converting the string param_monthly_income to an integer to check its validity.
			temp_monthly_income = Integer.parseInt(param_monthly_income);

			//Checking the minimum range for monthly contribution.
			if( temp_monthly_income <= 999 )
			{
				return 101;
			}
			return 100;
		}
		catch(NumberFormatException numberFormatObject)
		{
			return 102;
		}
	}

	/**
	  *@method validateFutureValue		-> A method to validate the future value entered by the user.
	  *@return integer					-> Returns an integer indicating error code for the input value. Error Codes are as follows.
	  *										101 -> Input value is below minimum range of 1000.
	  *										102	-> Input value is not a valid Integer (floats are not considered as valid input).
	  *										100	-> Input value is a valid monthly contribution value.
	  *@access public
	  *@param  param_monthly_income		-> A string paramter to get input of future value.
	**/
	public int validateFutureValue(String param_future_value)
	{
		int temp_monthly_income = 0;
		try{
			temp_monthly_income = Integer.parseInt(param_future_value);

			if( temp_monthly_income < 999 )
			{
				return 101;
			}
			return 100;
		}
		catch(NumberFormatException numberFormatObject)
		{
			return 102;
		}
	}	
}
//End of class Validation