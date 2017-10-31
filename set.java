import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
//import java.util.ArrayList;
import java.util.LinkedList;

public class set {

	public static void main(String[] args) {
		String[] orders = {"1","2","3","4","5"};
		for (String order : orders) show_result(order);
	}

	public static void show_result(String order){
		try {
			FileReader fileReader = new FileReader("setIn"+order+".txt");
			BufferedReader reader = new BufferedReader(fileReader);

			// read number for the size of Set A
			int ASize = Integer.parseInt(reader.readLine());
			
			// read in the elements of Set A
			String[] A = new String[ASize];
			for (int i = 0; i < ASize; i++) {
				A[i] = reader.readLine();
			}

			// read number for the size of Set B
			int BSize = Integer.parseInt(reader.readLine());
			
			// read in the elements of Set B
			String[] B = new String[BSize];
			for (int i = 0; i < BSize; i++) {
				B[i] = reader.readLine();
			}

			// print out set A
			if (ASize >0) {System.out.print("Set A = { " + A[0]);}
			else {System.out.print("Set A = { ");}
			for (int i = 1; i < ASize; i++) {
				System.out.print(", " + A[i]);
			}
			System.out.println(" }");

			// print out set B
			if (BSize > 0) {System.out.print("Set B = { " + B[0]);}
			else {System.out.print("Set B = { ");}
			for (int i = 1; i < BSize; i++) {
				System.out.print(", " + B[i]);
			}
			System.out.println(" )");


			// call union to print out the union of A and B
			union(A, ASize, B, BSize);
			
			// call intersection to print out the intersection of A and B
			intersection(A, ASize, B, BSize);

			// check whether A and B are subset of each other
			if (ASize < BSize) {
				if (subset(A, ASize, B, BSize)) {
					System.out.println("Set A is a subset of set B");
				} else {
					System.out.println("Set A and B are not subsets of each other");
				}
			} else {
				if (subset(B, BSize, A, ASize)) {
					System.out.println("Set B is a subset of set A");
				} else {
					System.out.println("Set A and B are not subsets of each other");
				}
			}
			System.out.printf("\n");
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean isElementOf(String X, String[] S, int SSize) {
		// This function returns true if X is an element of set S.
		// You can call this function in union, intersection and subset.
		for (int i = 0; i < SSize; i++) {
			if (X.equals(S[i])) {
				return true;
			}
		}
		return false;
	}


	public static void union(String[] X, int XSize, String[] Y, int YSize) {
		// WRITE YOUR CODE HERE for union, which prints 
		// the union of set X and Y
		//Linkedlist version（instead of array to get more fast)
		LinkedList<String> union_list = new LinkedList<String>();
		for(int i=0;i<XSize;i++){
			if(union_list.contains(X[i]))
				continue;
			union_list.add(X[i]);
		}
		for(int j=0;j<YSize;j++){
			if(union_list.contains(Y[j]))
				continue;
			union_list.add(Y[j]);
		}


		if (union_list.size() > 0) {System.out.print("Set A Union B = { " + union_list.get(0));}
			else {System.out.print("Set A Union B = { ");}
			for (int i = 1; i < union_list.size(); i++) {
				System.out.print(", " + union_list.get(i));
			}
			System.out.println(" }");

		//array version
		// String[] union_list = new String[XSize];
		// for(int i=0;i<XSize;i++){
		// 	if(isElementOf(X[i],union_list,union_list.length))
		// 		continue;
		// 	union_list[i]=X[i];
		// }
		// for(int j=0;j<YSize;j++){
		// 	if(isElementOf(Y[j],union_list,union_list.length))
		// 		continue;
		// 	union_list = Arrays.copyOf(union_list,union_list.length+1);
		// 	union_list[union_list.length-1] = Y[j];
		// }

		// if (union_list.length > 0) {System.out.print("Set A Union B = { " + union_list[0]);}
		// 	else {System.out.print("Set A Union B = { ");}
		// 	for (int i = 1; i < union_list.length; i++) {
		// 		System.out.print(", " + union_list[i]);
		// 	}
		// 	System.out.println(" }");
		
	}


	public static void intersection(String[] X, int XSize, String[] Y, int YSize) {
		// WRITE YOUR CODE HERE for intersection, which prints 
		// the intersection of set X and Y
		//Linkedlist version
		LinkedList<String> intersection_list = new LinkedList<String>();
		for(int i=0;i<XSize;i++){
			for (int j=0;j<YSize;j++) {
				if (X[i].equals(Y[j]) && !(intersection_list.contains(X[i])))
					intersection_list.add(X[i]);
			}
		}

		if (intersection_list.size() > 0) {System.out.print("Set A Intersection B = { " + intersection_list.get(0));}
			else {System.out.print("Set A intersection B = { ");}
			for (int i = 1; i < intersection_list.size(); i++) {
				System.out.print(", " + intersection_list.get(i));
			}
			System.out.println(" }");

		// String[] intersection_list = new String[1];
		// int count = 0;
		// for(int i=0;i<XSize;i++){
		// 	for (int j=0;j<YSize;j++) {
		// 		if (X[i].equals(Y[j]) && !(isElementOf(X[i],intersection_list,intersection_list.length))){
		// 			count+=1;
		// 			if (intersection_list.length == 1 && count ==1){
		// 				intersection_list[0] = X[i];
		// 			}
		// 			else{						
		// 				intersection_list = Arrays.copyOf(intersection_list,intersection_list.length+1);
		// 				intersection_list[intersection_list.length-1] = X[i];
		// 			}}
		// 	}
		// }
		// if (intersection_list.length > 0) {System.out.print("Set A intersection B = { " + intersection_list[0]);}
		// 	else {System.out.print("Set A intersection B = { ");}
		// 	for (int i = 1; i < intersection_list.length; i++) {
		// 		System.out.print(", " + intersection_list[i]);
		// 	}
		// 	System.out.println(" }");
		
	}


	public static boolean subset(String[] X, int XSize, String[] Y, int YSize) {
		// WRITE YOUR CODE HERE for subset, which returns 
		// true if set X is a subset of set Y
		for(int i=0;i<XSize;i++){
			if(!(isElementOf(X[i],Y,YSize)))
				return false;
		}
		return true;
	}
		
}
