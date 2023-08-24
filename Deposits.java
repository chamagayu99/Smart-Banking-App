import java.util.Scanner;

public class Deposits {
        private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final String clear = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        String title2="Deposits";

        String[][] clients=new String[5][3];
        clients[0][0]="SDB-00001";
    
        String name;
        String id;
        int initialDeposit;
        int deposit;
        String accNumber;
        double balance=0;
        int index=0;

        boolean depositFlag2=false;
        boolean accNumberFlag2=false;
        boolean amountFlag2=false;

        do{
        depositFlag2=false;
        System.out.println(clear);
        System.out.println("-".repeat(32));
        System.out.printf("%s%s%s \n",COLOR_BLUE_BOLD,title2,RESET);
        System.out.println("-".repeat(32));

        do{
            accNumberFlag2=false;
            System.out.print("Enter A/C Number: ");
            accNumber=scanner.nextLine().strip();

            if(accNumber.isBlank()){
                System.out.printf("%sA/C Number can't be empty%s",COLOR_RED_BOLD,RESET);
                System.out.println();
                System.out.println("Do you want to try again? [Y/N]");
                if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                    accNumberFlag2=true;
                    continue;
                }
            }else if(!accNumber.startsWith("SDB-")){
                System.out.printf("%sInvalid Format%s",COLOR_RED_BOLD,RESET);
                System.out.println();
                System.out.println("Do you want to try again? [Y/N]");
                if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                    accNumberFlag2=true;
                    continue;
                }
            }
            String num="";
            if(accNumber.length()>4){
            num=accNumber.substring(4);
            }
            int count=0;
            for(int i=0;i<num.length();i++){
                if(Character.isDigit(num.charAt(i))){
                    count++;
                }
            }
            if(count==0 || count<5){
                    System.out.printf("%sInvalid Acount Number%s",COLOR_RED_BOLD,RESET);
                    System.out.println();
                    System.out.println("Do you want to try again? [Y/N]");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                        accNumberFlag2=true;
                        continue;
                    }
            }
            index=-1;
            for(int i=0;i<clients.length;i++){
                if(accNumber==clients[i][0]){
                    index=i;
                }
            }

            if(index==-1){
                System.out.printf("%sNot Found%s",COLOR_RED_BOLD,RESET);
                    System.out.println();
                    System.out.println("Do you want to try again? [Y/N]");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                        accNumberFlag2=true;
                        continue;
                    }
            }
            }while(accNumberFlag2);

            if(index>-1){
                System.out.println();
                System.out.printf("Current Balance: Rs.%,.2f",clients[index][2]);
                System.out.println();

                do{
                amountFlag2=false;
                System.out.print("Deposit Amount: ");
                deposit=scanner.nextInt();
                scanner.nextLine();

                if(deposit<500){
                    System.out.println();
                    System.out.printf("%sInsufficient Amount2!%s",COLOR_RED_BOLD,RESET);
                    System.out.println();
                    amountFlag2=true;
                }
                }while(amountFlag2);

                if(deposit>500 || deposit==500){
                balance=balance+deposit;
                System.out.printf("New Balnace: %,.2f",balance);
                System.out.println();
                }

                System.out.println("Do you want to try again? [Y/N]");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                        depositFlag2=true;
                        continue;
                    }
            }


        
    }while(depositFlag2);

        
        
    }
    
}
