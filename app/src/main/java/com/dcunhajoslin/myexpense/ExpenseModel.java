package com.dcunhajoslin.myexpense;

public class ExpenseModel {


    private String expense_date, amount, expense_name;

    public ExpenseModel(String expense_date, String amount, String expense_name) {
        this.expense_date = expense_date;
        this.amount = amount;
        this.expense_name = expense_name;
    }


    public String getExpense_date() {
        return expense_date;
    }

    public void setExpense_date(String expense_date) {
        this.expense_date = expense_date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExpense_name() {
        return expense_name;
    }

    public void setExpense_name(String expense_name) {
        this.expense_name = expense_name;
    }
}
