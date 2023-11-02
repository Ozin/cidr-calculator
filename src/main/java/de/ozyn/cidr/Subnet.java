package de.ozyn.cidr;

public record Subnet(int size) {
    static int MAX = 0xFF_FF_FF_FF;

    public Subnet {
        if (size < 0) throw new IllegalArgumentException("Subnet must be greater than or equal 0");
        if (size > 32) throw new IllegalArgumentException("Subnet must be smaller than or equal 32");
    }

    public Ip toIp() {
        long negSize = 32 - size;
        return new Ip(MAX >> negSize << negSize);
    }
}
