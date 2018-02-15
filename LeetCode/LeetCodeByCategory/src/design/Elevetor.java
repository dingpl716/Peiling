package design;

import java.util.Comparator;
import java.util.Queue;

public class Elevetor {

	private enum Action {
		moveNext, openDoor, closeDoor, wait,
	}
	private static class Car {
		public enum Status {
			movingUp, movingDown, idle, wait
		}
		
		public Status currentStatus;
		public Status oldStatus;
		
		int currentFloor;
		Queue<PickupRequest> pickupQueue;
		
		// car 先确保把这个queue里面的人都drop off掉
		Queue<DropoffRequest> dropoffQueue;
		
		void addInput(PickupRequest request){
			
		}
		
		void addInput(DropoffRequest request) {
			// if the car is going up, it will not accept requests to go down
			
			// if the car is going down, it will not accept request to go up
			
			// if the car is idle, it will accept any request and set the direction
			if (currentStatus == Status.idle) {
				if (request.floor == currentFloor) {
					return;
				}
				
				dropoffQueue.add(request);
				if (request.floor < currentFloor) {
					currentStatus = Status.movingDown;
				} else {
					currentStatus = Status.movingUp;
				}
			}
		}
		
		Action moveNext() {
			if (pickupQueue.isEmpty() && dropoffQueue.isEmpty()) {
				currentStatus = Status.idle;
				oldStatus = Status.idle;
				return Action.wait;
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
		
		void openDoor() {
			oldStatus = currentStatus;
			currentStatus = Status.wait;
		}
		
		void closeDoor() {
			currentStatus = oldStatus;
			moveNext();
		}
		
	}
	
	private static class DropoffRequestComparator implements Comparator<DropoffRequest> {

		private Car car;
		private DropoffRequestComparator(Car car) {
			this.car = car;
		}
		
		@Override
		public int compare(DropoffRequest arg0, DropoffRequest arg1) {
			switch (car.currentStatus) {
				case movingUp : 
					return arg0.floor - arg1.floor;
				case movingDown:
					return arg1.floor - arg0.floor;
				case idle:
					break;
				default:
					break;
			}
			return 0;
		}
		
	}
	
	private static class PickupRequestComparator implements Comparator<PickupRequest> {

		private Car car;
		private PickupRequestComparator(Car car) {
			this.car = car;
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
		 * 
		 * 
		 * 如果o1是向下,且o1，
		 * 
		 * 
		 */
		@Override
		public int compare(PickupRequest o1, PickupRequest o2) {
			switch (car.currentStatus) {
				case movingUp : 
					if (o1.direction == PickupRequest.Direction.up 
					&& o2.direction == PickupRequest.Direction.up) {
						if (o1.floor >= car.currentFloor && o2.floor >= car.currentFloor) {
							return o1.floor - o2.floor;
						} else if (o1.floor < car.currentFloor && o2.floor < car.currentFloor) {
							return o1.floor - o2.floor;
						} else if (o1.floor < car.currentFloor) {
							return 1;
						} else {
							return -1;
						}
					} else if (o1.direction == PickupRequest.Direction.down 
					&& o2.direction == PickupRequest.Direction.down) {
						return o2.floor - o1.floor;
					} else if (o1.direction == PickupRequest.Direction.down)
				case movingDown:
					
				case idle:
					break;
				default:
					break;
			}
			return 0;
		}
	}
	
	// 表示电梯外面的人的输入
	private static class PickupRequest {
		
		public enum Direction {
			up, down
		}
		
		// 表示外面的人到底是想上还是想下
		public Direction direction;
		
		public int floor;
	}
	
	// 表示电梯里面的人的输入
	private static class DropoffRequest {
		
		int floor;
	}
	
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
