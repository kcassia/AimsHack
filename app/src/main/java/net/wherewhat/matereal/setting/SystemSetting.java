package net.wherewhat.matereal.setting;

/**
 * Created by RyuIkHan on 15. 7. 10..
 */
public class SystemSetting {

    private String key = null;
    private String value = null;

    private String category = null;

    private String type = null;

    private String description = null;

    public SystemSetting(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
