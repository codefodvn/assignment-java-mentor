package src.constance;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
@AllArgsConstructor
public enum TransactionStatus {
    PENDING(0,"Đang xử lí"),
    SUCCESS(1,"Thành công"),
    FAILED(2, "Thất bại"),
    REFUNDED(3,"Hoàn trả");

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
