//Ishan Chaurasia
//101011068
//NET 3004 - Assignment 3
//Nov 19, 2018

import java.util.*;

public class ProbeLList {
	
	private ProbeNode head;
	private ProbeNode tail;
	
	public ProbeLList() //Default Constructor
	{
		head = null;
		tail = null;
	}
	
	public ProbeLList(ProbeLList aList) //Constructor that passes a ProbeLList to be copied upon initialization.
	{
		if(aList.head == null)
		{
			head = null;
			tail = null;
		}
		else
		{
			head = new ProbeNode(aList.head);
			ProbeNode tmpDest = head;
			ProbeNode tmpSrc = aList.head;
			
			while(tmpSrc.next != null)
			{
				tmpDest = new ProbeNode(tmpSrc);
				tmpDest = tmpDest.next;
				tmpSrc = tmpSrc.next;
			}
			
			tail = new ProbeNode(aList.tail);
		}
	}
	
	public int getActualSz() //Function to iterate through the linked list and track number of elements. or return size of list.
	{
		int size = 0;
		ProbeNode temp = head;
		
		while(temp != null)
		{
			size++;
			temp = temp.next;
		}
		return size;
	}
	
	private int getActualSz(ProbeNode head) //Function to iterate through passed node and track number of elements, or return size of passed node.
	{
		int size = 0;
		ProbeNode temp = head;
		while(temp != null)
		{
			size++;
			temp = temp.next;
		}
		return size;
	}
	
	public int insertProbe(Probe probe) //Function that passes a probe, checks if local list has elements, and adds to last element if full, or first element if list is empty.
	{
		if(head == null)
		{
			head = new ProbeNode(probe);
			tail = head;
			return 0;
		}
		else
		{
			int element = 1;
			ProbeNode temp = head;
			while(temp.next != null)
			{
				element++;
				temp = temp.next;
			}
			temp.next = new ProbeNode(probe);
			tail = temp.next;
			return element;
		}
	}
	
	public int countProbes(String ip) //Function that uses getProbes function to count probes that matched to the passes IP, and returns number.
	{
		int temp = getProbes(ip).size();
		return temp;
	}
	
	public int countProbes(int destPort) //Function that uses getProbes function to count probes that matched to the passes Dest'n Port, and returns number.
	{
		int temp = getProbes(destPort).size();
		return temp;
	}
	
	public ArrayList<String> getProbes(int destPort) //Function that matches probes with passes Dest'n Port, and returns arraylist of source IPs.
	{
		ArrayList<String> myArr = new ArrayList<String>();
		ProbeNode tmpNode = head;
		
		while(tmpNode != null)
		{
			Probe tmpProbe = tmpNode.aProbe;
			
			if(tmpProbe.getDestPort() == destPort)
			{
				myArr.add(tmpProbe.getOriginIP());
			}
			tmpNode = tmpNode.next;
		}		
		return myArr;
	}
	
	public ArrayList<Probe> getProbes(String ip) //Function that matches probes with passes IP, and returns arraylist of the matched Probes.
	{
		ArrayList<Probe> myArr = new ArrayList<Probe>();
		ProbeNode tmpNode = head;
		
		while(tmpNode != null)
		{
			Probe tmpProbe = tmpNode.aProbe;
			
			if(tmpProbe.getOriginIP().equals(ip))
			{
				myArr.add(tmpProbe);
			}
			tmpNode = tmpNode.next;
		}		
		return myArr;
	}
	
	//Test Suite
	public static void main(String[] args)
	{
		//Create List. Test Default Constructor.
		ProbeLList tmpList = new ProbeLList();
		
		//If above constructor works, should display a size of 0.
		System.out.println(tmpList.getActualSz());
		
		//Insert test probe.
		tmpList.insertProbe(new Probe(903, "5.5.5.5", 782, "2019-11-16(19:32:05)"));
		
		//If above function works, should display size of 1.
		System.out.println(tmpList.getActualSz());
		
		//For test purposes should display 1 as only 1 probe should be matched to port # and ip address.
		System.out.println(tmpList.countProbes(903));
		System.out.println(tmpList.countProbes("5.5.5.5"));
		
		//For test purposes should return the IP for the probe we inserted earlier.
		System.out.println(tmpList.getProbes(903));
		
		//Should return the probe object itself.
		System.out.println(tmpList.getProbes("5.5.5.5"));
		
		//Add second object to list for testing.
		tmpList.insertProbe(new Probe(123, "6.6.6.6", 403, "2019-11-19(15:06:56)"));
		System.out.println(tmpList.getActualSz());
		
		//Testing for constructor that passes the object.
		ProbeLList tmpList2 = new ProbeLList(tmpList);
		
		//If constructor works, should display size of 2.
		System.out.println(tmpList2.getActualSz());
	}
}
