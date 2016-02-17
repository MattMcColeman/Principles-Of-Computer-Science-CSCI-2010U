package btSample;

import java.util.Random;

class Stream {
  private Random generator;
  private int max;

  public Stream(int max, int seed) 
  {

    // Don't pass anything to the Random number
    // generator if you want to create a different
    // sequence of numbers at each time.
    //
    // I want to generate the same sequence.  It is 
    // easier to test this *simple* program that way.
    // 
    // But please do remember to always test your program
    // by generating difference sequences of 
    // integers.
    generator = new Random(seed);

    this.max = max;
  }

  int getNext() {
    return generator.nextInt(max) + 1;
  }
}