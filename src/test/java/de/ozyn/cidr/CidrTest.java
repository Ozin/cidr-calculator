package de.ozyn.cidr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CidrTest {
    @Test
    void wikipediaExample() {
        Ip ip = new Ip("10.43.8.67");
        Subnet subnet = new Subnet(28);
        Cidr cidr = new Cidr(ip, subnet);

        assertEquals(new Cidr(new Ip("10.43.8.64"), subnet), cidr.networkAddress());
        assertEquals(new Cidr(new Ip("10.43.8.79"), subnet), cidr.broadcast());
    }

    @Test
    void netAddress_equals_ip() {// 0x2F03F26A
        final Subnet subnet = new Subnet(15);
        Cidr cidr = new Cidr(new Ip("47.3.242.106"), subnet);

        assertEquals(new Cidr(new Ip("47.2.0.0"), subnet), cidr.networkAddress());

        new Cidr(new Ip(0xABCD), new Subnet(0));
    }
}