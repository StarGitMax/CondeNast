package com.CondeNast.TestScenarios;

public class percccentage {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		String b1 = "US$2,000.00";
		String b2 = "US$3,000.00";
		
		String f1 = "USD 1,700.00";
		
		String st1 = "10";
		String st2 =  "50";
		
		
		String b11 = b1.replace("US$", "").replace(",", "").replace(".00", "");
		String b22 = b2.replace("US$", "").replace(",", "").replace(".00", "");
		String f11 = f1.replace("USD", "").replace(",", "").replace(".00", "").trim();


		System.out.println("Total No of rows are : " + b11);
		System.out.println("Total No of rows are : " + b22);
		System.out.println("Total No of rows are : " + f11);
		
		float fb1 = Float.parseFloat(b11);
		float fb2 = Float.parseFloat(b22);
		
        float per = (fb1/100)*10;
        System.out.println("Percentage ::"+ per);
        
        float per1 = (fb2/100)*50;
        System.out.println("Percentage ::"+ per1);
        
        
        int i1 = Integer.parseInt(String.valueOf(per).replace(".0", ""));
        int i2 = Integer.parseInt(String.valueOf(per1).replace(".0", ""));
        int i3 = Integer.parseInt(f11);
        int i4 = i1+i2;
        
        System.out.println("Total : " + i4);
		//System.out.println("Total  : " + i2);
		
        if(i3==i4)
        {
        	System.out.println("True : ");
        }
		
	}

}
