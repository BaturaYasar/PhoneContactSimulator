package com.example.phonecontactsimulator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private static int id = 0;

    public static void main(String[] args) {

        contacts = new ArrayList<>();
        System.out.println("\nWelcome to my humble of programming:)");
        showMenu();

    }

    private static void showMenu() {
        System.out.println("\n\t1. Manage Contacts" +
                "\n\t2. Messages" +
                "\n\t3. Quit");

        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;
        }
    }

    private static void manageMessages() {
        System.out.println("Please select one:" +
                "\n\t1. Show all messages" +
                "\n\t2. Send a new message" +
                "\n\t3. Go back");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllMessages();
                break;
            case 2:
                sendMessage();
                break;
            default:
                showMenu();
                break;
        }
    }

    private static void sendMessage() {
        System.out.println("Who are you gonna send the message?");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please enter the name of the contact");
            sendMessage();
        }else{
            boolean doesExist = false;
            for (Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if (doesExist){
                System.out.println("What are you gonna say?");
                String text = scanner.next();
                if (text.equals("")){
                    System.out.println("Please enter some message");
                    sendMessage();
                }else{
                    id++;
                    Message newMessage = new Message(text,name,id);
                    for (Contact c: contacts){
                        if (c.getName().equals(name)){
                            ArrayList<Message> newMessages = c.getMessages();
                            newMessages.add(newMessage);
                            c.setMessages(newMessages);
//                            Contact currentContact = c;
//                            currentContact.setMessages(newMessages);
//                            contacts.remove(c);
//                            contacts.add(currentContact);
                        }
                    }
                }
            }else{
                System.out.println();
            }
        }
        showMenu();
    }

    private static void showAllMessages() {
        ArrayList<Message> allMessages = new ArrayList<>();
        for (Contact c: contacts){
            allMessages.addAll(c.getMessages());
        }
        if (allMessages.size() > 0){
            for (Message m: allMessages){
                m.getDetails();
                System.out.println("***********");

            }
            }else {
            System.out.println("You do not have any message");
        }
        showMenu();
    }

    private static void manageContacts() {

        System.out.println("Select the option:" +
                "\n\t1. Show all contacts" +
                "\n\t2. Add a new contact" +
                "\n\t3. Search for a contact" +
                "\n\t4. Delete a contact" +
                "\n\t5. Go back");

        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllContacts();
                break;
            case 2:
                addContact();
                break;
            case 3:
                searchContact();
                break;
            case 4:
                deleteContact();
                break;
            case 5:
                showMenu();
        }

    
    }

    private static void deleteContact() {
        System.out.println("Please enter the contact that you would like to delete: ");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please enter the contact! :");
            deleteContact();
        }else{

            boolean doesExist = false;

            for (Contact c: contacts
                 ) {
                if (c.getName().equals(name)){
                    doesExist = true;
                    contacts.remove(c);
                }
            }
            if (!doesExist){
                System.out.println("There is no such contact in your phone!");
                deleteContact();
            }
        }
        showMenu();
    }

    private static void searchContact() {
        System.out.println("Please write the contact that you would like to search: ");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please write the name!");
            searchContact();
        }else{
            boolean doesExist = false;
            for (Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                    c.getDetails();
                }
            }
            if (!doesExist){
                System.out.println("There is no such contact in your phone!");
                showMenu();
            }
        }
    }

    private static void addContact() {
        System.out.println("Adding a new contact...\n" +
                "Please enter the contact name: ");
        String name = scanner.next();

        System.out.println("Please enter the email: ");
        String email = scanner.next();

        System.out.println("Please enter the number: ");
        String number = scanner.next();

        if (name.equals("") || email.equals("") || number.equals("")){
            System.out.println("Please fill the information!");
            addContact();
        }else{
            
            boolean doesExist = false;
            for (Contact c: contacts
                 ) {
                if (c.getName().equals(name)){
                    doesExist = true;
                }
                
            }
            if (doesExist == true){
                System.out.println("We have a contact with this name.");
                addContact();
            }else{
                Contact contact = new Contact(name, email, number);
                contacts.add(contact);
                System.out.println(name + " added successfully");
            }

        }
        showMenu();
    }

    private static void showAllContacts() {
        if (contacts.size() > 0){
                for (Contact c: contacts
                ) {
                    c.getDetails();
                    System.out.println("***********");
                }
            }else{
            System.out.println("You do not have any contact.");
        }
        showMenu();
    }
}
