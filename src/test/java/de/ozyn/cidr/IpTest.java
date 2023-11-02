package de.ozyn.cidr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IpTest {

    @Test
    public void zeros() {
        final Ip ip = new Ip("0.0.0.0");
        assertEquals(0, ip.ip);
    }

    @Test
    public void ffffffff() {
        final Ip ip = new Ip("255.255.255.255");
        assertEquals(0xFF_FF_FF_FF, ip.ip);
    }

    @Test
    public void fff000ff() {
        final Ip ip = new Ip("255.240.0.255");
        assertEquals(0xFF_F0_00_FF, ip.ip);
    }

    @Test
    public void invalid() {
        assertThrows(IllegalArgumentException.class, () -> new Ip("255.255.300.255"));
        assertThrows(IllegalArgumentException.class, () -> new Ip("255.255.2551.255"));
        assertThrows(IllegalArgumentException.class, () -> new Ip("255.255.255,255"));
        assertThrows(IllegalArgumentException.class, () -> new Ip("255..25a.255"));
    }
}