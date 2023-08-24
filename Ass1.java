import java.util.Scanner;

public class Ass1{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final String clear = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        String title="💰 Welcome to Smart Banking App";
        String title1="➕ Open New Account";
        String title2="Deposits";
        String title3="Withdrawals";

        String[][] clients=new String[0][3];
        
        boolean dashboardFlag=false;
        boolean flag=false;

        boolean openAccFlag1=false;
        boolean nameFlag1=false;
        boolean depositFlag1=false;
        boolean depositFlag2=false;
        boolean accNumberFlag2=false;
        boolean amountFlag2=false;
        boolean withdrawFlag3=false;
        boolean accNumberFlag3=false;
        boolean amountFlag3=false;


        String id;
        String name;
        int initialDeposit;
        double balance=0;
        int deposit;
        String accNumber;
        int index=-1;
        int withdraw;

        do{
            dashboardFlag=false;
            System.out.println(clear);
            System.out.println("-".repeat(32));
            System.out.printf("%s%s%s \n",COLOR_BLUE_BOLD,title,RESET);
            System.out.println("-".repeat(32));
            System.out.println("[1] Open New Account");
            System.out.println("[2] Deposits");
            System.out.println("[3] Withdrawals");
            System.out.println("[4] Transfer");
            System.out.println("[5] Print statement");
            System.out.println("[6] Delete Account");
            System.out.println("[7] Exit");

        do{
            flag=false;
            
            System.out.print("Enter an option to continue:");
            int option=scanner.nextInt();
            scanner.nextLine();

            if(option<1 || option>7){
                System.out.printf("%sInvalid option%s \n",COLOR_RED_BOLD,RESET);
                flag=true;
            }

            switch(option){
                case 1:
                do{
                    openAccFlag1=false;
                System.out.println(clear);
                System.out.println("-".repeat(32));
                System.out.printf("%s%s%s \n",COLOR_BLUE_BOLD,title1,RESET);
                System.out.println("-".repeat(32));
                System.out.printf("ID: SDB-%05d \n",clients.length+1);
                id="SDB-"+(clients.length+1);
                System.out.println();

                do{
                    nameFlag1=false;
                    System.out.print("Name: ");
                    name=scanner.nextLine().strip();

                    if(name.isBlank()){
                        System.out.printf("%sName can't be empty%s \n",COLOR_RED_BOLD,RESET);
                        nameFlag1=true;
                    }
                    for(int i=0;i<name.length();i++){
                        if(!Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i))){
                            System.out.printf("%s%sInvalid Name%s ","\b".repeat(15),COLOR_RED_BOLD,RESET);
                            nameFlag1=true;
                        }
                    }
                    System.out.println();

                }while(nameFlag1);

                do{
                    depositFlag1=false;
                    System.out.print("Initial Deposit: ");
                    initialDeposit=scanner.nextInt();
                    scanner.nextLine();

                    if(initialDeposit<500){
                        System.out.printf("%sInvalid Deposit%s",COLOR_RED_BOLD,RESET);
                        depositFlag1=true;
                    }
                    System.out.println();
                    }while(depositFlag1);

                    String[][] newClients=new String[clients.length+1][3];

                    for(int i=0;i<clients.length;i++){
                        for(int j=0;j<clients[i].length;j++){
                             newClients[i][j]=clients[i][j];
                        }  
                    }
                    
                    balance=initialDeposit;
            
                    newClients[newClients.length-1][0]=id;
                    newClients[newClients.length-1][1]=name;
                    newClients[newClients.length-1][2]=balance+"";
                    clients=newClients;

                    System.out.printf("%s%s:%s has been added successfully%s",COLOR_GREEN_BOLD,id,name,RESET);
                    System.out.println();
                    System.out.println("Do you want to add another ? [y/n]");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                        openAccFlag1=true;
                    }else{continue;}

            }while(openAccFlag1);

                case 2:
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

                case 3:
                do{
                    withdrawFlag3=false;
                    System.out.println(clear);
                    System.out.println("-".repeat(32));
                    System.out.printf("%s%s%s \n",COLOR_BLUE_BOLD,title3,RESET);
                    System.out.println("-".repeat(32));
            
                    do{
                        accNumberFlag3=false;
                        System.out.print("Enter A/C Number: ");
                        accNumber=scanner.nextLine().strip();
            
                        if(accNumber.isBlank()){
                            System.out.printf("%sA/C Number can't be empty%s",COLOR_RED_BOLD,RESET);
                            System.out.println();
                            System.out.println("Do you want to try again? [Y/N]");
                            if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                                accNumberFlag3=true;
                                continue;
                            }
                        }else if(!accNumber.startsWith("SDB-")){
                            System.out.printf("%sInvalid Format%s",COLOR_RED_BOLD,RESET);
                            System.out.println();
                            System.out.println("Do you want to try again? [Y/N]");
                            if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                                accNumberFlag3=true;
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
                                    accNumberFlag3=true;
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
                                    accNumberFlag3=true;
                                    continue;
                                }
                        }
                        }while(accNumberFlag3);
            
                        if(index>-1){
                            System.out.println();
                            System.out.printf("Current Balance: Rs.%,.2f",clients[index][2]);
                            System.out.println();
            
                            do{
                            amountFlag3=false;
                            System.out.print("Withdraw Amount: ");
                            withdraw=scanner.nextInt();
                            scanner.nextLine();
            
                            if(withdraw<100){
                                System.out.println();
                                System.out.printf("%sInsufficient Amount2!%s",COLOR_RED_BOLD,RESET);
                                System.out.println();
                                amountFlag3=true;
                            }
                            }while(amountFlag3);
            
                            balance=balance-withdraw;
                            if(balance<500){
                                System.out.printf("%sInsufficient Balance!%s",COLOR_RED_BOLD,RESET);
                                System.out.println();
                            }
            
                            if(balance>500 || balance==500){
                            System.out.printf("New Balnace: %,.2f",balance);
                            System.out.println();
                            }
            
                            System.out.println("Do you want to try again? [Y/N]");
                                if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                                    withdrawFlag3=true;
                                    continue;
                                }
                        }
            
            
                    
                }while(withdrawFlag3);
            


                case 7: System.exit(0);
            }

        }while(flag);
    }while(dashboardFlag);

    }
}