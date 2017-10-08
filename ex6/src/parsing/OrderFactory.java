package parsing;
import java.io.File;
import java.util.ArrayList;

import orders.Order;
import orders.OrderAbs;
import orders.OrderReverse;
import orders.OrderSize;
import orders.OrderType;

import manager.TypeOneException;



/**
 * 
 * @author Liat Ginosar
 *
 */
public class OrderFactory {
	
	/**
	 * 
	 * @param directory
	 * @param orderString
	 * @param numOfLine
	 * @param warnings
	 * @return
	 */
	public static Order createOrder(File directory, String orderString , int numOfLine, ArrayList<String> warnings) {
		
		try {
			if(orderString.equals("abs")) {
				return (Order) new OrderAbs();
			}

			else if(orderString.equals("type")) {
				return (Order) new OrderType();
			}

			else if(orderString.equals("size")) {
				return (Order) new OrderSize();
			}
			else {
				if(orderString.contains("#") && orderString.substring(orderString.indexOf("#")).equals("REVERSE")) {
					if(orderString.split("#")[0].equals("abs")) {
						return (Order) new OrderReverse(new OrderAbs());
					}
					else if(orderString.split("#")[0].equals("type")) {
						return (Order) new OrderReverse(new OrderType());
					}
					else if(orderString.split("#")[0].equals("size")) {
						return (Order) new OrderReverse(new OrderSize());
					}
				}	
			}
			throw new WrongOrderNameException();
		}
		
		catch(TypeOneException ex) {
			warnings.add("Warning in line " + numOfLine);
		}
		return new OrderSize();//Default order
	}
}
