package com.playground.user;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

public class JavaTest {

    @Test
    void test() {
        List<Object> emptyList = Collections.emptyList();
        List list = Collections.EMPTY_LIST;
        System.out.println(CollectionUtils.isEmpty(emptyList));
        System.out.println(CollectionUtils.isEmpty(list));
        System.out.println(ObjectUtils.isEmpty(emptyList));
        System.out.println(ObjectUtils.isEmpty(list));
    }

}
