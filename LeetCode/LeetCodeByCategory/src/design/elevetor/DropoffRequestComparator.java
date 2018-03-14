package design.elevetor;

import java.util.Comparator;

public class DropoffRequestComparator implements Comparator<DropoffRequest> {
	
	private Car car;
	
	public DropoffRequestComparator(Car car) {
		this.car = car;
	}

	/**
	 * 
	 */
	@Override
	public int compare(DropoffRequest arg0, DropoffRequest arg1) {
		if (car.getStatus() == CarStatus.MOVINGUP) {
			return arg0.floor - arg1.floor;
		}
		
		if (car.getStatus() == CarStatus.MOVINGDOWN) {
			return arg1.floor - arg0.floor;
		}
		
		return 0;
	}
}
