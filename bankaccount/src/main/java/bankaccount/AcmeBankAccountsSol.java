package bankaccount;

public class AcmeBankAccountsSol {
    private String holder;  
    private int accountNumber;  
    private static int idAccounts = 1; //First number account  to use 
    private static int BankBalance = 0; // Total bank balance 
    private int accountBalance = 0;

    // constructor
    public AcmeBankAccountsSol(String holder, int accountBalance){
        //in this method we are gonna create a new account
        this.holder = holder;   // we assing the holder
        if (accountBalance < 0){
            accountBalance = 0;
        }
        // we check the initial balance not to be negative value
        this.accountBalance = accountBalance;
        AcmeBankAccountsSol.BankBalance += this.accountBalance;
        idAccounts+= 1;
        this.accountNumber = idAccounts;
        System.out.println("account created successfully");
    }
 
    //class method
    public static void printBankData() { 
        // Print the total Balance of the bank and the current accounts. 
        System.out.println("Total bank money:"+ Integer.toString(AcmeBankAccountsSol.BankBalance));
        System.out.println("Total number of accounts created: "+Integer.toString(AcmeBankAccountsSol.idAccounts -1));
        } 

    //instance method
    public void  withdrawal (int amount) { 
        // For the current account, withdrawal the indicated amount 
        // Decrement also the Bank balance         
        this.accountBalance -= amount;
        AcmeBankAccountsSol.BankBalance -= amount;
        System.out.println("Operation withdrawal completed");
        } 

    //instance method
    public void deposit(int amount) {        
        this.accountBalance += amount;
        AcmeBankAccountsSol.BankBalance += amount;
        System.out.println("Operation deposit completed");
        } 


    //instance method
    public void transfer (AcmeBankAccountsSol destinationAccount, int amount) { 
        //Transfert the amount to the destination Account 
        this.withdrawal(amount);
        destinationAccount.deposit(amount);
        System.out.println("Operation transfer completed");
        } 


    public void printAccountData() { 
        // print the account number, holder and  account balance 
        System.out.println("The holder of the account is "+this.holder);
        System.out.println("The account number is "+Integer.toString(this.accountNumber));
        System.out.println("The balance is "+Integer.toString(this.accountBalance));
        } 

    public static void main(String[] args) {
        AcmeBankAccountsSol cJuan = new AcmeBankAccountsSol("Juan",200);
        
        AcmeBankAccountsSol cPepe = new AcmeBankAccountsSol("Pepe",0);
        
        cJuan.printAccountData();
        cPepe.printAccountData();

        cJuan.transfer(cPepe, 100);
        cPepe.withdrawal(50);
        cJuan.deposit(500);

        cJuan.printAccountData();
        cPepe.printAccountData();

        AcmeBankAccountsSol.printBankData();

    }

}

