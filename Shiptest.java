package unit17;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.io.*;
import java.util.*;
import static java.lang.System.*;

public class Shiptest
{
    public static void main( String args[] )
    {
        MovingThing test = new MarioLeft();
        System.out.println("Ship 1 " + test);

        MarioLeft test2 = new MarioLeft(50,75);
        System.out.println("Ship 2 " + test2);

        MarioLeft test3 = new MarioLeft();
        test3.setX(3);
        test3.setY(5);
        System.out.println("Ship 3 " + test2);
    }
}
