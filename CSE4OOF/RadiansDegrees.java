import java.util.Scanner;

class RadiansDegrees {
    public static void main(String args[]) {
        System.out.println("*** Mani Kumar Reddy Kancharla 13B81A0579 ***");
        double pi = 3.14159265, radians = 0, degrees = 0;
        Scanner keyboardScanner = new Scanner(System.in);
        System.out.print("Do you want to conver from 1. Convert from radians to degrees, or 2. Degrees to radians: ");
        byte choice = keyboardScanner.nextByte();
        if (choice == 1) {
            System.out.print("Enter the angle in radians: ");
            radians = keyboardScanner.nextDouble();
            degrees = radians * 180 / pi;
            System.out.println(degrees + " degrees");
        }
        else if(choice == 2) {
            System.out.print("Enter the angle in degrees: ");
            degrees = keyboardScanner.nextDouble();
            radians = degrees / 180 * pi;
            System.out.println(radians + " radians");
        }
        else {
            System.out.println("Invalid choice");
        }
        keyboardScanner.close();
    }
}