import java.util.ArrayList;

public class Machine {
	double AmountOfMoney ;
	ArrayList<Item> Items = new ArrayList<Item>() ;
	
	public Machine() {}
	
	public Machine(double defaultAmountOfMoney, ArrayList<Item> items) {
		this.AmountOfMoney = defaultAmountOfMoney ;
		Items.addAll(items);
	}
	
	public void setItems(ArrayList<Item> Items) {
		this.Items = Items ;
	}
	
	public double returnBuyerMoneyAgain(double money) {
		if(AmountOfMoney >= money) {
			AmountOfMoney -= money ;
			return money ;
		}
		else{
			System.out.println("Invalid request , user asked larger amount money");
			return 0 ;
		}
	}
	
	public double getPriceOfItem(int id) {
		return Items.get(id-1).price; 
	}	
	
	public void addMoneyOfBuyer(double money) {
		AmountOfMoney += money ;
	}
	
	public void removeUnit(int id, int units) {
		Items.get(id-1).NumberOfAvailableUnits -= units ;
	}
	
	public void addItem(Item item) {
		Items.add(item);
	}
	
	public boolean isMachineOutOfStock() {
		for(int i = 0 ; i < Items.size() ; i++) {
			if(!isItemOutOfStock(i+1))	return false ;
		}
		return true ;
	}
	
	public boolean isItemOutOfStock(int itemID) {
		return Items.get(itemID-1).NumberOfAvailableUnits == 0 ;
	}
	
	public void displayMyItems() {
		ArrayList<Item> TmpItems = new ArrayList();
		for(int i = 0 ; i < Items.size();i++) {
			if(!isItemOutOfStock(i+1)) {
				TmpItems.add(Items.get(i));
			}
		}
		Items.clear();
		Items.addAll(TmpItems);
		System.out.println("Here's our available items ");
		for(int i = 0 ; i < TmpItems.size() ; i++) {
			System.out.print("\tItem " + (i+1) + " : " + TmpItems.get(i).type + " -> " + TmpItems.get(i).price);
			System.out.println("");
		}
		System.out.println("\n");
	}

	public boolean isUnitsEnough(int id, int number) {
		if(Items.get(id-1).NumberOfAvailableUnits >= number)	return true;
		else	return false;
	}
}
