/**
  *Execution starts from the main function of this file.
  *@package	com.financeManager			->	All the java files are stored in the package com.financeManager (src/com/financeManager/UserInterfaceFinanceManager.java)
  *											Class files are compiled in (class/com/financeManager/UserInterfaceFinanceManager.class)
  *@class   UserInterfaceFinanceManager	->	This class deals with all the User Interface components for a Swing application.
  *@access  public
**/

package com.financeManager;

//**************IMPORTS**************//
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.imageio.ImageIO;
//*********************************//

public class UserInterfaceFinanceManager{

	/**
	  *@member  monthly_contribution, future_value, risk_level, number_of_years	->	Instance variables to store users financial details.
	  *@object	validationObject		-> 	Object of class Validation to call validation functions on user input.
	  *@object	financialDetailsObject	->	Object of class financial details to access its functions.
	  *@member 	errorCode				->	Store error codes returned by validation functions.
	**/
	private float monthly_contribution = 0.0f;
	private float future_value = 0.0f;
	private int risk_level = 0;
	private int number_of_years = 0;

	Validation validationObject = new Validation();
	financialDetails financialDetailsObject = new financialDetails();
	public int errorCode = 0;

	/**
	  *@guiComponent	titleLabel, monthlyContributionLabel, numberOfYearLabel, riskLevelLabel, imageLabel, futureValueLabel,
	  *		 			monthlyContributionValidationLabel, futureValueValidationLabel	-> Labels for various fields and validations.
	  *@guiComponent 	monthlyContributionTextField, futureValueTextField				-> Textboxes for financial details.
	  *@guiComponent 	numberOfYearComboBox, riskLevelComboBox							-> Comboboxes to get year and risk level.	
	  *@guiComponent	saveButton														-> Save button to create and store data in a file.
	  *@guiComponent	moneyImage														-> Display image on the UI.
	  *@member			numberOfYearChoice[]											-> Array to populate no of years combobox
	  *@member			riskLevelChoice[]												-> Array to populate risk level combobox
	**/
	private JLabel titleLabel, monthlyContributionLabel, numberOfYearLabel, riskLevelLabel, imageLabel, futureValueLabel, monthlyContributionValidationLabel, futureValueValidationLabel;
	private JTextField monthlyContributionTextField, futureValueTextField;
	private JComboBox numberOfYearComboBox, riskLevelComboBox;
	private JButton saveButton;
	private ImageIcon moneyImage;
	private final String[] numberOfYearsChoice = {"5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
	private final String[] riskLevelChoice = {"1","2","3","4"};

	/**
  	  *@constructor		UserInterfaceFinanceManager	->	A public constructor of class UserInterfaceFinanceManager to set UI components on the screen.
	**/
	UserInterfaceFinanceManager()
	{
		/**
		  *@guiComponent jFrameObject												-> creating Object of JFrame by calling its parameterized constructor.
		  *@guiComponent panelNorth, panelWest, panelCenter, panelEast, panelSouth  -> creating panels to placed in the frame.
		**/
		JFrame jFrameObject = new JFrame("Richie Rich Calculator");
		
		JPanel panelNorth = new JPanel();
		JPanel panelWest = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelEast = new JPanel();
		JPanel panelSouth = new JPanel();

		//Setting layout for the entire frame.
		jFrameObject.getContentPane().setLayout(new BorderLayout());

		//Setting layout for the panels to be placed inside the frame.
		panelNorth.setLayout(new FlowLayout());	
		panelWest.setLayout(new GridLayout(15,1));
		panelCenter.setLayout(new FlowLayout());
		panelEast.setLayout(new GridLayout(15,1));
		panelSouth.setLayout(new FlowLayout());

		//Defining the window size
		jFrameObject.setSize(800,500);
		jFrameObject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Creating title by printing using JLabel
		titleLabel = new JLabel("!!Richie Rich Calculator!!");

		//Creating monthly contribution label by printing using JLabel
		monthlyContributionLabel = new JLabel("Monthly Contribution");
		//Setting up the validation label of monthly contribution to empty
		monthlyContributionValidationLabel = new JLabel("");
		//Creaating a  textfield for monthly contribution
		monthlyContributionTextField = new JTextField(10);
		//Adding a document listener for the monthly contribution textfield
		monthlyContributionTextField.getDocument().addDocumentListener(new monthlyContributionListener());



		numberOfYearLabel = new JLabel("Number of Years");
		numberOfYearComboBox = new JComboBox(numberOfYearsChoice);
		number_of_years = Integer.parseInt(numberOfYearComboBox.getSelectedItem().toString()); 
		//Assigning a an action listener to the number of years combobx
		numberOfYearComboBox.addActionListener( new numberOfYearsListener());


		riskLevelLabel = new JLabel("Risk Level");
		riskLevelComboBox = new JComboBox(riskLevelChoice);		
		risk_level = Integer.parseInt(riskLevelComboBox.getSelectedItem().toString()); 
		//Assigning a an action listener to the risk level
		riskLevelComboBox.addActionListener( new riskLevelListener());
		
		moneyImage = new ImageIcon("Bike.jpg");
		/*moneyImage[0] = new ImageIcon("../../../assets/images/Bike.jpg");
		moneyImage[1] = new ImageIcon("../../../assets/images/car.jpg");
		moneyImage[2] = new ImageIcon("../../../assets/images/gold.jpg");
		moneyImage[3] = new ImageIcon("../../../assets/images/house.png");*/
		imageLabel = new JLabel(moneyImage);


		futureValueLabel = new JLabel("Future Value");
		futureValueTextField = new JTextField(10);
		futureValueValidationLabel = new JLabel("");
		//Adding a document listener to the future value textfield
		futureValueTextField.getDocument().addDocumentListener(new futureValueListener());

		//Creating a save button
		saveButton = new JButton("Save");
		//Assigning an action listener to get the onClick events on the button. Using anonymous classes
		/**
		   *@anonymousClass	A class to handle button click events.
		   *@method 		actionPerformed	->	A mehod of interface ActionListener implemented to handle click events.
		   *									It invokes storeCurrentData to store the output in a file.
		   *@return 		void
		**/
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				/**
				  *@local goal_name	->	A string variable to store goal name of curernt finanacial details.
				**/
				String goal_name = JOptionPane.showInputDialog("Enter File name");
				try{
					storeCurrentData(goal_name);	
				}catch(IOException ioExceptionObject){
				//	ioExceptionObject.printStackTrace();
				}
			}
		});

		//Adding element in the North panel
		panelNorth.add(titleLabel);

		//Adding elements in the West panel
		panelWest.add(monthlyContributionLabel);
		panelWest.add(monthlyContributionTextField);
		panelWest.add(monthlyContributionValidationLabel);
		panelWest.add(numberOfYearLabel);
		panelWest.add(numberOfYearComboBox);
		panelWest.add(riskLevelLabel);
		panelWest.add(riskLevelComboBox);

		//Adding elements in the Center panel
		panelCenter.add(imageLabel);
		
		//Adding elements to the East panel
		panelEast.add(futureValueLabel);
		panelEast.add(futureValueTextField);
		panelEast.add(futureValueValidationLabel);

		//Adding element to the South panel
		panelSouth.add(saveButton);

		//Adding panels to their respective areas in a border layout.
		jFrameObject.add(panelNorth,BorderLayout.NORTH);
		jFrameObject.add(panelWest,BorderLayout.WEST);
		jFrameObject.add(panelCenter,BorderLayout.CENTER);
		jFrameObject.add(panelEast,BorderLayout.EAST);
		jFrameObject.add(panelSouth,BorderLayout.SOUTH);

		jFrameObject.setVisible(true);
	}

	/**
	  *@innerClass	monthlyContributionListener	->	Inner class to implement the methods of a interface Document listener.
	  *@access		private
	**/
	private class monthlyContributionListener implements DocumentListener
	{
		/**
		  *@method	insertUpdate		->	A method of interface DocumentLsitener to grab all the input events in the monthly contribution text field.
		  *@return	void
		  *@access	public
		  *@param	documentEventObject	->	An object of DocumentEvent class passed as an arguement when the event occurs.
		**/
		public void insertUpdate(DocumentEvent documentEventObject)
		{
				//Invoking handleValidationMonthlyContribution method to validate the input
				int temp_monthly_contribution = handleValidationMonthlyContribution();
				
				//Check if a valid status signal.	0 symbols invalid	
				if(temp_monthly_contribution != 0)
				{
					monthly_contribution =  temp_monthly_contribution;
					//Setting the value of the of future value textfield
					futureValueTextField.setText(String.valueOf(makeCallToFindFutureValue()));
					//changeImage();
				}
		}

		/**
		  *@method	removeUpdate		->	A method of interface DocumentLsitener to grab all the remove (backspance) events in 
		  *									the monthly contribution text field.
		  *@return	void
		  *@access	public
		  *@param	documentEventObject	->	An object of DocumentEvent class passed as an arguement when the event occurs.
		**/
		public void removeUpdate(DocumentEvent documentEventObject)
		{
				int temp_monthly_contribution = handleValidationMonthlyContribution();
				
				if(temp_monthly_contribution != 0)
				{
					monthly_contribution =  temp_monthly_contribution;
					futureValueTextField.setText(String.valueOf(makeCallToFindFutureValue()));
					//changeImage();
				}
		}
		/**
		  *@method	changedUpdate		->	A method of interface DocumentLsitener. An empty method.
		  *@return	void
		  *@access	public
		  *@param	documentEventObject	->	An object of DocumentEvent class passed as an arguement when the event occurs.
		**/
		public void changedUpdate(DocumentEvent documentEventObject){}

	}//End of inner class monthlyContributionListener

	/**
	  *@innerClass	futureValueListener	->	Inner class to implement the methods of a interface Document listener.
	  *@access		private
	**/
	private class futureValueListener implements DocumentListener
	{
		/**
		  *@method	insertUpdate		->	A method of interface DocumentLsitener to grab all the input events in the future value text field.
		  *@return	void
		  *@access	public
		  *@param	documentEventObject	->	An object of DocumentEvent class passed as an arguement when the event occurs.
		**/
		public void insertUpdate(DocumentEvent documentEventObject)
		{
				int temp_future_value = handleValidationFutureValue();

				if(temp_future_value != 0)
				{
					future_value =  temp_future_value;
					monthlyContributionTextField.setText(String.valueOf(makeCallToFindMonthlyContribution()));
					//changeImage();
				}

		}
		/**
		  *@method	removeUpdate		->	A method of interface DocumentLsitener to grab all the remove (backspance) events in 
		  *									the future value text field.
		  *@return	void
		  *@access	public
		  *@param	documentEventObject	->	An object of DocumentEvent class passed as an arguement when the event occurs.
		**/
		public void removeUpdate(DocumentEvent documentEventObject)
		{
				int temp_future_value = handleValidationFutureValue();
				
				if(temp_future_value != 0)
				{
					future_value =  temp_future_value;
					monthlyContributionTextField.setText(String.valueOf(makeCallToFindMonthlyContribution()));
					//changeImage();
				}
		}
		/**
		  *@method	changedUpdate		->	A method of interface DocumentLsitener. An empty method.
		  *@return	void
		  *@access	public
		  *@param	documentEventObject	->	An object of DocumentEvent class passed as an arguement when the event occurs.
		**/
		public void changedUpdate(DocumentEvent documentEventObject){}
	}//End of inner class futureValueListener

	/**
	  *@innerClass	numberOfYearsListener	->	Inner class to implement the methods of a interface Action Listener. The events relating to
	  *											number of years combobox are handled here.
	  *@access		private
	**/
	private class numberOfYearsListener implements ActionListener
	{
		/**
		  *@method	actionPerformed		->	A method of interface ActionListener to grab all the change events in number of years combobox
		  *@return	void
		  *@access	public
		  *@param	actionEventObject	->	An object of ActionEvent class passed as an arguement when the event occurs.
		**/
		public void actionPerformed(ActionEvent actionEventObject)
		{
			//Getting the current value of number of years combobox.
			number_of_years = Integer.parseInt(numberOfYearComboBox.getSelectedItem().toString());

			//Setting future value field to the returned value by makeCallToFindFutureValue method
			futureValueTextField.setText(String.valueOf(makeCallToFindFutureValue()));
		}
	}//End of inner class numberOfYearsListener

	/**
	  *@innerClass	riskLevelListener	->	Inner class to implement the methods of a interface Action Listener. The events relating to
	  *										risk level combobox are handled here.
	  *@access		private
	**/
	private class riskLevelListener implements ActionListener
	{
		/**
		  *@method	actionPerformed		->	A method of interface ActionListener to grab all the change events in risk level combobox.
		  *@return	void
		  *@access	public
		  *@param	actionEventObject	->	An object of ActionEvent class passed as an arguement when the event occurs.
		**/
		public void actionPerformed(ActionEvent actionEventObject)
		{
			//Getting the current value of risk level combobox.
			risk_level = Integer.parseInt(riskLevelComboBox.getSelectedItem().toString());

			//Setting future value field to the returned value by makeCallToFindFutureValue method
			futureValueTextField.setText(String.valueOf(makeCallToFindFutureValue()));
		}
	}//End of inner class riskLevelListener

	/**
	  *@method	changeImage		->	A method to change the center image when the future value changes.
	  *@return	void
	  *@access	public
	**/
	public void changeImage()
	{
		if(future_value < 200000 )
		{
			//imageLabel.setIcon(moneyImage[0]);
		}
		else if(future_value >= 200000 && future_value < 1200000)
		{
			//imageLabel.setIcon(moneyImage[1]);
		}
		else if(future_value >= 1200000 && future_value < 3000000)
		{
			//imageLabel.setIcon(moneyImage[2]);
		}
		else if(future_value > 3000000)
		{
			//imageLabel.setIcon(moneyImage[3]);
		}
	}
	/**
	  *@method	handleValidationMonthlyContribution		->	A method to call validation methods for monthly contribution texfields.
	  *@return	integer
	  *@access	public
	**/
	public int handleValidationMonthlyContribution()
	{
				/**
	  			  *@local local_monthly_contribution_value	-> String variable to store  monthly contribution value of textfield.
	  			 **/
				String local_monthly_contribution_value = monthlyContributionTextField.getText();

				 if(!(local_monthly_contribution_value.isEmpty()))
				 {
				 	//Invoking validateMonthlyIncome method to validate the value in the monthly contribution textfield
				 	errorCode = validationObject.validateMonthlyIncome(local_monthly_contribution_value);
				 	
				 	switch(errorCode)
				 	{
				 		case 101:
				 				//Invoking printErrorLabelForMonthlyContribution with errorCode to print respective messages in the UI
				 				printErrorLabelForMonthlyContribution(errorCode);
				 				break;
				 		case 102:
				 				printErrorLabelForMonthlyContribution(errorCode);
				 				break;
				 		case 100:
				 				printErrorLabelForMonthlyContribution(errorCode);
				 				return Integer.parseInt(monthlyContributionTextField.getText());
				 	}	
				 }
				 return 0;
	}

	/**
	  *@method	handleValidationFutureValue		->	A method to call validation methods for future value texfields.
	  *@return	integer
	  *@access	public
	**/
	public int handleValidationFutureValue()
	{
				String local_future_value = futureValueTextField.getText();
				 if(!(local_future_value.isEmpty()))
				 {
				 	errorCode = validationObject.validateFutureValue(local_future_value);

				 	switch(errorCode)
				 	{
				 		case 101:
				 				printErrorLabelForFutureValue(errorCode);
				 				break;
				 		case 102:
				 				printErrorLabelForFutureValue(errorCode);
				 				break;
				 		case 100:
				 				printErrorLabelForFutureValue(errorCode);
				 				return Integer.parseInt(futureValueTextField.getText());
				 	}	
				 }
				 return 0;
	}

	/**
	  *@method	printErrorLabelForMonthlyContribution		->	A method to call validation methods for future value texfields.
	  *@return	void
	  *@access	public
	**/
	public void printErrorLabelForMonthlyContribution(int param_error_code)
	{
		switch(param_error_code)
		{
			case 101:
					monthlyContributionValidationLabel.setText("Please enter a number >= 1000");
					break;
			case 102:
					monthlyContributionValidationLabel.setText("Invalid Number");
					break;
			case 100:
					monthlyContributionValidationLabel.setText("");
					break;
		}
	}
	public void printErrorLabelForFutureValue(int param_error_code)
	{
		switch(param_error_code)
		{
			case 101:
					futureValueValidationLabel.setText("Please enter a number >= 1000");
					break;
			case 102:
					futureValueValidationLabel.setText("Invalid Number");
					break;
			case 100:
					futureValueValidationLabel.setText("");
					break;
		}
	}
	public float makeCallToFindFutureValue()
	{
		financialDetailsObject.setMonthlyContribution(monthly_contribution);
		financialDetailsObject.setNumberOfYears(number_of_years);
		financialDetailsObject.setRiskLevel(risk_level);
		financialDetailsObject.determineFutureValue();

		return financialDetailsObject.getFutureValue();
	}
	public void storeCurrentData(String param_goal_name) throws IOException
	{
		StringBuilder fileContentsToStore = new StringBuilder();
		fileContentsToStore.append("[{GoalName: "+ param_goal_name + ", MonthlyContribution: "
								+ financialDetailsObject.getMonthlyContribution() + ", NoOfYears :"
								+financialDetailsObject.getNumberOfYears() + ", RiskLevel: " 
								+ financialDetailsObject.getRiskLevel() + ", FutureValue: " 
								+ financialDetailsObject.getFutureValue() + "}]");
		System.out.println(fileContentsToStore);
		OutputStream os = new FileOutputStream("../../../goalOutput/" +param_goal_name+".txt");
		os.write((String.valueOf(fileContentsToStore)).getBytes());
	}
	public float makeCallToFindMonthlyContribution()
	{
		financialDetailsObject.setMonthlyContribution(monthly_contribution);
		financialDetailsObject.setNumberOfYears(number_of_years);
		financialDetailsObject.setRiskLevel(risk_level);
		financialDetailsObject.determinePrincipal();
		
		return financialDetailsObject.getMonthlyContribution();
	}
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run()
				{
					new UserInterfaceFinanceManager();	
				}
			}
		);
	}
}