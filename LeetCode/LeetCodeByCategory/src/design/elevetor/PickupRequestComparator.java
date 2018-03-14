package design.elevetor;

import java.util.Comparator;

public class PickupRequestComparator implements Comparator<PickupRequest> {

	private Car car;
	private int numFloors;
	public PickupRequestComparator(Car car) {
		this.car = car;
		numFloors = car.getHighestFloor() - car.getLowestFlorr();
	}
	
	@Override
	public int compare(PickupRequest o1, PickupRequest o2) {
		
		int steps1 = getSteps(o1);
		int steps2 = getSteps(o2);
		
		return steps1 - steps2;
	}
	
	/**
	 * 可以通过计算change direction的次数来进行判断，然后给每个request计算出一个值来:
	 * 这个值的计算公式是: 方向修改此处  * 10 + 楼层差的绝对值
	 * 以现在car在向上运行为例
	 * 
	 * 如果o1是向上,且o1的floor高于 car的floor 那么我们不需要进行方向修改，此时方向修改数为0
	 * 那么对o1的值就是 0 * 10 + o1.floor - car.floor
	 * 
	 * 如果o1是向上，且o1的floor低于car的floor 那么我们需要进行两次方向修改
	 * 
	 */
	private int getSteps(PickupRequest req) {
		switch (car.getStatus()) {
			case MOVINGUP:
				if (req.direction == Direction.UP) {
					if (req.floor >= car.getCurrentFloor()) {
						return req.floor - car.getCurrentFloor();
					} else {
						return numFloors * 2 + req.floor - car.getLowestFlorr();
					}
				} else {
					return numFloors + car.getHighestFloor() - req.floor;
				}
				
			case MOVINGDOWN:
				if (req.direction == Direction.DOWN) {
					if (req.floor <= car.getCurrentFloor()) {
						return car.getCurrentFloor() - req.floor;
					} else {
						return numFloors * 2 + car.getHighestFloor() - req.floor;
					}
				} else {
					return numFloors + req.floor - car.getLowestFlorr();
				}
				
			default: //IDEL
				return Math.abs(car.getCurrentFloor() - req.floor);
		}
	}
}
