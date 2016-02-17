package btSample;

public class btSample {
	  public static void main(String[] args) {

	    int integerRange = 100;
	    int streamLength = 10;
	    int seed = 0;

	    Stream stream = new Stream(integerRange, seed);  // 100 is the range of integers
	    StreamProcessor processor = new StreamProcessor();


	    for (int i=0; i<streamLength; ++i) {
	      processor.consume(stream.getNext());
	    }

	    // HERE YOU WILL PERFORM SEARCHES AND COLLECT TIMES
	    // AND PREPARE THE OUTPUT

	    processor.show();
	    //int[] target = processor.search(92);
	    System.out.println(processor.search(92));
	   // System.out.println(processor.at(6));
	}
}