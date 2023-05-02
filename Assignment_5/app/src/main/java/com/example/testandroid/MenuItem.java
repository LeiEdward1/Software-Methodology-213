package com.example.testandroid;
import java.io.Serializable;
import java.util.ArrayList;
public abstract class MenuItem implements Serializable
{
    public abstract String toString();
    public abstract double itemPrice();
}
