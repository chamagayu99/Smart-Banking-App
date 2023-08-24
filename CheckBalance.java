import java.util.Scanner;

public class CheckBalance {
            private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final String clear = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        String title5="Check Account Balance";

        double[] balances=new double[0];
        String[][] clients=new String[5][3];
        clients[0][0]="SDB-00001";
    
        String accNumber;
        int index=0;

        boolean checkBalanceFlag5=false;
        boolean accNumberFlag5=false;

        do{
        checkBalanceFlag5=false;
        System.out.println(clear);
        System.out.println("-".repeat(32));
        System.out.printf("%s%s%s \n",COLOR_BLUE_BOLD,title5,RESET);
        System.out.println("-".repeat(32));

        do{
            accNumberFlag5=false;
            System.out.print("Enter A/C Number: ");
            accNumber=scanner.nextLine().strip();

            if(accNumber.isBlank()){
                System.out.printf("%sA/C Number can't be empty%s",COLOR_RED_BOLD,RESET);
                System.out.println();
                System.out.println("Do you want to try again? [Y/N]");
                if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                    accNumberFlag5=true;
                    continue;
                }
            }else if(!accNumber.startsWith("SDB-")){
                System.out.printf("%sInvalid Format%s",COLOR_RED_BOLD,RESET);
                System.out.println();
                System.out.println("Do you want to try again? [Y/N]");
                if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                    accNumberFlag5=true;
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
                        accNumberFlag5=true;
                        continue;
                    }
            }
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
                        accNumberFlag5=true;
                        continue;
                    }
            }
            }while(accNumberFlag5);   

            if(index>-1){
                System.out.printf("Name: %s",clients[index][1]); 
                System.out.println();
                System.out.printf("Current Account Balance: Rs.%,.2f",balances[index]);
                System.out.println();

                if(balances[index]>500){
                double withdrawableBalance=balances[index]-500;
                System.out.printf("Amount that can be withdrawn: %,.2f",withdrawableBalance);
                System.out.println();
                }

                System.out.println("Do you want to try again? [Y/N]");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")){
                        checkBalanceFlag5=true;
                        continue;
                    }
            }


        
    }while(checkBalanceFlag5);


    }
    
}
