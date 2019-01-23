import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class tweets 
{
	public static void main(String[] args) 
	{
		
		Scanner scan = null;
		String[] data = new String[2000];
		Scanner ch = new Scanner(System.in);
		int choise;
		
		try{ 
			scan = new Scanner(new File("2K-Tweets.txt"));
			int count = 0;
			while(scan.hasNextLine())
			{
				data[count] = scan.nextLine();				
				count++;
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		
		System.out.println("If you want to find a tweet through a keyword press 1");
		System.out.println("If you want to find similar tweets press 2");
		System.out.println("If you want to exit press 3");
		choise = ch.nextInt();
		
		while(choise != 3 && choise != 1 && choise != 2)
		{
			System.out.println("If you want to find a tweet through a keyword press 1");
			System.out.println("If you want to find similar tweets press 2");
			System.out.println("If you want to exit press 3");
			choise = ch.nextInt();			
		}
		
		if(choise == 1)
			findKeyword(data);
		else if(choise == 2)
			similarTweets(data);
		else if(choise == 3)
			System.out.println("You have benn exit for the System.");		
	}

	public static void findKeyword(String[] data) 
	{

		Scanner sc = new Scanner(System.in);
		int[] weight = new int[2000];
		
		for(int i = 0; i<weight.length; i++)
		{
			weight[i] = 0;
		}
		
		boolean exist = false;
		int max = 0;	
		int res = 0;
		ArrayList<String> tweets = new ArrayList<String>();
		
		System.out.print("Type keyword/s: ");
		String keyword = sc.nextLine(); 
		System.out.println("\n");
		String[] keysplit = keyword.split(" ");		
		
		for(String start : keysplit)
		{
			String t = " ";
			for(int i = 0; i<data.length; i++)
			{
				t = data[i];
				String[] arr =  t.split(" ");
				for(String st : arr)
				{
					if(start.equals(st))
					{
						weight[i]++;
						exist = true;
						break;						
					}
				}				
			}						
		}
		
		for(int i = 0; i<weight.length; i++)
		{
			if(weight[i] > max)
			{
				max = weight[i];				
			}
		}
		if(exist == false)
			System.out.println("No tweets found");
		else
			for(int i = 0; i<weight.length; i++)
			{
				if(max == weight[i])
				{
					tweets.add(data[i]);
				}					
			}
			for(int i = 0; i<tweets.size(); i++)
			{
				res++;
			}
			
			System.out.println(res +" results for: " +keyword +"\n\n");	
			
			for(int i = 0; i<tweets.size(); i++)
			{	
				System.out.println(tweets.get(i));
			}
					
	}
	
	public static void  similarTweets(String[] data)
	{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Give the tweet number that you want to find a similar one (0-1999): ");
		int num = sc.nextInt();
		int[] similar = new int[2000];
		int max = 0;
		ArrayList<String> sim = new ArrayList<String>();
		
		System.out.println("The " +num +" tweet says: " +"\n\n");
		System.out.println(data[num]);
		
		String[] tt = data[num].split(" ");
		
		for(String start : tt)
		{
			String t = " ";
			for(int i = 0; i<data.length; i++)
			{
				if(i == num) // in order not to check the given tweet
					break;
				t = data[i];
				String[] arr =  t.split(" ");
				for(String st : arr)
				{
					if(start.equals(st))
					{
						similar[i]++;						
					}
				}					
			}
		}
		
		for(int i = 0; i<similar.length; i++)
		{
			if(similar[i] > max)
			{
				max = similar[i];				
			}
		}
		
		for(int i = 0; i<similar.length; i++)
		{
			if(max == similar[i])
			{
				sim.add(data[i]);
			}
		}
		
		System.out.println("\n");
		System.out.println("The most similar tweet/s is/are: \n");
		
		for(int i = 0; i<sim.size(); i++)
		{	
			System.out.println(sim.get(i));
		}
		
	}
}


