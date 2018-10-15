package ru.job4j.generic;

public class Role extends Base {
    private String roleName;
    protected Role(String id, String roleName) {
        super(id);
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
