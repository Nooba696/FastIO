/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastio;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 *
 * @author Pratick Roy
 */
public class FastOutput
{
    private final PrintWriter writer;
    private final boolean autoFlush;
 
    public FastOutput(OutputStream outputStream,boolean autoFlush) 
    {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        this.autoFlush=autoFlush;
    }
    public FastOutput(boolean autoFlush) 
    {
        this(System.out,autoFlush);
    }
    public FastOutput(OutputStream outputStream) 
    {
        this(outputStream,false);
    }
    public FastOutput() 
    {
        this(System.out,false);
    }
        
    public void print(Object... objects) 
    {
        for (int i = 0; i < objects.length; i++)
        {
            if (i != 0) 
            {
                writer.print(' ');
            }
            writer.print(objects[i]);
        }
        if(autoFlush)
            flush();
    }
 
    public void println(Object... objects)
    {
        for (int i = 0; i < objects.length; i++)
        {
            if (i != 0) 
            {
                writer.print(' ');
            }
            writer.print(objects[i]);
        }
        writer.println();
        if(autoFlush)
            flush();
    }
 
    public void close()
    {
        writer.close();
    }
 
    public void flush() 
    {
        writer.flush();
    }
}
