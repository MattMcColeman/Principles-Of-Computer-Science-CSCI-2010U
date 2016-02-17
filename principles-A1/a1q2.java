import java.io.FileNotFoundException;
import java.io.PrintWriter;

class a1q2{
	public static void main(String[] args){
		final long startTime = System.currentTimeMillis();
		int n = 100;
		int p = 0;
			int i;
			int num = 0;
			String fileName = "out.txt";
			try{
				PrintWriter outputStream = new PrintWriter(fileName);
			while(true){
				int reps = 0;
				for(i=num;i>=1;i--){
					if(num==1){
						outputStream.println(num);
					}
					if((num%i)==0){
						reps = reps + 1;
						//System.out.println(num);
						//outputStream.println(i);
					} else {
						//reps = reps + 1;
						//System.out.println(num);
					}
				}
				if(reps==2){
					System.out.println(num);
					outputStream.println(num);
					p = p + 1;
					if(p >= n){
						break;
					}
				}
				num = num + 1;
			} 
		
		final long elapsedTimeMillis = System.currentTimeMillis() - startTime;
		outputStream.println("Time to excecute program in milliseconds: " + elapsedTimeMillis);
		outputStream.close(); 
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
}