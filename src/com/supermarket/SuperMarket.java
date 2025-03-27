package com.supermarket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SuperMarket {
    private static final String data_file = "item_data.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSuperMarket Data Operation List:");
          System.out.println("1. List All Items");
            System.out.println("2. Add New Item");
            System.out.println("3. Remove Existing Item");
         System.out.println("4. Exit");
      System.out.print("Please Enter Your Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    list();
                    break;
                case 2:
                    add(scanner);
                    break;
                case 3:
                    remove(scanner);
                    break;
                case 4:
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("Invalid choice , Please Enter Valid Number");
            }
            
            }
        }
        
        
     //code for add remove and view and items   
        
        private static void list() 
        {
            try (BufferedReader bufferreader = new BufferedReader(new FileReader(data_file)))
            {
                String line;
                System.out.println("\n All Items:");
                while ((line = bufferreader.readLine()) != null) {
                    Item item = Item.fromString(line);
                    System.out.println("Name: " + item.name + ", Quantity: " + item.quantity + ", Date: " + item.date);
                }
            } 
            catch (IOException e) 
            {
                System.out.println("Not items found.");
            }
        }

        private static void add(Scanner scanner) 
        {
            System.out.print("Enter Item Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // newline
            String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

            try (BufferedWriter bufferwriter = new BufferedWriter(new FileWriter(data_file, true))) 
            {
            	bufferwriter.write(name + "," + quantity + "," + date);
            	bufferwriter.newLine();
                System.out.println("Item Added Successfully!");
            } 
            catch (IOException e) 
            {
                System.out.println("Error to saving item.");
            }
        }

        private static void remove(Scanner scanner) 
        {
            System.out.print("Enter Item Name to Remove: ");
            String Remove = scanner.nextLine();

            List<String> items = new ArrayList<>();

            try (BufferedReader bufferreader = new BufferedReader(new FileReader(data_file)))
            {
                String line;
                while ((line = bufferreader.readLine()) != null) 
                {
                    Item item = Item.fromString(line);
                    if (!item.name.equalsIgnoreCase(Remove))
                    {
                        items.add(line);
                    }
                }
            } 
            catch (IOException e)
            {
                System.out.println("Error reading file.");
                return;
            }


            try (BufferedWriter bufferwriter = new BufferedWriter(new FileWriter(data_file))) 
            {
                for (String item : items)
                {
                	bufferwriter.write(item);
                	bufferwriter.newLine();
                }
                System.out.println("Item Removed");
            } catch (IOException e) 
            {
                System.out.println(e);
            }
        }
    }

