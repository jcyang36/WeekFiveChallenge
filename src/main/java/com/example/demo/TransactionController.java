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



        @Autowired
        private TransactionRepository transactionRepository;

        @RequestMapping("/")
        public String home( Model model) {
            Iterable<Transaction> transactionList = transactionRepository.findAll();
            model.addAttribute("transactionList", transactionList);
            return "index";
        }

        @RequestMapping("/add")
        public String goAdd( Model model) {
            model.addAttribute(new Transaction());
            return "add";
        }

        @PostMapping("/addsubmit")
        public String addSubmit(@ModelAttribute Transaction transaction, Model model) {
            model.addAttribute(new Transaction());
            transactionRepository.save(transaction);
            Iterable<Transaction> transactionList = transactionRepository.findAll();
            model.addAttribute("transactionList", transactionList);

            return "index";
        }



    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String goLogout() {
        return "login";
    }

    }

