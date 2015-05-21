import java.lang.reflect.Array;

import ListPackage.LList;


public class opSort 
{
	LinkedPriorityQueue<op> prio = new LinkedPriorityQueue<op>();
	int length = 10;
	LinkedPriorityQueue<op>[] priority = (LinkedPriorityQueue<op>[])Array.newInstance(prio.getClass(), length); 

	LList<op> leftOver = new LList<op>();
	
	
	opRoom roomA, roomB, roomC;
	
	public opSort()
	{
		roomA  = new opRoom(1);
		roomB  = new opRoom(2);
		roomC  = new opRoom(3);
		
		for (int i = 1; i < 11; i++)
			priority[i-1] = new LinkedPriorityQueue<op>();

	}
	
	
	public void addOp(op nextOp)
	{
		int pri = -1;
		
		pri = nextOp.opPriority;
		
		priority[pri].add(nextOp);
		}
		

	public void sortOperations()
	{
		for (int i = 0; i < 10; i++)
		{
			System.out.println(i);
			while(!priority[i].isEmpty())
			{
				char letter = 'a';
				if (priority[i].peek() == null)
					letter = 'x';
				switch(letter)
				{
				case 'a':
				{
					if (!roomA.isFull)
					{
						boolean test = false;
						test = roomA.addOperation(priority[i].peek());
						if (test)
						{
							priority[i].remove();
						}
						else
							{
							letter = 'b';
							break;
							}
					}
					else
					{
						letter = 'b';
						break;
					}
				}
				case 'b':
				{
					if (!roomB.isFull)
					{
						boolean test = false;
						test = roomB.addOperation(priority[i].peek());
						if (test)
						{
							priority[i].remove();
							letter = 'a';
						}
						else
						{
							letter = 'c';
							break;
						}
					}
					else
					{
						letter = 'c';
						break;
					}
				}
				
				case 'c':
				{
					if (!roomC.isFull)
					{
						boolean test = false;
						test = roomC.addOperation(priority[i].peek());
						if (test)
						{
							priority[i].remove();
							letter = 'a';
						}
						else
						{
							letter = 'd';
							break;
						}
					}
					else
					{
						letter = 'd';
						break;
					}
				}
				case 'x': break;
				default:
				{
					leftOver.add(priority[i].remove());
					letter = 'd';
					break;
				}
				}// end switch
				System.out.println(priority[i].isEmpty());
			}
		}
	}

	public void printSchedule()
	{
		System.out.println(roomA.toString());
		System.out.println();
		System.out.println(roomB.toString());
		System.out.println();
		System.out.println(roomC.toString());
		System.out.println();
	}
	
	
	public void printRemaining()
	{
		int amt = leftOver.getLength();
		
		System.out.println("Unscheduled Operations: \n");
		
		for(int i = 0; i < amt; i++)
		{
			op info = leftOver.getEntry(i);
			System.out.println(info.toString());
			
		}
	}
	
	
	
	
	
	
}
	
	
