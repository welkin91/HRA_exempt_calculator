import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class test {
	
	public static Scanner read = new Scanner(System.in);
	public static int[] monthDays = {31, 28, 31, 30, 31, 30,31, 31, 30, 31, 30, 31};
	public static ArrayList<DateData> dateData = null;
	public static ArrayList<SalData> salData = null;
	public static int Idx = 0;
	
	public static int countLeapYears(Date d)
	{
	    int years = d.year;
	    
	    if (d.month <= 2)
	        years--;
	 
	    return years / 4 - years / 100 + years / 400;
	}
	
	public static long getDifference(Date start, Date end)
	{
	 
		long n1 = start.year * 365 + start.day;
	 
	    for( int i = 0 ; i < start.month - 1 ; i++){
	    	n1 += monthDays[i];
	    }
	 
	    n1 += countLeapYears(start);
	 
	    long n2 = end.year * 365 + end.day;
	    
	    for( int i = 0 ; i < end.month - 1 ; i++){
	    	n2 += monthDays[i];
	    }
	    
	    n2 += countLeapYears(end);
	 
	    return n2 - n1 + 1;
	}
	
	public static double getYear(long x)
	{
		double res = 0.0;
		
		res =(double) x/365;
		
		return res;
	}
	
	public static double getHouseRent(Date start, Date end)
	{
		double res = 0.0;
		
		
		
		return res;
	}
	
	public static void setHouseInfo( int i )
	{
		DateData data = new DateData();
		Date start = new Date();
		Date end = new Date();
		
		System.out.println("Appartment " + (i+1) + ": (dd/mm/yyyy)\n");
		
		System.out.print("start date: ");
		String st = read.next();
		start.day = Integer.parseInt(st.split("/")[0]);
		start.month = Integer.parseInt(st.split("/")[1]);
		start.year = Integer.parseInt(st.split("/")[2]);
		
		System.out.print("end date: ");
		st = read.next();
		end.day = Integer.parseInt(st.split("/")[0]);
		end.month = Integer.parseInt(st.split("/")[1]);
		end.year = Integer.parseInt(st.split("/")[2]);
		
		System.out.print("Enter House Rent: ");
		data.houseRent = read.nextDouble();
		
//		long numDays = getDifference(start, end);
//		System.out.println("\ndifference = "+numDays + " days");
//		
//		double numYears = getYear(numDays);
//		System.out.println("days in years = " + numYears + " years");
		
		data.start = start;
		data.end = end;
//		data.days = numDays;
//		data.years = numYears;
		
		dateData.add(data);
	}
	
	public static int compareDate(Date s1, Date s2)
	{
		int res = 0;
		
		res = s1.year - s2.year;
		if ( res == 0 ) {
			res = s1.month - s2.month;
			if ( res == 0 ) {
				res = s1.day - s2.day;
			}
		}
		
		return res;
	}
	
	public static void sortDate()
	{
		Collections.sort(dateData, new Comparator<DateData>() {
			@Override
			public int compare(DateData arg0, DateData arg1) {
				// TODO Auto-generated method stub
				
				return compareDate(arg0.start,arg1.start);
			}
		});
	}
	
	public static void sortSal()
	{
		Collections.sort(salData, new Comparator<SalData>() {
			@Override
			public int compare(SalData arg0, SalData arg1) {
				// TODO Auto-generated method stub
				
				return compareDate(arg0.start,arg1.start);
			}
		});
	}
	
	public static void setSalInfo( int i )
	{
		SalData data = new SalData();
		Date start = new Date();
		Date end = new Date();
		double sal = 0.0, hra = 0.0;
		String city = "";
		Salary sall = new Salary();
		
		System.out.println("Salary " + (i+1) + ": (dd/mm/yyyy)\n");
		
		System.out.print("start date: ");
		String st = read.next();
		start.day = Integer.parseInt(st.split("/")[0]);
		start.month = Integer.parseInt(st.split("/")[1]);
		start.year = Integer.parseInt(st.split("/")[2]);
		
		System.out.print("end date: ");
		st = read.next();
		end.day = Integer.parseInt(st.split("/")[0]);
		end.month = Integer.parseInt(st.split("/")[1]);
		end.year = Integer.parseInt(st.split("/")[2]);
		
		System.out.print("Enter Salary: ");
		sal = read.nextDouble();
		System.out.print("Enter HRA: ");
		hra = read.nextDouble();
		System.out.print("Enter City:");
		city = read.next();
		sall.city = city;
		sall.hra = hra;
		sall.sal = sal;
		
		data.start = start;
		data.end = end;
		data.salary = sall;
		
		salData.add(data);
	}
	
	public static void see ( Date s ,int l , int r)
	{
		if ( l < r ) {
			int m = (l+r)/2;
			int x = compareDate(s,dateData.get(m).start);
			if ( x == 0 ) {
				Idx = m;
				return;
			} else {
				if ( x < 0 ) {
					Idx = m;
					see(s,l,m-1);
				}else {
					see(s,m+1,r);
				}
			}
		}
	}
	
	public static int findIdx(Date s)
	{
		int n = dateData.size();
		
		int x = compareDate(dateData.get(0).start,s);
		if ( x < 0 )
			return -1;
		else if ( x == 0 )
			return 0;
		
		Idx = 0;
		see ( s,0,n);
		
		return Idx;
	}
	
	public static int findIndex(Date s) 
	{
		int res = -1;
		
		int i = 0;
		for ( DateData d : dateData ) {
			if ( compareDate(s,d.start) >= 0  && compareDate(s,d.end) <= 0 ) {
				return i;
			}
			i++;
		}
		
		return res;
	}
	
	public static double calHRAexempt() 
	{
		double res = 0.0;
		for ( SalData s : salData ) {
			int sIdx = 0, eIdx = 0;
			sIdx = findIndex(s.start);
			eIdx = findIndex(s.end);
			
//			System.out.println("sIdx = " + sIdx + "\teIdx = " + eIdx);
			
			if ( eIdx - sIdx == 0 ) {
				long numDays = getDifference(s.start, s.end);
				double numYears = getYear(numDays);
				
				HRAexempt obj = new HRAexempt(s.salary.sal,s.salary.hra,dateData.get(sIdx).houseRent,s.salary.city);
				res += (obj.getHRAexempt()*numYears);
			} else {
				for ( int i = sIdx ; i <= eIdx ; i++ ) {
					Date sd = null , ed = null;
					if ( compareDate(s.start,dateData.get(i).start) > 0 ) {
						sd = s.start;
					}else {
						sd = dateData.get(i).start;
					}
					
					if ( compareDate(s.end,dateData.get(i).end) <= 0){
						ed = s.end;
					} else {
						ed = dateData.get(i).end;
					}
					
					long numDays = getDifference(sd, ed);
					double numYears = getYear(numDays);
					
					HRAexempt obj = new HRAexempt(s.salary.sal,s.salary.hra,dateData.get(i).houseRent,s.salary.city);
					double ss = (obj.getHRAexempt()*numYears);
//					System.out.println("HRA exempt for ");
//					System.out.println("salary = "+s.salary.sal);
//					System.out.println("hra = "+s.salary.hra);
//					System.out.println("rent = "+dateData.get(i).houseRent);
//					System.out.println("city = "+s.salary.city);
//					System.out.println("-------------\n"+ss+"\n-------------");
					res += ss;
				}
			}
		}
		
		return res;
	}
	
	public static void compute()
	{
		double result = 0.0;
		System.out.print("Are U staying in a rented appartment?? (Y/n): ");
		String ans = read.next();
		
//-------------------------------------------(Phase-I)--------------------------------------------------------//
		
		if ( ans.toLowerCase().equals("y") ) {
			dateData = new ArrayList<DateData>();
			
			int shiftedTimes = 1;
			System.out.print("Are U living in the rented appartment from past 1 year?? (Y/n):");
			ans = read.next();
			if ( ans.toLowerCase().equals("n")) {
				System.out.print("How many appartments have U rented or change in HouseRent occurs: ");
				shiftedTimes = read.nextInt();
			}
			System.out.println("==========================================================");
			System.out.println("Enter the dates when U start and end living in appartments");
			System.out.println("----------------------------------------------------------");
			for ( int i = 0 ; i < shiftedTimes ; i++ ) {
				setHouseInfo(i);
				System.out.println("----------------------------------------------------------");
			}
		} else{
			System.out.println("Sorry, u'r not applicable for HRA exempt!");
			System.exit(1);
		}
		sortDate();
		System.out.println();
		
//-------------------------------------------(Phase-II)-------------------------------------------------------//

		salData = new ArrayList<SalData>();
		System.out.println("Is U'r salary/HRA constant from past 1 year?? (Y/n):");
		ans = read.next();
		int salChange = 1;
		if ( ans.toLowerCase().equals("n") ) {
			System.out.print("How many time does U'r salary changes from past 1 year: ");
			salChange = read.nextInt();
		}
		System.out.println("==========================================================");
		System.out.println("Enter the dates when change in salary/HRA changes");
		System.out.println("----------------------------------------------------------");
		
		for ( int i = 0 ; i < salChange; i++ ) {
			setSalInfo(i);
			System.out.println("----------------------------------------------------------");
		}
		sortSal();
		
//------------------------------------------(Phase-III)-------------------------------------------------------//
		
		result = calHRAexempt();
		System.out.println("Total HRA exempt = " + result);
	
//------------------------------------------------------------------------------------------------------------//
	}
	
	public static void main(String[] argv) { 
		compute();	
	}
}
class Date {
	int day,month,year;
}
class Salary {
	double sal,hra;
	String city;
}

class DateData {
	Date start;
	Date end;
	double houseRent;
}

class SalData {
	Salary salary;
	Date start,end;
}