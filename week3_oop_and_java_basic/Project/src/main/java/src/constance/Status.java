package src.constance;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Status {
    ACTIVE(0,"Đang hoạt động"),
    SUSPENDED(1, "Bị khóa tạm thời do giao dịch đáng ngờ"),
    BANNED(2, "Bị cấm vĩnh viễn do vi phạm chính sách.");

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
