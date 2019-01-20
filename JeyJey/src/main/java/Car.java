public class Car {

    private String brand = null;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    private int doors = 0;

    Car() {
        System.out.println("Hi");
    }

    public Car(String brand, int doors) {
        System.out.println("Hello");
        this.brand = brand;
        this.doors = doors;
    }



}
