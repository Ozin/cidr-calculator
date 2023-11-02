package de.ozyn.cidr;

public record Cidr(Ip ip, Subnet subnet) {
    public Cidr broadcast() {
        return new Cidr(new Ip(ip.ip | (~subnet.toIp().ip & Subnet.MAX)), subnet);
    }

    public Cidr networkAddress() {
        return new Cidr(new Ip(ip.ip & subnet.toIp().ip), subnet);
    }
}
