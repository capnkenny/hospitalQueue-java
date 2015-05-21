/**Based off and utilizes code provided from original readPatient.java */

import java.io.*;
import java.util.Scanner;

public class readPatient 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	throws FileNotFoundException
	{
		// TODO Auto-generated method stub
		String patientID, operationCode, doctorID;
		int priority, lengthOfOperation;
		
		Scanner inFile = new Scanner (new FileReader("Patients.txt"));
		
		opSort schedule = new opSort();

		while (inFile.hasNext())
		{
			patientID = inFile.next();
			operationCode = inFile.next();
			doctorID = inFile.next();
			priority = inFile.nextInt();
			lengthOfOperation = inFile.nextInt();
		
			/*System.out.println (patientID + " " + operationCode 
				       + " " + doctorID + " " + priority
				       + " " + lengthOfOperation);*/
			
			
			op nextOp = new op(patientID, operationCode, doctorID, 
					lengthOfOperation, priority);
			
			schedule.addOp(nextOp);
			
			
			
		}
		inFile.close();
		
		schedule.sortOperations();
		//schedule.printSchedule();
		//schedule.printRemaining();
	}

}
