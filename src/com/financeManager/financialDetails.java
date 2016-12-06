/**
  *In case, if you are directly reading this file, please go through UserInterfaceFinanceManager.java.
  *@package	com.financeManager	->	All the java files are stored in the package com.financeManager (src/com/financeManager/financialDetails.java)
  *									Class files are compiled in (class/com/financeManager/financialDetails.class)
  *@class   financialDetails	->	This class contains all the members and methods to calculate financial details for the user.
  *@access  public
**/
package com.financeManager;

public class financialDetails{
	/**
	  *@member	 monthly_contribution	->	A private float member variable to store monthly contribution of the user for his/her savings.
	  *@member   number_of_years		->	A private integer member variable to store number of years user wills to invest for.
	  *@member	 risk_level				->	A private integer member variable to store the type of risk involved in investment.
	  *@member	 future_value			->	A private float member variable to store the expected future value of the investment.
	  *@constant ROI_8_PERCENT			->  An integer constant indicating 8 percent rate of interest.
	  *@constant ROI_10_PERCENT			->  An integer constant indicating 10 percent rate of interest.
	  *@constant ROI_12_PERCENT			->  An integer constant indicating 12 percent rate of interest.
	  *@constant ROI_14_PERCENT			->  An integer constant indicating 14 percent rate of interest.
	**/

	private float monthly_contribution;
	private int number_of_years;
	private int risk_level;
	private float future_value;
	private final int ROI_8_PERCENT = 8;
	private final int ROI_10_PERCENT = 10;
	private final int ROI_12_PERCENT = 12;
	private final int ROI_14_PERCENT = 14;

	/**
	  *@constructor financialDetails	->	A constructor to initialize all the instance variables to their default values.
	  *@access		public
	**/
	public financialDetails()
	{
		monthly_contribution = 0.0f;
		number_of_years = 0;
		risk_level = 0;
		future_value = 0.0f;
	}

	/**
	  *@method getMonthlyContribution	->	A method  to provide access to private instance variable monthly_contribution for other classes.
	  *@return float
	  *@access public
	**/
	public float getMonthlyContribution()
	{
		return 	this.monthly_contribution;
	}

	/**
	  *@method getNumberOfYears	->	A method to provide access to private instance variable number_of_years for other classes.
	  *@return integer
	  *@access public
	**/
	public int getNumberOfYears()
	{
		return this.number_of_years;
	}

	/**
	  *@method getRiskLevel	->	A method to provide access to private instance variable risk_level for other classes.
	  *@return integer
	  *@access public
	**/
	public int getRiskLevel()
	{
		return this.risk_level;
	}

	/**
	  *@method getFutureValue	->	A method to provide access to private instance variable future_value for other classes.
	  *@return float
	  *@access public
	**/
	public float getFutureValue()
	{
		return this.future_value;
	}

	/**
	  *@method setMonthlyContribution	  ->	A method to set private instance variable monthly_contribution for other classes.
	  *@return void
	  *@access public
	  *@param  param_monthly_contribution ->	A float function paramter to store value of monthly contribution  passed by calling objects.	
	**/
	public void setMonthlyContribution(float param_monthly_contribution)
	{
		 	this.monthly_contribution = param_monthly_contribution;
	}

	/**
	  *@method setNumberOfYears			  ->	A method to set private instance variable number_of_years for other classes.
	  *@return void
	  *@access public
	  *@param  param_number_of_years	  ->	An integer function paramter to store value of number of years passed by calling objects.	
	**/
	public void setNumberOfYears(int param_number_of_years)
	{		 
			this.number_of_years = param_number_of_years;
	}

	/**
	  *@method setRiskLevel			 	  ->	A method to set private instance variable risk_level for other classes.
	  *@return void
	  *@access public
	  *@param  param_risk_level			  ->	An integer function paramter to store value of risk level  passed by calling objects.	
	**/
	public void setRiskLevel(int param_risk_level)
	{
			this.risk_level = param_risk_level;
	}

	/**
	  *@method setFutureValue			  ->	A method to set private instance variable future_value for other classes.
	  *@return void
	  *@access public
	  *@param  param_future_value		  ->	An integer function paramter to store future value passed by calling objects.	
	**/
	public void setFutureValue(float param_future_value)
	{
			this.future_value = param_future_value;
	}

	/**
	  *@method getRateOfInterest		  ->	A method to get rate of interest for a specified risk level. It does so assigning
	  *											a constant value based on risk level set by user.
	  *@return integer
	  *@access public
	**/
	public int getRateOfInterest()
	{
		/**
		  *@local	rate_of_interest	->	A local integer variable to temporarily store interest rates to be returned by the function.
		**/
		int rate_of_interest = 0;
		switch(this.risk_level)
		{
			case 1:
					rate_of_interest = ROI_8_PERCENT;
					break;
			case 2:
					rate_of_interest = ROI_10_PERCENT;
					break;
			case 3:
					rate_of_interest = ROI_12_PERCENT;
					break;
			case 4:
					rate_of_interest = ROI_14_PERCENT;
					break;
			default:
					rate_of_interest = 0;
					break;
		}
		return	rate_of_interest;
	}

	/**
	  *@method determineFutureValue		  ->	A method to determine future value of the investment for the calling object.
	  *@return void
	  *@access public
	**/
	public void determineFutureValue()
	{
		/**
		  *@local per_year			->	An amount of money invested by the user per year (per_month*12).
		  *@local next_year			->	The accured amount in addition with previous years interest.
		  *@local at_the_end		-> 	A local variable to store the income at the end of a year.
		  *@local rate_of_interest	->	The rate of interest retured by getRateOfInterest method.
		**/
		float per_year = getMonthlyContribution() * 12;
		float next_year = per_year;
		float at_the_end = 0 ;
		int rate_of_interest = getRateOfInterest();

			for( int initialization_variable = 0 ; initialization_variable < getNumberOfYears() ; initialization_variable++ )
			{
				at_the_end = ((next_year) + (next_year * rate_of_interest / 100));
				next_year = at_the_end + per_year;
			}
		//Invoking setFutureValue method to store the calculated future value in the private instance variable future_value.
		setFutureValue(at_the_end);
	}

	/**
	  *@method determinePrincipal	->	A method to determine monthly contribution of the investment for the calling object.
	  *@return void
	  *@access public
	**/
	public void determinePrincipal()
	{
		/**
		  *@local monthly_contribution			->	An amount of money to be invested by the user per month.
		  *@local at_the_end					-> 	A local variable to store the income at the end of a year.
		  *@local rate_of_interest				->	The rate of interest retured by getRateOfInterest method.
		**/
			float monthly_contribution = 0;
			float at_the_end = getFutureValue();
			int rate_of_interest = getRateOfInterest();
				
			monthly_contribution = (at_the_end * rate_of_interest)/(1 - (1 / ((1+rate_of_interest))));

			//Invoking setMonthlyContribution method to store the calculated monthly contribution in the private instance variable monthly_contribution.
			setMonthlyContribution(monthly_contribution);
	}
}