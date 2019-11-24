package in.hocg.payment.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
class MapUtilsTest {
    
    @Test
    void sorted() {
        Map<String, Object> hashMap = Maps.newLinkedHashMap();
        hashMap.put("a", 1);
        hashMap.put("c", 3);
        hashMap.put("b", 2);
        
        log.debug("hashMap :: {}", hashMap);
    
        Set<String> ascSet = new HashSet<>(Lists.newArrayList("a", "b", "c"));
        hashMap = MapUtils.sortedUseKey(hashMap, true);
        log.debug("ASC 排序后 hashMap :: {}", hashMap);
        Assertions.assertEquals(hashMap.keySet(), ascSet);
    
        Set<String> descSet = ascSet.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(LinkedHashSet::new));
        hashMap = MapUtils.sortedUseKey(hashMap, false);
        log.debug("DESC 排序后 hashMap :: {}", hashMap);
        Assertions.assertEquals(hashMap.keySet(), descSet);
    }
}