package Collection;

public class Hashing {

    protected int count;
    protected  Customer[] customer = new Customer[20];

    int hash(long id)           //calculate the hash address for given id
    {
        return (int) (id%20);
    }
//changes


void create(Customer c){
    int i=hash(c.id);                            //find index to store the customer
    int temp = i;
    if (customer[i] == null) {
        customer[i] = new Customer();//assign vacant location to customer
        customer[i] = c ;
        count++;
        return;
    } else {
        i = (i + 1) % 40;
    }
    while (customer[i] != null && i != temp) {
        i = (i + 1) % 40;                                  //if index is occupied, move forward
    }

    customer[i] = new Customer();//assign vacant location to customer
    customer[i] = c ;       //assign vacant location to customer
    count++;                                        //increase count
}





    Customer search(int key) {
        boolean flag = false;
        //System.out.println("Enter  the customer ID to be searched.");
        //long search_ID = sc.nextLong();
        int i = hash(key);
        //if {(hashtable[hashval] == null) hashval = (hashval + 1) % 20;

        if (customer[i] != null) {
            if (customer[i].id == key) {
                //customer[i].id .display();
                return customer[i];
            }
        }
        //(hashval + 1) % 20;

        int temp = i;
        i = (i + 1) % 20;
        while (temp != i) {
            if (customer[i] == null) i = (i + 1) % 20;
            else if (customer[i].id != key) i = (i + 1) % 20;
            else if (customer[i].id == key) {
                //customer[i].display();
                flag = true;
                break;
            }
        }
        if (!flag) return null;
        return customer[i];
    }




}
