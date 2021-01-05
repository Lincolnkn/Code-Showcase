import java.time.LocalDate;
import java.util.*;

public class CardRegisterView {
    private Scanner scanner;

    public CardRegisterView() {
        scanner = new Scanner(System.in);
    }

    public void displayInformationToConsole(String input) {
        System.out.println(input);
    }

    public String[] getCardRegisterInfoFromUser() {
        System.out.println("Create a Frequent Flyer Card:");

        System.out.print("Enter full name: ");
        String name = scanner.nextLine();

        System.out.print("Enter total frequent flyers points: ");
        String frequentFlyingPoints = scanner.nextLine();

        String dateOfActivation = getDateFromConsole();

        String[] address = getAddressFromConsole();

        //Could also return whole address as 1 string split by ","
        return new String[]{name, frequentFlyingPoints, dateOfActivation,
                address[0], address[1], address[2], address[3], address[4], address[5]};
    }

    public String getCardTypeFromConsole() {
        System.out.print("Enter type of card. Platinum (P) or Titanium (T): ");
        return scanner.nextLine();
    }

    public String getFlyerCardIdFromConsole() {
        System.out.print("Enter ID: ");

        return scanner.nextLine();
    }

    private String getNameFromConsole() {
        System.out.print("Enter Name: ");

        return scanner.nextLine();
    }

    private String[] getAddressFromConsole() {
        //Can add verification to all variables if needed. i.e. ID = 10 digits
        System.out.println("What is your address?");
        System.out.print("Enter Street Number: ");
        String streetNumber = scanner.nextLine();

        System.out.print("Enter Street Name: ");
        String streetName = scanner.nextLine();

        System.out.print("Enter Suburb: ");
        String suburb = scanner.nextLine();

        System.out.print("Enter City: ");
        String city = scanner.nextLine();

        System.out.print("Enter State: ");
        String state = scanner.nextLine();

        System.out.print("Enter Postcode: ");
        String postCode = scanner.nextLine();

        return new String[]{streetNumber, streetName, suburb, city, state, postCode};
    }

    public String getDateFromConsole() {
        //Checks date to be valid
        String dateRegex = "[0-9]{1,2}(/|-)[0-9]{1,2}(/|-)[0-9]{4}"; //DD/MM/YYYY
        System.out.print("Enter date of activation. DD/MM/YYYY: ");
        String date = scanner.nextLine();

        do {
            if (date.matches(dateRegex) ){
                return date;
            } else {
                System.out.println("That was not a valid date.");
                System.out.print("Please enter date of activation. DD/MM/YYYY: ");
                date = scanner.nextLine();
            }
        } while (!date.matches(dateRegex));

        return date;
    }

}
