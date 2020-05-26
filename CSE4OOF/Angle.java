import java.util.Scanner;
import java.lang.Math;

class Angle {
    public static void main(String args[]) {
        System.out.println("*** Mani Kumar Reddy Kancharla 13B81A0579 ***");
        int opposite = 0, adjacent = 0, hypotenuse = 0;
        double angle, pi = 3.14159265;
        String choice;
        Scanner keyboardScanner = new Scanner(System.in);
        System.out.print("Do you know the length of the opposite side: ");
        choice = keyboardScanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter the length of the opposite side: ");
            opposite = keyboardScanner.nextInt();
        }
        System.out.print("Do you know the length of the adjacent side: ");
        choice = keyboardScanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter the length of the adjacent side: ");
            adjacent = keyboardScanner.nextInt();
        }
        System.out.print("Do you know the length of the hyportenuse: ");
        choice = keyboardScanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter the length of the hyportenuse: ");
            hypotenuse = keyboardScanner.nextInt();
        }
        if(opposite > 0) {
            if (hypotenuse > 0) {
                angle = Math.asin((double) opposite/hypotenuse);
                System.out.println("The angle is " + angle + " radians or " + angle * 180 / pi + " degrees");
            }
            else if(adjacent > 0) {
                angle = Math.atan((double) opposite/adjacent);
                System.out.println("The angle is " + angle + " radians or " + angle * 180 / pi + " degrees");
            }
            else {
                System.out.println("The angle can not be calculate from the given information");
            }
        }
        else if (hypotenuse > 0 && adjacent > 0) {
            angle = Math.acos((double) adjacent/hypotenuse);
            System.out.println("The angle is " + angle + " radians or " + angle * 180 / pi + " degrees");
        }
        else {
            System.out.println("The angle can not be calculate from the given information");
        }
        keyboardScanner.close();
    }
}