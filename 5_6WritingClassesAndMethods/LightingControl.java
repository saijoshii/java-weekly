public class LightingControl {
    public static void main(String[] args) {
        LightBulb bulb1 = new LightBulb();
        LightBulb bulb2 = new LightBulb();
        LightBulb bulb3 = new LightBulb();

        bulb1.switchOn();
        bulb3.switchOn();
        System.out.println("Bulb 1: " + bulb1);
        System.out.println("Bulb 2: " + bulb2);
        System.out.println("Bulb 3: " + bulb3);
    }
}
