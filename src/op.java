
public class op implements Comparable<op>
{

	String patientID = "", opCode = "", docID = "", timeString = "";
	int opLength = 0, opPriority = -1;
	
	public op()
	{
		this(null, null, null, -1, -1);
	}
	
	public op(String pID, String opC, String dID, 
			int opL, int opPri)
	{
		patientID = pID;
		opCode = opC;
		docID = dID;
		opLength = opL;
		opPriority = opPri;
		
	}
	
	public int compareTo(op secondOp)
	{
		return secondOp.opCode.compareTo(this.opCode);
	}
	
	public String toString()
	{
		String str = "";
		
		str += "Patient ID: " + patientID + "\t\tDoctor ID: " + docID +"\n";
		str += "Operation Code: " + opCode + "\t\tPriority: " + opPriority + "\n";
		str += "Length: " + opLength + " Hours\t\tTime: " + timeString + "\n";
		
		return str;
	}
	
}
