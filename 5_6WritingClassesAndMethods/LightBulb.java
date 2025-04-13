public class LightBulb {
    private boolean isOn;

    public LightBulb() {
        isOn = false;
    }

    public void switchOn() {
        isOn = true;
    }

    public void switchOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    @Override
    public String toString() {
        return "LightBulb is " + (isOn ? "on" : "off");
    }
}
