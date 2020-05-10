//Ishan Chaurasia
//101011068
//NET 3004 - Assignment 3
//Nov 19, 2018

public class ProbeNode {
	
	public Probe aProbe;
	public ProbeNode next;
	
	public ProbeNode() //Default Constructor
	{
		
	}
	
	public ProbeNode(Probe aProbe) //Constructor that passes a probe to assignment to local variable.
	{
		this.aProbe = aProbe;
		next = null;
	}
	
	public ProbeNode(ProbeNode aNode) //Copy Constructor that passes a existing node to be deep copied. 
	{
		Probe tmpProbe = aNode.aProbe;
		aProbe = new Probe (tmpProbe.getDestPort(), tmpProbe.getOriginIP(), tmpProbe.getOriginPort(), tmpProbe.getProbeTime());
		next = aNode.next;
	}
	
	
	//Test Suite
	public static void main(String[] args)
	{
		//Initializes a node, for testing should not output errors if it works.
		Probe tmpProbe = new Probe(903, "5.5.5.5", 782, "2019-11-16(19:32:05)");
		System.out.println(tmpProbe);	
	}
}
