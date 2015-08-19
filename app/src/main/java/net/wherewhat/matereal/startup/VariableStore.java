package net.wherewhat.matereal.startup;

/**
 * @author RyuIkHan
 */
public interface VariableStore {

    public int getIntegerValue(String key);
    public long getLongValue(String key);
    public double getDoubleValue(String key);
    public String getStringValue(String key);
    public boolean getBooleanValue(String key);
}
