package frc.robot.lib;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public abstract class ShuffleboardTabBase {
    protected ShuffleboardTab m_Tab;

    public abstract void createEntries();

    protected NetworkTableEntry createNumberEntry(String name) {
        return m_Tab.add(name, 0.0).withSize(2, 1).getEntry();
    }

    protected NetworkTableEntry createStringEntry(String name) {
        return m_Tab.add(name, "").withSize(2, 1).getEntry();
    }

    public abstract void update();

    /* Truncates number to 2 decimal places for cleaner numbers */
    protected double truncate(double number) {
        return Math.floor(number * 100) / 100;
    }
    
    public ShuffleboardTab getTab() {
        return m_Tab;
    }
}
