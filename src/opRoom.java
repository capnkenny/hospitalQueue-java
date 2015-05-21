import ListPackage.LList;


public class opRoom 
{
	public int timeAvailable;
	private int timeUsed = 0;
	private String roomName = "";
	private LList<op> operationSched = new LList<op>();
	public boolean isFull = false;
	
	public opRoom(int num)
	{
		roomName = "Room #" + num + "\n";
		timeAvailable = 8;
	}
	
	public boolean addOperation(op nextOp)
	{
		
		boolean allowed = false;
		int t = nextOp.opLength;
		int testVar = timeAvailable - t;;
		
		if (testVar >= 0 && !isFull)
		{
			allowed = true;
			convertTime(nextOp, t);
			timeUsed += t;
			timeAvailable = testVar;
			operationSched.add(nextOp);
		}
		else if(testVar < 0 && !isFull)
		{
			System.out.println("TIME ERROR: Operation too long; cannot add!");
		}
		else
		{
			System.out.println("SCHEDULE ERROR: Room Schedule is full; cannot add!");
		}
			
		if (timeAvailable == 0)
			isFull = true;
		
		return allowed;
	}
	
	public void convertTime(op nextOp, int t2)
	{
		String str = "";
		int t1 = timeAvailable;
		int intConv = t1+t2;
		
		switch(t1)
		{
			case 8: 
			{
				str += "8:00 AM - ";
				break;
			}
			case 7: 
			{
				str += "9:00 AM - ";
				break;
			}
			case 6: 
			{
				str += "10:00 AM - ";
				break;
			}
			case 5: 
			{
				str += "11:00 AM - ";
				break;
			}
			case 4: 
			{
				str += "12:00 PM - ";
				break;
			}
			case 3: 
			{
				str += "1:00 PM - ";
				break;
			}
			case 2: 
			{
				str += "2:00 PM - ";
				break;
			}
			case 1: 
			{
				str += "3:00 PM - ";
				break;
			}
			default:
			{
				System.out.println("TIME ERROR: Too much time taken; cannot convert!");
				break;
			}
		}
		
		switch(intConv)
		{
		case 9:
		case 10:
		case 11:
		{
			str += intConv + ":00 AM\n";
			break;
		}
		case 12:
		{
			str += intConv + ":00 PM\n";
			break;
		}
		case 13:
		case 14:
		case 15:
		case 16:
		{
			str += (intConv-12) + ":00 PM\n";
			break;
		}
		default:
			break;
		
		}
		
		nextOp.timeString = str;
	}
	
	public String toString()
	{
		int schedNum = operationSched.getLength();
		String str = "";
		
		str += roomName;
		
		for (int i = 0; i < schedNum; i++)
		{
			op info = operationSched.getEntry(i);
			str += info.toString();
		}
		
		return str;
	}
	
}
