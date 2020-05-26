import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.IOException;
import java.lang.Math;

class AngleFile {
    public static void main(String args[]) throws IOException {
        System.out.println("*** Mani Kumar Reddy Kancharla 13B81A0579 ***");
        int opposite = 0, adjacent = 0;
        double angle1, angle2, pi = 3.14159265;
        Scanner keyboardScanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        Scanner fileScanner = new Scanner(new File(keyboardScanner.next()));
        keyboardScanner.close();
        String line1 = fileScanner.nextLine();
        String line2 = fileScanner.nextLine();
        fileScanner.close();
        for(int i = 1; i < 4; i ++) {
            opposite = opposite * 10 + line1.charAt(i) - 48;
        }
        for(int i = 5; i < 8; i ++) {
            adjacent = adjacent * 10 + line1.charAt(i) - 48;
        }
        angle1 = Math.atan((double) opposite/adjacent) * 180 / pi;
        adjacent = 0;
        opposite = 0;
        for(int i = 1; i < 4; i ++) {
            opposite = opposite * 10 + line2.charAt(i) - 48;
        }
        for(int i = 5; i < 8; i ++) {
            adjacent = adjacent * 10 + line2.charAt(i) - 48;
        }
        angle2 = Math.atan((double) opposite/adjacent) * 180 / pi;
        if(angle2 > angle1) {
            System.out.println("Angle 2 is " + angle2 + " degrees.");
            System.out.println("Angle 1 is " + angle1 + " degrees.");
        }
        else {
            System.out.println("Angle 1 is " + angle1 + " degrees.");
            System.out.println("Angle 2 is " + angle2 + " degrees.");
        }
    }
}