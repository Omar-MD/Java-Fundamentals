package nested_classes.abstracts;

import java.util.Calendar;

public class AbstractBankingMain {

    public static void main(String[] args) {
        Bank bank = new Bank();
        initializeCustomers(bank);

        // run the customer report
        CustomerReport report = new CustomerReport();
        report.setBank(bank);
        report.generateReport();
    }

    private static void initializeCustomers(Bank bank) {
        Customer customer;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 180);

        bank.addCustomer("Jane", "Simms");
        customer = bank.getCustomer(0);
        customer.addAccount(new TimeDepositAccount(500.00, cal.getTime()));

        bank.addCustomer("Owen", "Bryant");
        customer = bank.getCustomer(1);

        bank.addCustomer("Tim", "Soley");
        customer = bank.getCustomer(2);
        customer.addAccount(new TimeDepositAccount(1500.00, cal.getTime()));

        bank.addCustomer("Maria", "Soley");
        customer = bank.getCustomer(3);

        customer.addAccount(new TimeDepositAccount(150.00, cal.getTime()));
    }
}