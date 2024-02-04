package oop;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class vendingMachine {
    class Product {
        public double price;
        public String name;
        public Product(String name, double d) {
            this.name = name;
            this.price = d;
        }
    }

    class ProductInventory {
        public Product product;
        public int count;
        public ProductInventory(Product prod, int count) {
            this.product = prod;
            this.count = count;
        }
    }

    enum InventoryUpdateType {
        INCREAMENT_BY_ONE,
        DECREAMENT_BY_ONE
    }

    class Inventory {
        // key: coord
        public Map<String, ProductInventory> inventory;

        public Inventory() {
            this.inventory = new HashMap<>();
            // populate all coords.
        }

        public void populateInventory(String[] coords, ProductInventory[] inv) {
            for(int i=0; i<coords.length; ++i) {
                inventory.put(coords[i], inv[i]);
            }
        }

        // assume not exceeding the cap
        public void updateInventory(String coord, InventoryUpdateType updateType) {
            ProductInventory p = this.inventory.get(coord);

            if(updateType == InventoryUpdateType.INCREAMENT_BY_ONE) {
                p.count++;
            } else {
                p.count--;
            }

            this.inventory.put(coord, p);

        }

        public boolean hasInventory(String coord) {
            return this.inventory.get(coord) != null && this.inventory.get(coord).count > 0;
        }

        public Product getProduct(String coord) {
            return this.inventory.get(coord).product;
        }
    }

    enum State {
        WAITING_FOR_SELECTION,
        WAITING_FOR_PAY,
        WAITING_FOR_DISPENSE,
        WAITING_FOR_BOOKKEEPING,
        CALL_SUPPORT,
    }

    class Transaction {
        public String productName;
        public double price;
        public Date eventTime;

        public Transaction(String pName, double price) {
            this.productName = pName;
            this.price = price;
            this.eventTime = Date.from(Instant.now());
        }

        public void persist() {
            // todo
        }
    }

    class Vendor {
        State state;
        Inventory inventory;
        String coordInFlight;

        public Vendor(Inventory inv) {
            this.inventory = inv;
            this.state = State.WAITING_FOR_SELECTION;
        }

        public void orchestrate() {
            while(true) {
                switch(state) {
                    case WAITING_FOR_SELECTION:
                        askSelection();
                        break;
                    case WAITING_FOR_PAY:
                        askToPay();
                        break;
                    case WAITING_FOR_DISPENSE:
                        dispense();
                        break;
                    case WAITING_FOR_BOOKKEEPING:
                        bookkeeping();
                        break;
                    case CALL_SUPPORT:
                        callSupport();
                        break;
                }
            }
        }

        void askSelection() {
            this.coordInFlight = null;

            while(true) {
                System.out.print("input your selection?");
                String coord = System.console().readLine();
                if(this.inventory.hasInventory(coord)) {
                    this.state = State.WAITING_FOR_PAY;
                    this.coordInFlight = coord;
                    break;
                }
                System.out.println("out of inventory, please reselect.");
            }
        }

        void askToPay() {
            try {
                while(true) {
                    System.out.print("pay now?(Y/N)");
                    String paid = System.console().readLine();
                    if(paid.compareToIgnoreCase("Y")==0) {
                        this.state = State.WAITING_FOR_DISPENSE;
                        break;
                    }
                }
            } catch(Exception ex) {
                this.state = State.CALL_SUPPORT;
            }
        }

        void dispense() {
            try {
                this.inventory.updateInventory(coordInFlight, InventoryUpdateType.DECREAMENT_BY_ONE);
                this.state = State.WAITING_FOR_BOOKKEEPING;
            } catch(Exception ex) {
                this.state = State.CALL_SUPPORT;
            }
        }

        void bookkeeping() {
            try {
                Product prod = this.inventory.getProduct(coordInFlight);
                Transaction trans = new Transaction(prod.name, prod.price);
                trans.persist();
                this.state = State.WAITING_FOR_SELECTION;
            } catch(Exception ex) {
                this.state = State.CALL_SUPPORT;
            }
        }

        void callSupport() {
            System.out.println("called support.");
        }
    }

    public void test() {
        Inventory inv = new Inventory();
        String[] coords = new String[]{"a1", "b1"};
        ProductInventory[] products = new ProductInventory[]{
            new ProductInventory(new Product("peanut", 2.0), 1),
            new ProductInventory(new Product("candy", 10.0), 2)
        };
        inv.populateInventory(coords, products);
        Vendor vendor = new Vendor(inv);
        vendor.orchestrate();
    }


    public static void main(String args[])  {
        new vendingMachine().test();
    }
}
