import java.util.Scanner;

public class Temp {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "👷 Welcome to Customer Managment System";
        final String ADD_CUSTOMER = "➕ Add New Customer";
        final String REMOVE_CUSTOMER = "❌ Remove Exisiting Customer";
        final String PRINT_DETAILS = "🖨 Print Customer Details";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String[] customerIds = new String[0];
        String[] customerNames = new String[0];

        String screen = DASHBOARD;

        mainLoop:
        do{
            final String APP_TITLE = String.format("%s%s%s",
            COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch(screen){
                case DASHBOARD: 
                    System.out.println("\t[1]. Add New Customer");
                    System.out.println("\t[2]. Remove Existing Customer");
                    System.out.println("\t[3]. Print Customer Details");
                    System.out.println("\t[4]. Exit\n");
                    System.out.print("\tEnter an option to continue: ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option){
                        case 1: screen = ADD_CUSTOMER; break;
                        case 2: screen = REMOVE_CUSTOMER; break;
                        case 3: screen = PRINT_DETAILS; break;
                        case 4: System.out.println(CLEAR); System.exit(0);
                        default: continue;
                    }
                    break;
                case ADD_CUSTOMER:
                    String id;
                    String name;
                    boolean valid;

                    // ID Validation
                    do {
                        valid = true;
                        System.out.print("\tEnter New Customer ID: ");  // C-ac
                        id = SCANNER.nextLine().toUpperCase().strip();
                        if (id.isBlank()){
                            System.out.printf(ERROR_MSG, "ID can't be empty");
                            valid = false;
                        }else if (!id.startsWith("C-") || id.length() < 3){
                            System.out.printf(ERROR_MSG, "Invalid ID format");
                            valid = false;
                        }else{
                            String number = id.substring(2);
                            for (int i = 0; i < number.length(); i++) {
                                if (!Character.isDigit(number.charAt(i))){
                                    System.out.printf(ERROR_MSG, "Invalid ID format");
                                    valid = false;
                                    break;
                                }
                            }
                            for (int i = 0; i < customerIds.length; i++) {
                                if (customerIds[i].equals(id)){
                                    System.out.printf(ERROR_MSG, "Customer ID already exists");
                                    valid = false;
                                    break;
                                }
                            }    
                        }
                    }while (!valid);

                    // Name Validation
                    do{
                        valid = true;
                        System.out.print("\tEnter Customer Name: ");
                        name = SCANNER.nextLine().strip();
                        if (name.isBlank()){
                            System.out.printf(ERROR_MSG, "Customer name can't be empty");
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) || 
                                Character.isSpaceChar(name.charAt(i))) ) {
                                System.out.printf(ERROR_MSG, "Invalid name");
                                valid = false;
                                break;
                            }
                        }
                    }while(!valid);

                    String[] newCustomerIds = new String[customerIds.length + 1];
                    String[] newCustomerNames = new String[customerNames.length + 1];
                    for (int i = 0; i < customerIds.length; i++) {
                        newCustomerIds[i] = customerIds[i];
                        newCustomerNames[i] = customerNames[i];
                    }
                    newCustomerIds[newCustomerIds.length - 1] = id;
                    newCustomerNames[newCustomerIds.length - 1] = name;
                    customerIds = newCustomerIds;
                    customerNames = newCustomerNames;

                    System.out.println();
                    System.out.printf(SUCCESS_MSG, 
                        String.format("%s:%s has been saved successfully", id, name));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                case PRINT_DETAILS:
                    if (customerIds.length == 0){
                        System.out.printf(ERROR_MSG, "No customer records found, please add a new customer!");                      
                    }else {
                        final String LINE = "\t+" + "-".repeat(6) + "+" + "-".repeat(15) + "+";
                        System.out.println(LINE);
                        // Header
                        System.out.printf("\t|%-6s|%-15s|\n", "ID", "NAME");
                        System.out.println(LINE);
                        // Body
                        for (int i = 0; i < customerIds.length; i++) {
                            System.out.printf("\t|%-6s|%-15s|\n", customerIds[i],
                            customerNames[i]);  
                        }
                        System.out.println(LINE);
                    }

                    System.out.print("\tDo you want to go back? (Y/n) ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) screen = DASHBOARD;
                    break;
                case REMOVE_CUSTOMER:

                    int index = 0;
                    // ID Validation
                    do {
                        valid = true;
                        System.out.print("\tEnter the Customer ID to delete: ");
                        id = SCANNER.nextLine().toUpperCase().strip();
                        if (id.isBlank()){
                            System.out.printf(ERROR_MSG, "ID can't be empty");
                            valid = false;
                        }else if (!id.startsWith("C-") || id.length() < 3){
                            System.out.printf(ERROR_MSG, "Invalid ID format");
                            valid = false;
                        }else{
                            String number = id.substring(2);
                            for (int i = 0; i < number.length(); i++) {
                                if (!Character.isDigit(number.charAt(i))){
                                    System.out.printf(ERROR_MSG, "Invalid ID format");
                                    valid = false;
                                    break;
                                }
                            }
                            boolean exists = false;
                            for (int i = 0; i < customerIds.length; i++) {
                                if (customerIds[i].equals(id)){
                                    index = i;
                                    exists = true;
                                    break;
                                }
                            }    
                            if (!exists){
                                valid = false;
                                System.out.printf(ERROR_MSG, "Customer ID does not exist");
                            }
                        }
                        if (!valid) {
                            System.out.print("\n\tDo you want to try again? (Y/n)");
                            if (!SCANNER.nextLine().strip().toUpperCase().equals("Y")){
                                screen = DASHBOARD;
                                continue mainLoop;
                            }
                            System.out.println();
                        }
                    }while (!valid);

                    newCustomerIds = new String[customerIds.length - 1];
                    newCustomerNames = new String[newCustomerIds.length];

                    for (int i = 0; i < customerIds.length; i++) {
                        if (i < index){
                            newCustomerIds[i] = customerIds[i];
                            newCustomerNames[i] = customerNames[i];
                        }else if (i == index){
                            continue;
                        }else{
                            newCustomerIds[i - 1] = customerIds[i];
                            newCustomerNames[i - 1] = customerNames[i];
                        }
                    }

                    customerIds = newCustomerIds;
                    customerNames = newCustomerNames;

                    System.out.println();
                    System.out.printf(SUCCESS_MSG, 
                        String.format("%s has been deleted successfully", id));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
            }
        }while(true);
    }
}
