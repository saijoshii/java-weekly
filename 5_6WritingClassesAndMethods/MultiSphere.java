public class MultiSphere {
    public static void main(String[] args) {
        Sphere sphere1 = new Sphere(5.0);
        Sphere sphere2 = new Sphere(10.0);

        System.out.println(sphere1);
        System.out.println("Volume: " + sphere1.getVolume());
        System.out.println("Surface Area: " + sphere1.getSurfaceArea());

        sphere1.setDiameter(8.0);
        System.out.println("Updated " + sphere1);
        System.out.println("New Volume: " + sphere1.getVolume());

        sphere2.setDiameter(15.0);
        System.out.println(sphere2);
        System.out.println("Surface Area: " + sphere2.getSurfaceArea());
    }
}