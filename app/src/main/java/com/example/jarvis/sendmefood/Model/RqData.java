package com.example.jarvis.sendmefood.Model;

import java.util.List;

public class RqData {
    private String numbers;
    private String address;
    private String sum;
    private List<Orders> orders;
    private String name;

    public RqData() {
    }

    public RqData(String numbers, String address, String sum, List<Orders> orders, String name) {
        this.numbers = numbers;
        this.address = address;
        this.sum = sum;
        this.orders = orders;
        this.name = name;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
