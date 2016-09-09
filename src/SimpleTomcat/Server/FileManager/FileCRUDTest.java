package SimpleTomcat.Server.FileManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by 41237 on 2016/8/5.
 */
@RunWith(Theories.class)
public class FileCRUDTest
{
    FileCRUD crud;
    @Before
    public void setup()
    {
        FileManager.add(FileManager.conUrl, FileManager.homeDir);
        crud = FileManager.getCrud();
    }

    @DataPoints
    public static String[] names() {
        return  new String[]{ "corejava9.zip", "Hello.class", "XIAOSHOU.txt","Hello.java"};
    }


    @Theory
    public void exist(String name) throws Exception
    {
        assertThat(crud.exist(name), is(true));
    }

    @Theory
    public void find3(String name) throws Exception
    {
        assertThat(crud.find(name), containsString(name));
    }

    @Test
    public void find2() throws Exception
    {
        crud.find();
    }

    @Test
    public void find() throws Exception
    {
        List<String> list = crud.find(crud.size());
        list.forEach(System.out::println);
    }

}