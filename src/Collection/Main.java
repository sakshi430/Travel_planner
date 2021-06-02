package Collection;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    static int check(){                    //check if the input entered by the user is int or not
        Scanner sc=new Scanner(System.in);
        System.out.print("\t\tEnter input: ");
        String temp=sc.next();

        boolean flag=true;
        try {
            Integer num = Integer.parseInt(temp);
        } catch (NumberFormatException e) {
            flag = false;
        }

        if(!flag) {
            System.out.println(temp + " is not a number. Enter a valid number.");
            return check();
        }
        else
            return  Integer.parseInt(temp);
    }



    public static void initialization(Continents[] continent) throws FileNotFoundException {
        for (int i = 0; i < 3; i++)
            continent[i] = new Continents();
        continent[0].name = "Europe";
        continent[1].name = "Asia";
        continent[2].name = "North America";

        File myObj = new File("src\\file1.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            for (int i = 0; i < 3; i++) {
                continent[i].allPlaces = new Location[8];
                for (int j = 0; j < 8; j++) {
                    continent[i].allPlaces[j] = new Location();
                    continent[i].allPlaces[j].name = myReader.nextLine();
                }
            }
        }
        myReader.close();
    }


    public static void readUserData(Hashing hash) throws IOException {
        String line = "";
        String splitBy = ",";
        int i=0;
        Customer c;
        BufferedReader br = new BufferedReader(new FileReader("src\\users.csv"));
        while ((line = br.readLine()) != null)   //returns a Boolean value
        {
            c=new Customer();
            int k;
            String[] details = line.split(splitBy);
            c.id=Integer.parseInt(details[0]);
            c.password=details[1];
            c.client.name=details[2];
            c.client.age=Integer.parseInt(details[3]);
            c.members=Integer.parseInt(details[4]);
            for( k = 5; !details[k].equals("India"); k++){
                c.choices.add(Integer.parseInt(details[k]));
            }
            for (int b=0; b < c.choices.size(); k++,b++) {
                c.bucketList[b]=new Location();
                c.bucketList[b].name=details[k];
            }
            // System.out.println("choice "+c.choices.size());
            for (int j = 0; j < c.choices.size()+1; k++,j++) {
                //System.out.println("==short "+c.shortestRoute[j].name);
                c.shortestRoute[j]=new Location();
                c.shortestRoute[j].name=details[k];
            }
            c.minDuration=Double.parseDouble(details[k]);
            hash.create(c);
        }
    }

    public static void writeUserData(Customer[] c) throws IOException {

        FileWriter csvWriter = new FileWriter("src\\users.csv");
        for (int i = 0; i < c.length; i++) {
            if (c[i] != null) {
                ArrayList<String> data = new ArrayList<>();
                data.add(Integer.toString(c[i].id));
                data.add(c[i].password);
                data.add(c[i].client.name);
                data.add(Integer.toString(c[i].client.age));
                data.add(Integer.toString(c[i].members));
                for (int j = 0; j < c[i].choices.size(); j++) {
                    data.add(Integer.toString(c[i].choices.get(j)));
                }
                for (int j = 0; j < c[i].choices.size(); j++) {
                    data.add(c[i].bucketList[j].name);
                }

                for (int j = 0; j < c[i].choices.size()+1; j++) {
                    data.add(c[i].shortestRoute[j].name);
                }
                data.add(String.valueOf(c[i].minDuration));
                csvWriter.write(String.join(",", data));
                csvWriter.write("\n");
            }
        }
        csvWriter.flush();
        csvWriter.close();
    }


    public static void main(String[] args) throws IOException {
        Continents[] continent = new Continents[3];
        initialization(continent);
        Customer c=new Customer();
        Hashing hash = new Hashing();
        readUserData(hash);
        int count = 0;
        int min = 10;
        int idBase = 100;
        Scanner sc = new Scanner(System.in);
        int opt, opt1 = -1, opt2, opt3 = -1, opt4 = -1,edit=-1;
        boolean flag2 = false;
        boolean flag1;
        int details = 0;
        do {
            flag1 = false;
            System.out.println("\n\t\t\tPlease select the type of user:");

            System.out.println("\t\t________________________________________________________");
            System.out.println("\n\t\t\t\t1.New User\n\t\t\t\t2.Existing User\n\t\t\t\t3.Exit");
            System.out.println("\t\t________________________________________________________");


            opt = check();
            switch (opt) {
                case 1: //new user
                    do {

                        System.out.println("\n\t\t________________________________________________________");
                        System.out.println("\t\t\t\t**  NEW USER  **\n");

                      //  System.out.println("Password is "+c.password);
                        if (c.password.equals("")) {

                            c.id = idBase + min + (int) (Math.random() * (min + 5));
                            //c.id= idBase+count*2 ;//Math.random()   maintain min and max
                            count++;
                            min += 10;
                            System.out.print("\t\tCreate Password: ");
                            c.password = sc.next();
                            System.out.println("\t\tRegistration is successful!");
                            System.out.println("\t\t_____________________________________________________________");
                            System.out.println("\t\t|\tYour customer id is " + c.id + ",  keep it for future reference.\t|");
                            System.out.println("\t\t-------------------------------------------------------------");
                        }

                        System.out.println("\n\t\t\tChoose the type of details you want to enter:");
                        System.out.println("\t\t\t1.Personal Details\t\t\n\t\t\t2.Travel Details\t\n\t\t\t3.Back ");
                        opt1 = check();
                        switch (opt1) {
                            case 1://personal details
                                c.acceptPersonalDetails();
                                flag1 = true;
                                break;
                            case 2:// Travel details
                                if (!flag1) System.out.println("\t\tPlease fill personal details first as you are a new user!");
                                else {

                                    System.out.println("\t\t________________________________________________________");
                                    System.out.println("\t\tWhich continent do you want to visit?\n\t\t1.Europe\n\t\t2.Asia\n\t\t3.North America\n");
                                    int countiChoice=check();
                                    while (true) {
                                        if (countiChoice < 4 && countiChoice > 0) {
                                            break;
                                        }
                                        else{
                                            System.out.println("Enter a valid option!!");
                                            countiChoice = check();
                                        }
                                    }
                                    c.acceptBucket(continent[countiChoice - 1]);
                                    flag2 = true;
                                    hash.create(c);

                                }
                                System.out.println("\n\n\t\t** For modifications,Please visit 'existing user' section **");
                                break;
                            case 3:c=new Customer();
                                break;

                            default:
                                System.out.println("\t\tInvalid input!!");
                                break;
                        }


                    } while (opt1 != 3);
                    break;
                case 2: //existing user
                    //do {
                    boolean exitoption=false;
                    boolean password_validation=false;

                    System.out.println("\n\t\t________________________________________________________");
                    System.out.println("\t\t\t**  EXISTING USER  **");
                    System.out.println();
                    System.out.println("\t\tPlease enter your customer id : ");
                    int custId = check();
                    Customer temp = hash.search(custId);
                    if (temp==null) {
                        System.out.println("\t\tYou are not registered... Please enter new user and register!!");
                        c=new Customer();
                        break;
                    }
                    else c=temp;
                    System.out.print("\t\tEnter password : ");
                    String password = sc.next();
                    //c = hash.search(password);
                    while(!password_validation){
                        if(c.password.equals(password)){
                            System.out.println("\t\tLogin is Successful!!");
                            password_validation=true;
                        }
                        else{
                            System.out.println("\t\tInvalid password !!");
                            System.out.println("\t\t1)Re-enter or 2)Exit");
                            int choice= check();
                            if(choice==1){
                                System.out.print("\t\tPlease re-enter : ");
                                password = sc.next();
                            }
                            else {
                                exitoption=true;
                                break;
                            }
                        }
                    }
                    if(exitoption)break;
                    do {
                        System.out.println("\n\t\tSelect the operation that you would like to perform:");

                        System.out.println("\t\t________________________________________________________");
                        System.out.println("\t\t\t1.View Personal Details\n\t\t\t2.View Travel Details\n\t\t\t3.Edit Details\n\t\t\t4.Back");

                        System.out.println("\t\t________________________________________________________");
                        opt2 = check();
                        //opt2;
                        switch (opt2) {
                            case 1:
                                c.DisplayCustomerDetails();
                                //call t personal details
                                continue;
                            case 2:c.DisplayTravelsDetails();
                                continue;
                            case 3:
                                do {
                                    System.out.println("\n\t\t\t***** EDIT INFORMATION *****");
                                    System.out.println("\n\t\tPlease select among the editing options:");

                                    System.out.println("\t\t________________________________________________________");
                                    System.out.println("\t\t\t\t1.Modify\n\t\t\t\t2.Back\n\t\t\t\t");

                                    System.out.println("\t\t________________________________________________________");
                                    opt3 = check();
                                    switch (opt3) {
                                        case 1://modify personal or travel detail
                                            do {
                                                boolean modification=false;

                                                System.out.println("\t\t________________________________________________________");
                                                System.out.println("\n\t\tChoose The Type Of Details You Want To Modify:\n\t\t\t1.Personal Details");
                                                System.out.println("\t\t\t2.Travel Details\n\t\t\t3.Back");

                                                System.out.println("\t\t________________________________________________________");

                                                opt4 = check();
                                                switch (opt4) {
                                                    case 1://personal
                                                        System.out.println("\n\t\tModify personal details: ");
                                                        System.out.println("\t\t1.Modify name  \n\t\t2.Change number of tickets to be book");
                                                        int choice = check();
                                                        if(choice==1) {
                                                            System.out.print("\t\tEnter your new name.  :");
                                                            c.client.name = sc.next();
                                                        }
                                                        else if(choice==2){
                                                            System.out.println("\t\tEnter no of tickets you want to book : ");
                                                            c.members = check();
                                                        }
                                                        else System.out.println("\t\tInvalid option!!");
                                                        break;
                                                    case 2://modify travel details
                                                        System.out.println("\t\tPlease enter your new travel locations.... ");

                                                        System.out.println("\t\t________________________________________________________");
                                                        System.out.println("\t\tWhich continent do you want to visit?\n\t\t1.Europe\n\t\t2.Asia\n\t\t3.North America\n");

                                                        System.out.println("\t\t________________________________________________________");
                                                        int newContinent = check();
                                                        while (true) {
                                                            //countiChoice = ;
                                                            // System.out.print("Enter: ");
                                                            if (newContinent < 4 && newContinent > 0) {
                                                                break;
                                                            }
                                                            else{
                                                                System.out.println("\t\tEnter a valid option!!");
                                                                newContinent = check();
                                                            }
                                                        }
                                                        c.acceptBucket(continent[newContinent - 1]);
                                                       // hash.create(c);
                                                        System.out.println("\t\tTravel details are modified successfully!!");
                                                        System.out.println("\t\tPlease visit 'view travel details' to view your updated travel details !!");
                                                        modification=true;
                                                        break;

                                                    case 3:
                                                        System.out.println("\t\tGoing back ...");
                                                        break;
                                                    default :
                                                        System.out.println("\t\tInvalid Input!!");
                                                        break;
                                                }
                                                if(modification)break;
                                            } while (opt4 != 3);
                                            break;

                                        case 2:
                                            System.out.println("\t\tGoing back!!");
                                            break;
                                        default:
                                            System.out.println("\t\tInvalid input!!");
                                            break;
                                    }
                                } while (opt3 != 2);
                                break;
                            case 4:
                                System.out.println("\t\tGoing back");
                                break;
                            default:
                                System.out.println("\t\tInvalid input!!");
                                break;
                        }
                        // }while(edit!=4);
                    } while (opt2 != 4);
            }
        } while (opt != 3);

        writeUserData(hash.customer);
    }
}


