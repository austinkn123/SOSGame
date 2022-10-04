package TestSprint0;

import java.util.Arrays;

public class TestFunction {
	public int square(int x) {
		return x*x;
	}
	
	public String determineGrade(char x) {
		String Comment = null;
		if(x == 'A') {
			Comment = "Excellent";
		}
		else if (x == 'B') {
			Comment = "Very Good";
		}
		else if (x == 'C') {
			Comment = "Average";
		}
		else if (x == 'D') {
			Comment = "Unsatifactory";
		}
		else if (x == 'F') {
			Comment = "Failing";
		}
		return Comment;
	}
	
	public int[] insertionSort() {
		int[] Arr = {120,20,30,40,50,60,70,80};
        int n = Arr.length; 
        
        
        int i, key, j; 
        for (i = 1; i < n; i++)
        { 
            key = Arr[i]; 
            j = i - 1; 
      
            // Move elements of arr[0..i-1],  
            // that are greater than key, to one 
            // position ahead of their 
            // current position
            while (j >= 0 && Arr[j] > key)
            { 
            	Arr[j + 1] = Arr[j]; 
                j = j - 1; 
            } 
            Arr[j + 1] = key; 
        } 
        
        System.out.print(Arrays.toString(Arr));
		return Arr;
	}
}
