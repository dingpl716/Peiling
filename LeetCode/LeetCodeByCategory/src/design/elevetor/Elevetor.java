package design.elevetor;

import java.util.Comparator;
import java.util.Queue;

public class Elevetor {

	public void run(Car car){
		while(true) {
			
			Action action = car.moveNext();
			if(action == Action.moveNext) {
				continue;
			} else if (action == Action.openDoor){
				car.openDoor();
				car.closeDoor();
			} else {
				
			}
		}
	}
	
}
