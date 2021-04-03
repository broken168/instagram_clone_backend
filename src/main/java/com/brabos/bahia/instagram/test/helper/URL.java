package com.brabos.bahia.instagram.test.helper;

import java.util.ArrayList;
import java.util.List;

public class URL {

    public static List<Long> decodeLongList(String ids){
        String[] vet = ids.split(",");
        List<Long> list = new ArrayList<>();
        for (String s : vet){
            list.add(Long.parseLong(s));
        }
        return list;
    }
}
