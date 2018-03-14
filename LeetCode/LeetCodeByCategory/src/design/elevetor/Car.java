package design.elevetor;

import java.util.PriorityQueue;
import java.util.Queue;

public class Car {
	private final int highestFloor;
	private final int lowestFloor;
	
	private CarStatus currentStatus;
	private int currentFloor;
	// Is door open.
    private boolean isOpen; 

	private Queue<PickupRequest> pickupQueue;
	private Queue<DropoffRequest> dropoffQueue;
	
	public Car(int highestFloor, int lowestFloor, int currentFloor) {
		currentStatus = CarStatus.IDEL;
		this.currentFloor = currentFloor; 
		this.highestFloor = highestFloor;
		this.lowestFloor = lowestFloor;
		pickupQueue = new PriorityQueue<PickupRequest>(new PickupRequestComparator(this));
		dropoffQueue = new PriorityQueue<DropoffRequest>(new DropoffRequestComparator(this));
	}
	
	public CarStatus getStatus() {
		return currentStatus;
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}
	
	public int getHighestFloor() {
		return highestFloor;
	}
	
	public int getLowestFlorr() {
		return lowestFloor;
	}
	

	/**
	 * Add a pickup request to the car if the pick up floor is within highest and lowest floor,
	 * otherwise return -1.
	 * @param request
	 * @return
	 */
	int addPickupRequest(PickupRequest request){
		if (request.floor > highestFloor || request.floor < lowestFloor) {
			return -1;
		}
		
		pickupQueue.add(request);
		return 1;
	}
	
	// if the car is going up, it will not accept requests to go down
	// if the car is going down, it will not accept request to go up
	// if the car is idle, it will accept any request
	boolean addDropoffRequest(DropoffRequest request) {
		switch (currentStatus) {
			case MOVINGUP:
				if (currentFloor < request.floor) {
					dropoffQueue.add(request);
				} else {
					return false;
				}
				
			case MOVINGDOWN:
				if (currentFloor > request.floor) {
					dropoffQueue.add(request);
				} else {
					return false;
				}
				
			case IDEL:
				dropoffQueue.add(request);
				return true;
		}
		
		return false;
	}
	
	public void moveNext() {
		if (pickupQueue.isEmpty() && dropoffQueue.isEmpty()) {
			currentStatus = CarStatus.IDEL;
			isOpen = true;
		}
		
		PickupRequest pickup = pickupQueue.peek();
		DropoffRequest dropoff = dropoffQueue.peek();
		
		// 如果现在这一层有人要下，那么我们一定要让他下电梯
		if (dropoff != null && dropoff.floor == currentFloor) {
			dropoffQueue.poll();
			if (dropoffQueue.isEmpty()) {
				// 这个时候需要根据第一个pickupRequest的情况来调整移动方向
				// 可能是继续朝一个方向，可能改变方向
			}
			return Action.openDoor;
		}
		
		// 如果现在这一层有人想上，并且方向和电梯方向一致，那么我们要让他上
		else if (pickup != null && pickup.floor == currentFloor && pickup.direction == currentDirection) {
			pickupQueue.poll();
			return Action.openDoor;
		}
		// 如果现在没有人要下，也没有刚好可以上的人，那么朝这个方向移动一层,并且根据情况更改方向
		else {
			
			return Action.moveNext;
		}
	}
	
}
