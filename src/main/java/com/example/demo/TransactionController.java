package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by student on 6/23/17.
 */
@Controller
public class TransactionController {

private float depositSum=0;
private float withdrawalSum=0;


    public float balance;

        @Autowired
        private TransactionRepository transactionRepository;

        @RequestMapping("/")
        public String home( Model model) {
            Iterable<Transaction> transactionList = transactionRepository.findAll();
            model.addAttribute("transactionList", transactionList);
            depositSum=0;
            withdrawalSum=0;
            for (Transaction t :
                    transactionList) {
                balance=t.getBalance();
                if (t.getAcctNumber() == 200) {
                    if (
                            t.getActionType().equalsIgnoreCase("deposit")) {
                        depositSum += t.getAmount();
                    } else if (
                            t.getActionType().equalsIgnoreCase("withdrawal")) {
                        withdrawalSum += t.getAmount();
                    }
                }
                balance=depositSum - withdrawalSum;
                t.setBalance(balance);
            }

            return "index";
        }

        @RequestMapping("/add")
        public String goAdd( Model model) {
            model.addAttribute(new Transaction());
            return "add";
        }

        @PostMapping("/addsubmit")
        public String addSubmit(@ModelAttribute Transaction transaction, Model model) {
           /* model.addAttribute(new Transaction());*/
           /*To add a record to a table, use save(transaction)*/
            Iterable<Transaction> transactionList = transactionRepository.findAll();
            model.addAttribute("transactionList", transactionList);
            depositSum=0;
            withdrawalSum=0;
            for (Transaction t :
                    transactionList) {
                balance=t.getBalance();
                if (t.getAcctNumber() == 200) {
                    if (
                            t.getActionType().equalsIgnoreCase("deposit")) {
                        depositSum += t.getAmount();
                    } else if (
                            t.getActionType().equalsIgnoreCase("withdrawal")) {
                        withdrawalSum += t.getAmount();
                    }
                }
                balance=depositSum - withdrawalSum;

                t.setBalance(balance);
            }
           transaction.setBalance(balance);
            transactionRepository.save(transaction);
        /*    Iterable<Transaction> transactionList = transactionRepository.findAll();
            model.addAttribute("transactionList", transactionList);*/

            return "redirect:/";
        }
        @RequestMapping("/balance")
        public String goBalance(@ModelAttribute Transaction transaction, Model model) {
            /*model.addAttribute(new Transaction());*/

            Iterable<Transaction> transactionList = transactionRepository.findAll();
            model.addAttribute("transactionList", transactionList);
            depositSum=0;
            withdrawalSum=0;
            for (Transaction t :
                    transactionList) {
                balance=t.getBalance();
                if (t.getAcctNumber() == 200) {
                    if (
                            t.getActionType().equalsIgnoreCase("deposit")) {
                        depositSum += t.getAmount();
                    } else if (
                            t.getActionType().equalsIgnoreCase("withdrawal")) {
                        withdrawalSum += t.getAmount();
                    }
                }
                 balance=depositSum - withdrawalSum;
                t.setBalance(balance);
            }

            return "balance";
        }



    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String goLogout() {
        return "login";
    }



    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }


}