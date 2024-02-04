package oop;
/*
 * Problem:
Below is a section of a city with roads and stoplights. The letters indicate entry points where we measure traffic flow.
Each intersection has a traffic light - We want to build a program to simulate traffic flowing through the city so we can
test traffic light control algorithms with sample data.

For purposes of this exercise, your simulation should operate on 1 minute intervals,
where every minute lights (potentially) change and cars drive along roads, with each section of road taking 1 minute to drive.

         J
         |      
     A---🚦 ---     
		 |
     B---🚦 ---
		 |
		 
	```	 
Example of traffic flow::
 - A car enters road J at minute 2
 - It takes 1 minute to drive to light A-J
 - Once light A-J is green for road J, it takes 1 minute to drive to light B-J
 - Once light B-J is green for road J, it takes 1 minute to drive to the exit of the city
 - Total travel time would be 3 minutes + number of minutes spent waiting at lights
 - Assuming the car hit only green lights, it would exit the city at minute 5

PART 1
 Let's start by simulating one road - Road J. 
 - First, create a data model to represent the road, lights, and cars driving along it.
 - Next, enter one car into the road and write a function that simulates time in 1 minute intervals, 
  moving the car along the road and stopping it at red lights until they turn green. It should run until the car has exited the city.

 For right now we can use a simple traffic light control: Start with both lights green on the first minute, 
 and then toggle back and forth between red and green each minute after that.

 Your function should return the total time it took the car to travel through the city.
 */

public class trafficLight {
 // https://leetcode.com/discuss/interview-question/1143290/build-a-program-to-simulate-traffic-flowing-through-the-city
 
    enum State {
        WAITING_FOR_MOVING,
        WAITING_FOR_REDLIGHT,
        EXITED
    }

    class Orchestrator {
        String[] points;
        int whereAmI;
        int timer;
        State state;

        public Orchestrator() {
            this.points = new String[]{"J", "AJ", "BJ", "K"};
            this.timer = 0;
            this.state = State.WAITING_FOR_MOVING;
            this.whereAmI = 0;
        }

        public void orch() {
            while(true) {
                switch(state) {
                    case WAITING_FOR_MOVING:
                        move();
                        break;
                    case WAITING_FOR_REDLIGHT:
                        waitForRedLight();
                        break;
                    default:
                        System.out.println(String.format("exit at timer:%d", this.timer));
                        return;
                }
            }
        }

        boolean isRedLight() {
            return this.timer%2 == 0;
        }

        void move() {
            System.out.println(String.format("move, timer:%d, position: %d", this.timer, this.whereAmI));

            this.whereAmI++;
            if(this.whereAmI >= this.points.length-1) {
                this.state = State.EXITED;
                return;
            }

            this.timer++;
            if(isRedLight()) {
                this.state = State.WAITING_FOR_REDLIGHT;
            }
        }

        void waitForRedLight() {
            System.out.println(String.format("wait for red light, timer:%d", this.timer));
            this.timer++;
            if(!isRedLight()) {
                this.state = State.WAITING_FOR_MOVING;
            }
        }
    }

        public void test() {
            Orchestrator or = new Orchestrator();
            or.orch();
        }

        public static void main(String args[])  {
            new trafficLight().test();
        }
}
