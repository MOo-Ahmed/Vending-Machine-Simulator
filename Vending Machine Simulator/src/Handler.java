import java.util.ArrayList;
import java.util.Scanner;

public class Handler {
	Machine mach = null;
	
	Handler(){
		ArrayList<Item> items = new ArrayList();
		items.add(new Item("Snickers", 6));
		items.add(new Item("Peanut M&M’s", 5));
		items.add(new Item("Pepsi", 5));
		items.add(new Item("Mountain Dew", 5));
		items.add(new Item("Water", 3));
		items.add(new Item("Gumballs", 4));
		items.add(new Item("Doritos", 8));
		items.add(new Item("Grandma’s Cookies", 11));
		items.add(new Item("Strawberry Pop Tarts", 22));
		items.add(new Item("Diet Coke", 6));
		mach = new Machine(200, items);
	}
	
	void start(){
		double money = 0 ;
		while(true){
			if(mach.isMachineOutOfStock()) {
				System.out.println("Sorry! The machine is out of stock");
				if(money > 0) {
					System.out.println("Please get your money back, make sure you get " + money + " $");
					money = 0 ;
				}
				System.out.println("Thank you !");
				break ;
			}
			Scanner sc = new Scanner(System.in);
			if(money <= 0) {
				System.out.println("Insert enough money please ..");
				money = Double.parseDouble(sc.nextLine());
			}
			
			mach.displayMyItems();
			System.out.println("\nChoose the number of the item : ");
			int itemNo = Integer.parseInt(sc.nextLine());
			if(itemNo > 10 || itemNo < 1) {
				continue;
			}
			else {
				System.out.println("\nEnter number of units : ");
				int units = Integer.parseInt(sc.nextLine());
				if(mach.isUnitsEnough(itemNo, units) == false) {
					System.out.println("Sorry! No available units .. ");
					continue ;
				}
				double price = mach.getPriceOfItem(itemNo)*units;
				boolean tryElse = false;
				if(price > money) {
					System.out.println("Wrong request .. You have inserted less enough money");
					tryElse = true ;
				}
				if(tryElse) {
					System.out.println("Press 1 to try something else or 0 to get back your money");
					int choice = Integer.parseInt(sc.nextLine());
					if(choice == 1) {
						continue;
					}
					else {
						if(money > 0)	System.out.println("Here is your money back, make sure you get " + money + " $");
						money = 0 ;
						continue;
					}
				}
				else {
					mach.addMoneyOfBuyer(price);
					money -= price ;
					mach.removeUnit(itemNo, units);
					System.out.println("Please pick up the item(s)");
					if(money == 0) {
						continue;
					}
					System.out.println("Press 1 to try something else or 0 to get back your money");
					int choice = Integer.parseInt(sc.nextLine());
					if(choice == 1) {
						continue;
					}
					else {
						if(money > 0)	System.out.println("Here is your money back, make sure you get " + money + " $");
						money = 0 ;
						continue;
					}
				}
				
			}
		}
	}
}
