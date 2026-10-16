package com.demo.example.mscustomerdummy.request;

public record CustomerRequest(
    String nombre,
    String apellido,
    String correo,
    String dni
) {
}

