package de.ozyn.cidr;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Ip {
    public final int ip;
    //public Ip {
    // if (ip < 0) throw new IllegalArgumentException("IP must be greater than or equal 0.0.0.0");
    // if (ip > 0xFF_FF_FF_FF) throw new IllegalArgumentException("IP must be smaller than or equal 255.255.255.255");
    //}

    Ip(int ip) {
        this.ip = ip;
    }

    Ip(String subnet) {
        this(parse((subnet)));
    }

    private static int parse(String subnet) {
        if (!subnet.matches("([0-2]?\\d?\\d\\.){3}([0-2]?\\d?\\d)"))
            throw new IllegalArgumentException("No valid IPv4 format.");

        long[] parts = Arrays.stream(subnet.split("\\."))
                             .mapToLong(Long::parseLong)
                             .toArray();

        int ip = 0;
        for (int i = 0; i < parts.length; i++) {
            ip |= parts[i] << (24 - (i * 8));
        }
        return ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Ip ip1 = (Ip) o;

        return ip == ip1.ip;
    }

    @Override
    public int hashCode() {
        return ip;
    }

    @Override
    public String toString() {
        int[] i = new int[]{
                ip & 0xFF_00_00_00,
                ip & 0xFF_00_00,
                ip & 0xFF_00,
                ip & 0xFF
        };
        return "Ip{" +
               "ip=" + Arrays.stream(i).mapToObj(Integer::toString).collect(Collectors.joining(".")) +
               '}';
    }
}
