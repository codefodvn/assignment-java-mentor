package com.huynq.multipaysystem.model;


import com.huynq.multipaysystem.constance.PaymentType;

import java.math.BigDecimal;

public abstract class PaymentMethod {
    protected String id;
    protected String name;
    protected PaymentType type;

    public PaymentMethod(String id, String name, PaymentType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }


}
