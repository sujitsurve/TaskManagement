package org.flowable;

import java.util.Scanner;

public class NavigationClass 
{
	  Scanner scanner= new Scanner(System.in);
	public String approve()
	{
		return " which status you want to go,Please enter number ? 1.Review 2.Done 3.Inprogress";
	}
	public String done()
	{
		return " which status you want to go,Please enter number ? 1.End Task 2.Open";
	}
	public String open() 
	{
		return " which status you want to go,Please enter number ? 1.InProgress 2.Done";
		
	}
	public String review()
	{
		return " which status you want to go,Please enter number ? 1.Done 2.Approve";
	}
	public String inprogress()
	{
		return " which status you want to go,Please enter number ? 1.Approve 2.Done 3.Open";
	}
	public boolean approvecode()
	{
		System.out.println(" which status you want to go,Please enter number ? 1.Review 2.Done 3.Inprogress");
		boolean approvedtwo = scanner.nextLine().toLowerCase().equals("1");
		return approvedtwo;
	}
	
	public boolean donecode()
	{
		System.out.println("which status you want to go,Please enter number ? 1.End Task 2.Open");
		  boolean approve = scanner.nextLine().toLowerCase().equals("1");
		return approve;
	}
	public boolean opencode()
	{
		System.out.println("which status you want to go,Please enter number ? 1.InProgress 2.Done");
		 boolean approved = scanner.nextLine().toLowerCase().equals("1");
		return approved;
	}
	public boolean reviewcode()
	{
		System.out.println(" which status you want to go,Please enter number ? 1.Done 2.Approve");
		boolean approvedtwo = scanner.nextLine().toLowerCase().equals("1");
		return approvedtwo;
	}
	public int inprogresscode()
	{
	System.out.println(" which status you want to go,Please enter number ? 1.Approve 2.Done 3.Open");
	   int input = Integer.parseInt(scanner.nextLine());
	return input;
	}
	
}
