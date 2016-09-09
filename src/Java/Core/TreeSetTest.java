package Java.Core;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.DataInput;
import java.util.Comparator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.BooleanSupplier;

/**
 * Created by 41237 on 2016/4/3.
 */
public class TreeSetTest {
    public static void main(String[] args)
    {
        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4562));
        parts.add(new Item("Moden", 9912));

        SortedSet<Item> sortByDescription = new TreeSet<>(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                String descrA = o1.getDescription();
                String descrB = o2.getDescription();
                return descrA.compareTo(descrB);
            }
        });
        sortByDescription.addAll(parts);
        System.out.println(parts);
        System.out.println(sortByDescription);
    }
}

class Item implements Comparable<Item>
{
    private String description;
    private int partNumber;

    public Item(String description, int partNumber)
    {
        this.description = description;
        this.partNumber = partNumber;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString()
    {
        return "[description=" +  description + ", partNumber=" + partNumber + "]";
    }

    public boolean equals(Object otherObject)
    {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;
        Item other = (Item) otherObject;
        return Objects.equals(description, other.description) && partNumber == other.partNumber;
    }

    public int hashCode()
    {
        return Objects.hash(description, partNumber);
    }

    public int compareTo(Item other)
    {
        return Integer.compare(partNumber, other.partNumber);
    }

}
