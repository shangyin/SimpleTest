//package JinXuLiang.LearnJava;
//
//
//import nu.xom.*;
//
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.util.*;
//
///**
// * Created by 41237 on 2016/7/16.
// */
//public class Person
//{
//    private String first, last;
//
//    public Person(String first, String last)
//    {
//        this.first = first;
//        this.last = last;
//    }
//
//    public Element getXML()
//    {
//        Element person = new Element("person");
//        Element firstName = new Element("first");
//        firstName.appendChild(first);
//        Element lastName = new Element("last");
//        lastName.appendChild(last);
//        person.appendChild(firstName);
//        person.appendChild(lastName);
//        return person;
//    }
//
//    public Person(Element person)
//    {
//        first = person.getFirstChildElement("first").getValue();
//        last = person.getFirstChildElement("last").getValue();
//    }
//
//    public String toString()
//    {
//        return first + " " + last;
//    }
//
//    public static void format(OutputStream os, Document doc) throws Exception
//    {
//        Serializer serializer = new Serializer(os, "ISO-8859-1");
//        serializer.setIndent(4);
//        serializer.setMaxLength(60);
//        serializer.write(doc);
//        serializer.flush();
//    }
//
//    public static void main(String[] args) throws Exception
//    {
//        List<Person> people = Arrays.asList(
//                new Person("Ted", "Mosby"),
//                new Person("Barney", "Stinson"),
//                new Person("Robin", "Bla"),
//                new Person("Lily", "Blabla")
//        );
//        System.out.println(people);
//        Element root = new Element("people");
//        people.stream()
//                .forEachOrdered(x ->  root.appendChild(x.getXML()));
//        Document doc = new Document(root);
//        format(new FileOutputStream("file.xml"), doc);
//    }
//}
//
//class ReadPeople extends ArrayList<Person>
//{
//    public ReadPeople(String fileName) throws Exception
//    {
//        Document doc = new Builder().build(fileName);
//        Elements elements = doc.getRootElement().getChildElements();
//        for (int i = 0; i < elements.size(); i++)
//        {
//            add(new Person(elements.get(i)));
//        }
//    }
//
//    public static void main(String[] args) throws Exception
//    {
//        ReadPeople people = new ReadPeople("file.xml");
//        System.out.println(people);
//    }
//}
//
//class Connection
//{
//}
//
//class ConnectionManager
//{
//    private static Connection[] array = new Connection[5];
//    private static int count;
//    static
//    {
//        for (count = 0; count < 5; count++)
//        {
//            array[count] = new Connection();
//        }
//    }
//
//    public static Connection getConnection()
//    {
//        if (count > 0)
//        {
//            return array[--count];
//        }
//        else
//        {
//            return null;
//        }
//    }
//
//}