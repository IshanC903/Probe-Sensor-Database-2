//Ishan Chaurasia
//101011068
//NET 3004 - Assignment 3
//Nov 19, 2018

import java.io.*;
import java.util.*;

public class PSDB {
	
	public static void main(String[] args) throws FileNotFoundException
	{
		// TODO Auto-generated method stub
		//Read firewall.log.txt, if not found throw exception
		
		//------------------------
		//Code copied from provided paperwork for assignment.
		Scanner s = new Scanner(new File("D:\\Ishan C\\Documents\\eclipse-workspace-java\\NET3004_Assignment 3\\src\\firewall.log.txt"));
		//I had to hard code the path because just the file name with the file in src was throwing the FileNotFoundException.
		//-----------------------------------------------------------------------------------------------------------------------
		
		ProbeLList p = new ProbeLList(); //Calling ProbeList Class
		
		boolean moreLines = true; //Read end of file
		
		while(moreLines)
		{
			if(s.hasNext())
			{
				String tmpPTime = s.next();//Date and Time
				String[] tmpSIP = s.next().split(":"); //SourceIP:Port
				String[] tmpDIP = s.next().split(":"); //DestinationIP:Port
				
				Probe tmpProbe = new Probe(Integer.parseInt(tmpDIP[1]), tmpSIP[0], Integer.parseInt(tmpSIP[1]), tmpPTime);
				p.insertProbe(tmpProbe);
			}
			else
				moreLines = false;
		}	
		s.close();
		//-------------------------
		//User interaction code is reused from previous assignment due to redundancy, code to scan the log file above this point has been altered to
		//accommodate the probes being added to a LinkedList.
				
		String userInput; //Variable that will hold user input.
		System.out.println("\nWelcome to the Port Scan Database.");
		Scanner in = new Scanner(System.in); //Scanner to reach user input in console.
		
		do //Do loop that runs until "END" is typed into console.
		{
			System.out.println("Enter IP/DP/PL/IL/END (IP address/Destination Port/Port List/IP List/END):");
			userInput = in.next(); //Assigns console input to variable to be used in code.
			userInput = userInput.toUpperCase(); //Converts to upper case so code doesn't crash.
			
			if (userInput.equals("IP"))
			{
				boolean stop = false; //stop code added to allow used to "END" out of menu option back to previous menu.
				
				while(!stop)
				{
					System.out.println("For which IP do you wish to retrieve statistics?");
					String search = in.next();
					
					if(search.toUpperCase().equals("END")) //goes back to main menu.
					{
						stop = true;
					}
					else //continues if user gives correct input
					{
						int count = p.countProbes(search); //Matches user input with information in probe.
						
						if (count == 0) //error message if no information found.
						{
							System.out.println("There are no probes for this IP."); 
						}
						else
						{
							System.out.println("There were "+count+" probes from "+search+".\n"); //Displays number of probes for matched IP.
						}
					}						
				}		
			}
			
			else if (userInput.equals("DP"))
			{
				boolean stop = false; //stop code added to allow used to "END" out of menu option back to previous menu.
				
				while(!stop)
				{
					System.out.println("For which port do you wish to retrieve statistics?");
					String search = in.next();
					
					if(search.toUpperCase().equals("END")) //goes back to main menu.
					{
						stop = true;
					}
					else //continues if user gives correct input
					{
						int count = p.countProbes(Integer.parseInt(search)); //Matches user input with information in probe.
						
						if (count == 0) //error message if no information found.
						{
							System.out.println("There are no probes for this Port.");
						}
						else
						{
							System.out.println("There were "+count+" probes from "+search+".\n"); //Displays number of probes for matched Port.
						}
					}
				}
			}
			else if (userInput.equals("PL"))
			{
				boolean stop = false; //stop code added to allow used to "END" out of menu option back to previous menu.
				
				while(!stop)
				{
					System.out.println("Enter a source IP address to see a list of ports that it scanned.");
					String search = in.next();
					
					if(search.toUpperCase().equals("END")) //goes back to main menu.
					{
						stop = true;
					}
					else 
					{
						ArrayList<Probe> tmpList = p.getProbes(search); //Matches user input with information in probe and stores in temporary list.
						
						if(tmpList.size() == 0) //error message if no information found.
						{
							System.out.println("There are no probes for this IP.");
						}
						else //continues if user gives correct input
						{
							for(int i = 0; i < tmpList.size(); i++) //Displays all ports scanned by provided IP address.
							{
								System.out.println("IP "+tmpList.get(i).getOriginIP()+" sent a packet from port "+tmpList.get(i).getOriginPort()+" to port "+tmpList.get(i).getDestPort()+".");
							}					
						}
					}
				}
			}
			else if (userInput.equals("IL"))
			{
				boolean stop = false; // stop code....
				
				while(!stop)
				{
					System.out.println("For which port do you wish to retrieve statistics?");
					String search = in.next();
					
					if(search.toUpperCase().equals("END")) //back to main menu
					{
						stop = true;
					}
					else
					{
						ArrayList<String> tmpList = p.getProbes(Integer.parseInt(search)); //matches user input with probe and stores in temp list.
						
						if(tmpList.size() == 0)  //error message if no information found.
						{
							System.out.println("There are no probes for this Port.");
						}
						else //continues if information provided is valid.
						{
							System.out.println("The "+tmpList.size()+" different IP's who probed port "+search+" are as follows:");
							
							for(int i = 0; i < tmpList.size(); i++) //Displays all IP's that probed provided port number.
							{
								System.out.println(tmpList.get(i));
							}				
						}
					}
				}
			}
		}while(!userInput.equals("END"));
		
		System.out.println("Goodbye.");
		in.close();
	}
}
