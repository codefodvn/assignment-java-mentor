package src.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Currency {
    VND(0,"Việt Nam đồng"),
    USD(1, "Đô la Mỹ");

    private final Integer value;
    private final String description;

    private static final Map<Integer, Status> map = new HashMap<>();
    static {
        for (Status status : Status.values()) {
            map.put(status.getValue(), status);
        }
    }
    public static Status get(Integer value) {
        return map.getOrDefault(value,null);
    }

    @Override
    public String toString() {
        return name() + " (" + value + "): " + description;
    }
}
