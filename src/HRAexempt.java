import java.util.HashSet;

public class HRAexempt {
	
	private static double baseSal;
	private static double hra;
	private static double houseRent;
	private static String city;
	
	public static HashSet<String> set = null;
	
	HRAexempt() 
	{
		baseSal = 0.0;
		hra = 0.0;
		houseRent = 0.0;
		city = "";
		set = new HashSet<String>();
		set.add("mumbai");
		set.add("delhi");
		set.add("chennai");
		set.add("kolkata");
	}
	
	HRAexempt(double a,double b,double c,String s)
	{
		baseSal = a;
		hra = b;
		houseRent = c;
		city = s;
		set = new HashSet<String>();
		set.add("mumbai");
		set.add("delhi");
		set.add("chennai");
		set.add("kolkata");
	}
	
	public static void setSal(double a)
	{
		baseSal = a;
	}
	public double getSal()
	{
		return baseSal;
	}
	
	public static void sethra(double a)
	{
		hra = a;
	}
	public double gethra()
	{
		return hra;
	}
	
	public static void sethouseRent(double a)
	{
		houseRent = a;
	}
	public double gethouseRent()
	{
		return houseRent;
	}
	
	public static void setCity(String s)
	{
		city = s;
	}
	public String getCity()
	{
		return city;
	}
	
	public static double minn ( double a , double b)
	{
		if ( a > b ) return b;
		return a;
	}
	public double getHRAexempt()
	{
		double res = 0.0;
		
		if ( set.contains(city) )
			res = minn(hra,minn((0.5*baseSal),(houseRent-(0.1*baseSal))));
		else 
			res = minn(hra,minn((0.4*baseSal),(houseRent-(0.1*baseSal))));
		
		return res;
	}
	
	public double getHRAexempt(double a,double b,double c, String city)
	{
		double res = 0.0;
		
		if ( set.contains(city) )
			res = minn(b,minn((0.5*a),(c-(0.1*a))));
		else 
			res = minn(b,minn((0.4*a),(c-(0.1*a))));
		
		return res;
	}
	
	public static void main(String[] argv) {
		HRAexempt x = new HRAexempt(50000, 20000, 18000, "mumbai");
		System.out.println(x.getHRAexempt(x.getSal(),x.gethra(),x.gethouseRent(),x.getCity()));
	}
}
