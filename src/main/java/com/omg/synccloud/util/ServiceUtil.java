package com.omg.synccloud.util;

import com.omg.synccloud.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class ServiceUtil {
    public static <T extends BaseEntity> void insertBatch(List<T> source, MapperUtil mapper) {
        if (!source.isEmpty()) {
            List<T> subSource = new ArrayList<>(JobUtil.INSERT_BATCH);
            for (var i = 0; i < source.size(); i++) {
                if (i != 0 && i % JobUtil.INSERT_BATCH == 0) {
                    mapper.insertBatch(subSource);
                    subSource = new ArrayList<>(JobUtil.INSERT_BATCH);
                }
                subSource.add(source.get(i));
            }

            if (!subSource.isEmpty()) {
                mapper.insertBatch(subSource);
            }
        }
    }
}
