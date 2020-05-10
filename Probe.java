//Ishan Chaurasia
//101011068
//NET 3004 - Assignment 3
//Nov 19, 2018

public class Probe {
	
	private int destPort;
	private String originIP;
	private int originPort;
	private String probeTime;
	
	public Probe() //Default constructor.
	{
		
	}
	
	public Probe(int destPort, String originIP, int originPort, String probeTime) //Constructor that passes/accepts all 4 variables upon initialization.
	{
		this.destPort = destPort;
		this.originIP = originIP;
		this.originPort = originPort;
		this.probeTime = probeTime;
	}
	
	public int getDestPort() //getter for destination port.
	{
		return destPort;
	}
	
	public String getOriginIP() //getter for origin ip.
	{
		return originIP;
	}
	
	public int getOriginPort() //getter for origin port.
	{
		return originPort;
	}
	
	public String getProbeTime() //getter for probe time.
	{
		return probeTime;
	}
}