

import java.io.*;
import java.util.*;
import java.io.*;

class a1q1 {
	public static void main(String[] args) {
		String user;
		user = enterUN();
		if(read(user)){
			write(user);
		}
	}

	public static String enterUN(){
		Scanner user_input = new Scanner( System.in );
		
		String first_name;
		System.out.print("Enter your first name: ");
		first_name = user_input.next( );
		first_name = first_name.substring(0, Math.min(first_name.length(), 1));
		//System.out.print(first_name + "\b\b\b");
		//System.out.print(first_name.length()+"\n");
		int first_name_length = first_name.length();

		String last_name;
		System.out.print("Enter your last name: ");
		last_name = user_input.next( );
		last_name = last_name.substring(0, Math.min(last_name.length(), 5));
		//System.out.print(last_name);
		//System.out.print(last_name.length()+"\n");
		int last_name_length = last_name.length();


		String number;
		if(last_name_length < 2){
			number = "000000";	
		} else if(last_name_length < 3) {
			number = "00000";
		} else if(last_name_length < 4){
			number = "0000";
		} else {
			number = "000";
		}

		String user = first_name + last_name + number;
		return user;
	}

	public static void write(String username){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("username.db", true)))) {
		    
		    out.println(username);
		}catch (IOException e) {
		    e.printStackTrace();
		}
	}

	public static boolean read(String username){
		// The name of the file to open.
        String textFile = "username.db";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(textFile);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if(username.equals(line)){
                	return false;
                }
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                textFile + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + textFile + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return true;
	}
}