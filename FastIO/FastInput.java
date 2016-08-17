package fastio;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * @author Pratick Roy
 * This Class contains methods to achieve fast Input using java InputStream 
 * methods
 */
public class FastInput 
{
    private final InputStream in;
    private final char[] brkChar;
    private byte[] buf; 
    private int curBufCharPos;
    private int bufSize;
    
    public FastInput(InputStream in,char[] brkChar)
    {
        this.in=in;
        buf = new byte[1024];
        curBufCharPos =0;
        bufSize  =0;
        this.brkChar=brkChar;
    }
    public FastInput(InputStream in)
    {
        this(in,null);
    }
    public FastInput()
    {
        this(System.in,null);
    }
    
    public int read()
    {
        if(bufSize==-1)
        {
            throw new InputMismatchException();
        }
        if(curBufCharPos>=bufSize)
        {
            curBufCharPos=0;
            try 
            {
                bufSize=in.read(buf);
            } 
            catch (IOException x) 
            {
                throw new InputMismatchException();
            }
            if(bufSize<=0)
            {
                return -1;
            }
        }
        return buf[curBufCharPos++];
    }
    public int readInt()
    {
        int c = read();
        while (isBreakChar(c))
            c = read();
        
        int sign=1;
        if(c=='-')
        {
            sign=-1;
            c=read();
        }
        int res=0;
        do
        {
            if(c<'0' || c>'9')
            {
                throw new InputMismatchException();
            }
            res=res*10+(c-'0');
            c=read();
        }
        while(!isBreakChar(c));
        return sign*res;
    }
    public long readLong()
    {
        int c = read();
        while (isBreakChar(c))
            c = read();
        
        int sign=1;
        if(c=='-')
        {
            sign=-1;
            c=read();
        }
        long res=0;
        do
        {
            if(c<'0' || c>'9')
            {
                throw new InputMismatchException();
            }
            res=res*10+(c-'0');
            c=read();
        }
        while(!isBreakChar(c));
        return sign*res;
    }
    public float readFloat()
    {
        int c=read();
        while(isBreakChar(c))
            c=read();
        int sign=1;
        if(c=='-')
        {
            sign=-1;
            c=read();
        }
        float res=0;
        while(!isBreakChar(c) && c!='.')
        {
            if(c=='e' || c=='E')
                return (float) (res*Math.pow(10, readInt()));
            if(c<'0' || c>'9')
                throw new InputMismatchException();
            
            res = res*10 + (c-'0');
            c=read();
            //System.out.println("RES 1 : "+res);
        }
        if(c=='.')
        {
            c=read();
            float fac=10;
            do
            {
                if(c=='e' || c=='E')
                    return (float) (res*Math.pow(10, readInt()));
                if(c<'0' || c>'9')
                    throw new InputMismatchException();
                
                //System.out.println("c   2 : "+ res +" "+ (c-'0')/fac + " "+ (res+(c-'0')/fac));
                res+= (c-'0')/fac;
                fac*=10;
                c=read();
                //System.out.println("RES 2 : "+res);
            }
            while(!isBreakChar(c));
        }
        return res*sign;
    }
    public double readDouble()
    {
        int c=read();
        while(isBreakChar(c))
            c=read();
        int sign=1;
        if(c=='-')
        {
            sign=-1;
            c=read();
        }
        double res=0;
        while(!isBreakChar(c) && c!='.')
        {
            if(c=='e' || c=='E')
                return res*Math.pow(10, readInt());
            if(c<'0' || c>'9')
                throw new InputMismatchException();
            
            res = res*10 + (c-'0');
            c=read();
            //System.out.println("RES 1 : "+res);
        }
        if(c=='.')
        {
            c=read();
            //int count=0;
            double fac=10;
            do
            {
                if(c=='e' || c=='E')
                    return res*Math.pow(10, readInt());
                if(c<'0' || c>'9')
                    throw new InputMismatchException();
                
                //res= res*10+(c-'0');
                //count++;
                res+=(c-'0')/fac;
                fac*=10;
                c=read();
            }
            while(!isBreakChar(c));
            //res/=Math.pow(10, count);
        }
        return res*sign;
    }
    public char readChar()
    {
        int c = read();
        if(c!='\n' && read()=='\n')
            return (char)c;
        throw new InputMismatchException();
    }
    public String readString()
    {
        int c = read();
        while(isBreakChar(c))
        {
            c=read();
        }
        StringBuilder res = new StringBuilder();
        do
        {
            res.appendCodePoint(c);
            c=read();
        }
        while (!isBreakChar(c));
        return res.toString();
    }
    public String readStringLine()
    {
        int c = read();
        StringBuilder res = new StringBuilder();
        do
        {
            res.appendCodePoint(c);
            c=read();
        }
        while (c!='\n');
        return res.toString();
    }
    public String next()
    {
        return readString();
    }
    
    private boolean isBreakChar(int c) 
    {
        if(brkChar==null)
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        else
        {
            for(char ch : brkChar)
            {
                if(c==ch)
                    return true;
            }
        }
        return false;
    }
}
