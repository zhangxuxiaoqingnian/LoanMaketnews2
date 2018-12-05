package com.smileflowpig.money.moneyplatfrom.Bean;

public class CheckGuidJson {

    private String name;
    private boolean isSelect;

    public String getName() {
        return name;
    }

    public CheckGuidJson(String name, boolean isSelect) {
        this.name = name;
        this.isSelect = isSelect;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
