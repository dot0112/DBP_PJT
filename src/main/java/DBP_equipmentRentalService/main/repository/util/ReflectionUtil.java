package DBP_equipmentRentalService.main.repository.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ReflectionUtil {
    public static Set<String> getFieldNames(Class<?> clazz) {
        Set<String> fieldNames = new HashSet<>();
        Field[] fields = clazz.getDeclaredFields(); // 해당 클래스의 모든 필드 가져오기
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }
}
